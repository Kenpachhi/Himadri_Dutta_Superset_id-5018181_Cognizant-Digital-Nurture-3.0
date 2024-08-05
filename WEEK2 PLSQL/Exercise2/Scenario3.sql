CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id Customers.CustomerID%TYPE,
    p_name Customers.Name%TYPE,
    p_dob Customers.DOB%TYPE,
    p_balance Customers.Balance%TYPE,
    p_last_modified Customers.LastModified%TYPE
) AS
BEGIN
    -- Check if customer already exists
    IF EXISTS (SELECT 1 FROM Customers WHERE CustomerID = p_customer_id) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Customer ID already exists');
    END IF;

    -- Insert new customer
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, p_last_modified);

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('New customer added successfully');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20002, 'Error adding new customer: '| SQLERRM);
END;
