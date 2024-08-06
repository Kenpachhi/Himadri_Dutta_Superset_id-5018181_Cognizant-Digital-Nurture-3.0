CREATE OR REPLACE PACKAGE AccountOperations AS
    -- Procedure to open a new account
    PROCEDURE OpenAccount(
        p_account_id IN Accounts.AccountID%TYPE,
        p_customer_id IN Accounts.CustomerID%TYPE,
        p_account_type IN Accounts.AccountType%TYPE,
        p_balance IN Accounts.Balance%TYPE
    );

    -- Procedure to close an account
    PROCEDURE CloseAccount(
        p_account_id IN Accounts.AccountID%TYPE
    );

    -- Function to get the total balance of a customer across all accounts
    FUNCTION GetTotalBalance(
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    -- Procedure to open a new account
    PROCEDURE OpenAccount(
        p_account_id IN Accounts.AccountID%TYPE,
        p_customer_id IN Accounts.CustomerID%TYPE,
        p_account_type IN Accounts.AccountType%TYPE,
        p_balance IN Accounts.Balance%TYPE
    ) AS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_balance, SYSTIMESTAMP);
    END OpenAccount;

    -- Procedure to close an account
    PROCEDURE CloseAccount(
        p_account_id IN Accounts.AccountID%TYPE
    ) AS
    BEGIN
        UPDATE Accounts
        SET LastModified = SYSTIMESTAMP
        WHERE AccountID = p_account_id;

        -- Add logic to handle any pending transactions or other dependencies
        -- before closing the account
    END CloseAccount;

    -- Function to get the total balance of a customer across all accounts
    FUNCTION GetTotalBalance(
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER AS
        v_total_balance NUMBER;
    BEGIN
        SELECT SUM(Balance)
        INTO v_total_balance
        FROM Accounts
        WHERE CustomerID = p_customer_id;

        RETURN v_total_balance;
    END GetTotalBalance;
END AccountOperations;
/
