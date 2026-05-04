CREATE TABLE audit_log (
    id BIGSERIAL PRIMARY KEY,
    entity_type VARCHAR(100),
    entity_id BIGINT,
    action VARCHAR(50),
    old_value TEXT,
    new_value TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX idx_audit_entity
ON audit_log(entity_type, entity_id);