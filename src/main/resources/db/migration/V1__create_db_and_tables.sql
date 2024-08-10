use tempdb;

CREATE TABLE users (
        id BIGINT IDENTITY PRIMARY KEY,
        first_name NVARCHAR(255),
        middle_name NVARCHAR(255),
        last_name NVARCHAR(255),
        email NVARCHAR(255),
        address NVARCHAR(255),
        phone NVARCHAR(50),
        birth_date DATE,
        created_date DATETIME2,
        modified_date DATETIME2
);

CREATE TABLE accounts (
        id BIGINT IDENTITY PRIMARY KEY,
        owner_id BIGINT NOT NULL,
        first_name NVARCHAR(255),
        last_name NVARCHAR(255),
        account_number NVARCHAR(20),
        account_type VARCHAR(8) CHECK (account_type in ('SAVINGS', 'CHECKING', 'BUSINESS')) NOT NULL DEFAULT 'CHECKING',
        account_status VARCHAR(8) CHECK (account_status in ('ACTIVE', 'INACTIVE', 'CLOSED')) NOT NULL DEFAULT 'INACTIVE',
        balance DECIMAL(19, 4),
        min_balance DECIMAL(19, 4),
        created_date DATETIME2,
        modified_date DATETIME2,
        FOREIGN KEY (owner_id) REFERENCES users(id),
);

ALTER TABLE
    accounts
ADD CONSTRAINT
    check_balance
        DEFAULT 0.0
FOR balance;