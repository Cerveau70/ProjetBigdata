-- Modified Hive Metastore initialization script for PostgreSQL without statement_timeout

-- Remove or comment out the statement_timeout setting causing syntax error
-- SET statement_timeout = 0;

-- The rest of the original hive-schema-2.3.0.postgres.sql script content should be here,
-- but with the above line removed or commented out.

-- For demonstration, here is a minimal example to create the metastore version table:

CREATE TABLE VERSION (
  VER_ID INT PRIMARY KEY,
  VER_NAME VARCHAR(100),
  VER_COMMENT VARCHAR(255)
);

INSERT INTO VERSION (VER_ID, VER_NAME, VER_COMMENT) VALUES (1, '2.3.0', 'Initial schema version');

-- You should replace this with the full schema script content minus the problematic line.
