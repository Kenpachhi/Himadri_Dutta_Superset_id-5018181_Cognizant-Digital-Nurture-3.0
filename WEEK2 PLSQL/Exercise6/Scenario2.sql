DECLARE
    
    v_account_id NUMBER;
    v_balance NUMBER;
    v_last_modified DATE;
    v_annual_fee NUMBER := 50;  

    
    CURSOR all_accounts IS
        SELECT AccountID, Balance, LastModified
        FROM Accounts;

  
    BEGIN
        OPEN all_accounts;

        LOOP
            FETCH all_accounts INTO v_account_id, v_balance, v_last_modified;

            EXIT WHEN all_accounts%NOTFOUND;

            
            v_balance := v_balance - v_annual_fee;

            
            UPDATE Accounts
            SET Balance = v_balance,
                LastModified = SYSTIMESTAMP
            WHERE AccountID = v_account_id;
        END LOOP;

       
        CLOSE all_accounts;
    END;
/
