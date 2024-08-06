DECLARE
    -- Declare variables
    v_account_id NUMBER;
    v_balance NUMBER;
    v_last_modified DATE;
    v_annual_fee NUMBER := 50;  -- Annual maintenance fee

    -- Declare cursor
    CURSOR all_accounts IS
        SELECT AccountID, Balance, LastModified
        FROM Accounts;

    -- Open cursor
    BEGIN
        OPEN all_accounts;

        -- Fetch and process each row
        LOOP
            FETCH all_accounts INTO v_account_id, v_balance, v_last_modified;

            EXIT WHEN all_accounts%NOTFOUND;

            -- Deduct annual maintenance fee from balance
            v_balance := v_balance - v_annual_fee;

            -- Update account balance and last modified date
            UPDATE Accounts
            SET Balance = v_balance,
                LastModified = SYSTIMESTAMP
            WHERE AccountID = v_account_id;
        END LOOP;

        -- Close cursor
        CLOSE all_accounts;
    END;
/
