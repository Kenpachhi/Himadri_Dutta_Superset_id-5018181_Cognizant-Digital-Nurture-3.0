CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account_id Accounts.AccountID%TYPE,
    p_to_account_id Accounts.AccountID%TYPE,
    p_amount NUMBER
) AS
    v_from_account_balance Accounts.Balance%TYPE;
    v_to_account_balance Accounts.Balance%TYPE;

BEGIN
    -- Check if both accounts exist
    IF NOT EXISTS (SELECT 1 FROM Accounts WHERE AccountID = p_from_account_id) THEN
        RAISE_APPLICATION_ERROR(-20001, 'From account does not exist');
    END IF;

    IF NOT EXISTS (SELECT 1 FROM Accounts WHERE AccountID = p_to_account_id) THEN
        RAISE_APPLICATION_ERROR(-20002, 'To account does not exist');
    END IF;

    -- Check if accounts are not the same
    IF p_from_account_id = p_to_account_id THEN
        RAISE_APPLICATION_ERROR(-20003, 'Cannot transfer funds to the same account');
    END IF;

    -- Check if amount is positive
    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Transfer amount must be positive');
    END IF;

    -- Get current balances
    SELECT Balance INTO v_from_account_balance
    FROM Accounts
    WHERE AccountID = p_from_account_id;

    SELECT Balance INTO v_to_account_balance
    FROM Accounts
    WHERE AccountID = p_to_account_id;

    -- Check if from account has sufficient funds
    IF v_from_account_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20005, 'Insufficient funds in from account');
    END IF;

    -- Perform transfer
    BEGIN
        UPDATE Accounts
        SET Balance = Balance - p_amount
        WHERE AccountID = p_from_account_id;

        UPDATE Accounts
        SET Balance = Balance + p_amount
        WHERE AccountID = p_to_account_id;

        -- Log transaction
        INSERT INTO Transactions (AccountID, TransactionDate, Amount, TransactionType)
        VALUES (p_from_account_id, SYSDATE, -p_amount, 'TRANSFER');

        INSERT INTO Transactions (AccountID, TransactionDate, Amount, TransactionType)
        VALUES (p_to_account_id, SYSDATE, p_amount, 'TRANSFER');

        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20006, 'Error transferring funds: ' || SQLERRM);
    END;
END;
