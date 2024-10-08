#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE APP_MODERN_DEMO;

	CREATE ROLE APP_MASTER;
	GRANT ALL PRIVILEGES ON DATABASE APP_MODERN_DEMO TO APP_MASTER;

	CREATE USER APP_RUNNER WITH PASSWORD 'secret';
	GRANT ALL PRIVILEGES ON DATABASE APP_MODERN_DEMO TO APP_RUNNER;
EOSQL
