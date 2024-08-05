CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id Employees.EmployeeID%TYPE,
    p_percentage NUMBER
) AS
    v_employee_salary Employees.Salary%TYPE;

BEGIN
    -- Check if employee exists
    BEGIN
        SELECT Salary INTO v_employee_salary
        FROM Employees
        WHERE EmployeeID = p_employee_id;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20001, 'Employee ID does not exist');
    END;

    -- Update salary
    UPDATE Employees
    SET Salary = Salary * (1 + p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Salary of employee '| p_employee_id || 'pdated successfully');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE_APPLICATION_ERROR(-20002, 'Error updating salary: '| SQLERRM);
END;
