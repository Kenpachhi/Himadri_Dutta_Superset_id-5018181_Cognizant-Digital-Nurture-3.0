-- First, we need to add a new column IsVIP to the Customers table
ALTER TABLE Customers ADD IsVIP VARCHAR2(5) DEFAULT 'FALSE';

DECLARE
    v_customer_id Customers.CustomerID%TYPE;
    v_balance Customers.Balance%TYPE;

BEGIN
    FOR cur_customer IN (SELECT CustomerID, Balance FROM Customers) LOOP
        v_customer_id := cur_customer.CustomerID;
        v_balance := cur_customer.Balance;

        IF v_balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = v_customer_id;
        END IF;
    END LOOP;

    COMMIT;
END;
