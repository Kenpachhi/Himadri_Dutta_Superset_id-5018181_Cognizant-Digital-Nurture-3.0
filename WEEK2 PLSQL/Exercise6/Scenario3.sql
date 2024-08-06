DECLARE
    -- Declare variables
    v_loan_id NUMBER;
    v_customer_id NUMBER;
    v_loan_amount NUMBER;
    v_interest_rate NUMBER;
    v_start_date DATE;
    v_end_date DATE;
    v_new_interest_rate NUMBER;

    -- Declare cursor
    CURSOR all_loans IS
        SELECT LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate
        FROM Loans;

    -- Open cursor
    BEGIN
        OPEN all_loans;

        -- Fetch and process each row
        LOOP
            FETCH all_loans INTO v_loan_id, v_customer_id, v_loan_amount, v_interest_rate, v_start_date, v_end_date;

            EXIT WHEN all_loans%NOTFOUND;

            -- Update interest rate based on new policy
            IF v_loan_amount < 10000 THEN
                v_new_interest_rate := v_interest_rate + 0.5;
            ELSIF v_loan_amount BETWEEN 10000 AND 50000 THEN
                v_new_interest_rate := v_interest_rate + 0.25;
            ELSE
                v_new_interest_rate := v_interest_rate - 0.25;
            END IF;

            -- Update loan interest rate
            UPDATE Loans
            SET InterestRate = v_new_interest_rate
            WHERE LoanID = v_loan_id;
        END LOOP;

        -- Close cursor
        CLOSE all_loans;
    END;
/
