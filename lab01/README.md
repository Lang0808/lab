# LAB 01 - DATABASE MIGRATION

## Define

In software development, there are cases that we need to migrate from old to new database.

Requirements are:

- Not downtime. User must use system normally during the time we migrate database
- Emergency. Rollback-able. If something happens during database migration, such as writing to new database fail, we
  must rollback to the old database.
- Correctness. The old and new database must be the same, must be consistent.

## Technologies must know

### Debezium

A service that captures all row-level changes happen in database, so that application can respond to it.


