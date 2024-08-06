-- Create the AuditLog table if it doesn't exist
CREATE TABLE AuditLog (
    AuditLogID NUMBER PRIMARY KEY,
    TableName VARCHAR2(50),
    Operation VARCHAR2(10),
    TransactionDate DATE,
    AccountID NUMBER,
    Amount NUMBER,
    TransactionType VARCHAR2(10)
);

-- Create a sequence for the AuditLogID
CREATE SEQUENCE AuditLogSeq;

-- Create the trigger
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (AuditLogID, TableName, Operation, TransactionDate, AccountID, Amount, TransactionType)
    VALUES (AuditLogSeq.NEXTVAL, 'Transactions', 'INSERT', SYSTIMESTAMP, :NEW.AccountID, :NEW.Amount, :NEW.TransactionType);
END;
/
