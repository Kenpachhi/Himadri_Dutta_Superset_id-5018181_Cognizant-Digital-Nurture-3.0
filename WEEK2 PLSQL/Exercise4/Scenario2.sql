CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_LoanAmount NUMBER,
    p_InterestRate NUMBER,
    p_LoanDurationYears NUMBER
)
RETURN NUMBER
AS
    v_MonthlyInterestRate NUMBER;
    v_LoanDurationMonths NUMBER;
    v_MonthlyInstallment NUMBER;
BEGIN
    v_MonthlyInterestRate := p_InterestRate / 100 / 12;
    v_LoanDurationMonths := p_LoanDurationYears * 12;
    
    v_MonthlyInstallment := p_LoanAmount * v_MonthlyInterestRate * POWER(1 + v_MonthlyInterestRate, v_LoanDurationMonths) / (POWER(1 + v_MonthlyInterestRate, v_LoanDurationMonths) - 1);
    
    RETURN ROUND(v_MonthlyInstallment, 2);
END;
/
