CREATE OR REPLACE PACKAGE EmployeeManagement AS
    -- Procedure to hire a new employee
    PROCEDURE HireEmployee(
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name IN Employees.Name%TYPE,
        p_position IN Employees.Position%TYPE,
        p_salary IN Employees.Salary%TYPE,
        p_department IN Employees.Department%TYPE,
        p_hire_date IN Employees.HireDate%TYPE
    );

    -- Procedure to update employee details
    PROCEDURE UpdateEmployee(
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name IN Employees.Name%TYPE,
        p_position IN Employees.Position%TYPE,
        p_salary IN Employees.Salary%TYPE,
        p_department IN Employees.Department%TYPE
    );

    -- Function to calculate annual salary
    FUNCTION CalculateAnnualSalary(
        p_employee_id IN Employees.EmployeeID%TYPE
    ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    -- Procedure to hire a new employee
    PROCEDURE HireEmployee(
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name IN Employees.Name%TYPE,
        p_position IN Employees.Position%TYPE,
        p_salary IN Employees.Salary%TYPE,
        p_department IN Employees.Department%TYPE,
        p_hire_date IN Employees.HireDate%TYPE
    ) AS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_employee_id, p_name, p_position, p_salary, p_department, p_hire_date);
    END HireEmployee;

    -- Procedure to update employee details
    PROCEDURE UpdateEmployee(
        p_employee_id IN Employees.EmployeeID%TYPE,
        p_name IN Employees.Name%TYPE,
        p_position IN Employees.Position%TYPE,
        p_salary IN Employees.Salary%TYPE,
        p_department IN Employees.Department%TYPE
    ) AS
    BEGIN
        UPDATE Employees
        SET Name = p_name,
            Position = p_position,
            Salary = p_salary,
            Department = p_department
        WHERE EmployeeID = p_employee_id;
    END UpdateEmployee;

    -- Function to calculate annual salary
    FUNCTION CalculateAnnualSalary(
        p_employee_id IN Employees.EmployeeID%TYPE
    ) RETURN NUMBER AS
        v_salary NUMBER;
    BEGIN
        SELECT Salary
        INTO v_salary
        FROM Employees
        WHERE EmployeeID = p_employee_id;

        RETURN v_salary * 12;
    END CalculateAnnualSalary;
END EmployeeManagement;
/
