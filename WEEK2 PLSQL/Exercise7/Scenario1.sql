CREATE OR REPLACE PACKAGE CustomerManagement AS
    -- Procedure to add a new customer
    PROCEDURE AddCustomer(
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name IN Customers.Name%TYPE,
        p_dob IN Customers.DOB%TYPE,
        p_balance IN Customers.Balance%TYPE
    );

    -- Procedure to update customer details
    PROCEDURE UpdateCustomer(
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name IN Customers.Name%TYPE,
        p_dob IN Customers.DOB%TYPE
    );

    -- Function to get customer balance
    FUNCTION GetCustomerBalance(
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    -- Procedure to add a new customer
    PROCEDURE AddCustomer(
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name IN Customers.Name%TYPE,
        p_dob IN Customers.DOB%TYPE,
        p_balance IN Customers.Balance%TYPE
    ) AS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_customer_id, p_name, p_dob, p_balance, SYSTIMESTAMP);
    END AddCustomer;

    -- Procedure to update customer details
    PROCEDURE UpdateCustomer(
        p_customer_id IN Customers.CustomerID%TYPE,
        p_name IN Customers.Name%TYPE,
        p_dob IN Customers.DOB%TYPE
    ) AS
    BEGIN
        UPDATE Customers
        SET Name = p_name,
            DOB = p_dob,
            LastModified = SYSTIMESTAMP
        WHERE CustomerID = p_customer_id;
    END UpdateCustomer;

    -- Function to get customer balance
    FUNCTION GetCustomerBalance(
        p_customer_id IN Customers.CustomerID%TYPE
    ) RETURN NUMBER AS
        v_balance NUMBER;
    BEGIN
        SELECT Balance
        INTO v_balance
        FROM Customers
        WHERE CustomerID = p_customer_id;

        RETURN v_balance;
    END GetCustomerBalance;
END CustomerManagement;
/
