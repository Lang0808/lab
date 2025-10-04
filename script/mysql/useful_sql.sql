-- View table avg row size
select TABLE_NAME, AVG_ROW_LENGTH from INFORMATION_SCHEMA.tables;

-- grant permission for user
grant SESSION_VARIABLES_ADMIN on *.* to 'luke_luke'@'%';