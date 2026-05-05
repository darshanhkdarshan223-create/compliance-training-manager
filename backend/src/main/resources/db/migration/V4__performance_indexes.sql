CREATE INDEX IF NOT EXISTS idx_training_status
    ON training_record(status);

CREATE INDEX IF NOT EXISTS idx_training_title
    ON training_record(title);

CREATE INDEX IF NOT EXISTS idx_training_due_date
    ON training_record(due_date);