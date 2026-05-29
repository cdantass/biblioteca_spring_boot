ALTER TABLE loans
    ADD COLUMN cashier_id BIGINT;

ALTER TABLE loans
    ADD CONSTRAINT fk_loans_cashier
        FOREIGN KEY (cashier_id) REFERENCES cashiers (id);