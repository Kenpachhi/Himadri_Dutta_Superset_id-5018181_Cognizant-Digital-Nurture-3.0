DECLARE
    v_loan_id Loans.LoanID%TYPE;
    v_customer_id Loans.CustomerID%TYPE;
    v_customer_name Customers.Name%TYPE;
    v_end_date Loans.EndDate%TYPE;

BEGIN
    FOR cur_loan IN (
        SELECT l.LoanID, l.CustomerID, l.EndDate
        FROM Loans l
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        v_loan_id := cur_loan.LoanID;
        v_customer_id := cur_loan.CustomerID;
        v_end_date := cur_loan.EndDate;

        SELECT Name INTO v_customer_name
        FROM Customers
        WHERE CustomerID = v_customer_id;

        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ' || v_loan_id || ' for customer ' || v_customer_name || ' is due on ' || v_end_date);
    END LOOP;
END;
