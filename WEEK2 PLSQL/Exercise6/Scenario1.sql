DECLARE
    -- Declare variables
    v_customer_id NUMBER;
    v_customer_name VARCHAR2(100);
    v_account_id NUMBER;
    v_account_type VARCHAR2(20);
    v_transaction_date DATE;
    v_amount NUMBER;
    v_transaction_type VARCHAR2(10);
    v_balance NUMBER;
    v_last_modified DATE;

    -- Declare cursor
    CURSOR monthly_transactions IS
        SELECT t.AccountID, c.CustomerID, c.Name, a.AccountType, t.TransactionDate, t.Amount, t.TransactionType, a.Balance, a.LastModified
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM');

    -- Open cursor
    BEGIN
        OPEN monthly_transactions;

        -- Fetch and process each row
        LOOP
            FETCH monthly_transactions INTO v_account_id, v_customer_id, v_customer_name, v_account_type, v_transaction_date, v_amount, v_transaction_type, v_balance, v_last_modified;

            EXIT WHEN monthly_transactions%NOTFOUND;

            -- Print statement for each customer
            DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_customer_id);
            DBMS_OUTPUT.PUT_LINE('Customer Name: ' || v_customer_name);
            DBMS_OUTPUT.PUT_LINE('Account ID: ' || v_account_id);
            DBMS_OUTPUT.PUT_LINE('Account Type: ' || v_account_type);
            DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || v_transaction_date);
            DBMS_OUTPUT.PUT_LINE('Amount: ' || v_amount);
            DBMS_OUTPUT.PUT_LINE('Transaction Type: ' || v_transaction_type);
            DBMS_OUTPUT.PUT_LINE('Balance: ' || v_balance);
            DBMS_OUTPUT.PUT_LINE('Last Modified: ' || v_last_modified);
            DBMS_OUTPUT.PUT_LINE('------------------------');
        END LOOP;

        -- Close cursor
        CLOSE monthly_transactions;
    END;
/
