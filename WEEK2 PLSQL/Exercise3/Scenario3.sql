CREATE OR REPLACE PROCEDURE TransferFunds(
    p_SourceAccountID NUMBER,
    p_DestinationAccountID NUMBER,
    p_Amount NUMBER
)
AS
    v_SourceBalance NUMBER;
BEGIN
    -- Check if source account has sufficient balance
    SELECT Balance INTO v_SourceBalance
    FROM Accounts
    WHERE AccountID = p_SourceAccountID;

    IF v_SourceBalance < p_Amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance in source account');
    END IF;

    -- Update source account balance
    UPDATE Accounts
    SET Balance = Balance - p_Amount
    WHERE AccountID = p_SourceAccountID;

    -- Update destination account balance
    UPDATE Accounts
    SET Balance = Balance + p_Amount
    WHERE AccountID = p_DestinationAccountID;

    -- Insert transaction records
    INSERT INTO Transactions (AccountID, TransactionDate, Amount, TransactionType)
    VALUES (p_SourceAccountID, SYSTIMESTAMP, -p_Amount, 'Transfer Out');

    INSERT INTO Transactions (AccountID, TransactionDate, Amount, TransactionType)
    VALUES (p_DestinationAccountID, SYSTIMESTAMP, p_Amount, 'Transfer In');
END;
/
