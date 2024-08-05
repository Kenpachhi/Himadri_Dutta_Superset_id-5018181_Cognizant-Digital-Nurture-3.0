DECLARE
    v_customer_id Customers.CustomerID%TYPE;
    v_dob Customers.DOB%TYPE;
    v_age NUMBER;
    v_loan_id Loans.LoanID%TYPE;
    v_interest_rate Loans.InterestRate%TYPE;

BEGIN
    FOR cur_customer IN (SELECT CustomerID, DOB FROM Customers) LOOP
        v_customer_id := cur_customer.CustomerID;
        v_dob := cur_customer.DOB;
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, v_dob) / 12);

        IF v_age > 60 THEN
            FOR cur_loan IN (SELECT LoanID, InterestRate FROM Loans WHERE CustomerID = v_customer_id) LOOP
                v_loan_id := cur_loan.LoanID;
                v_interest_rate := cur_loan.InterestRate;

                UPDATE Loans
                SET InterestRate = v_interest_rate - (v_interest_rate * 0.01)
                WHERE LoanID = v_loan_id;
            END LOOP;
        END IF;
    END LOOP;

    COMMIT;
END;
