#!/bin/sh

# dropdb dw_todo 
# dropuser dw_todo

# Update apt and install postgres and java runtime ubuntu (vm)
# sudo apt-get update
# sudo apt-get install postgresql
# sudo apt-get install default-jre

# Create a user, db, and table for todo app
echo "Creating dev database"
createuser -s dw_todo_dev
createdb dw_todo -U dw_todo
psql -d dw_todo -U dw_todo -f create_db.sql

echo "Creating test database"
createdb dw_todo_test -U dw_todo
psql -d dw_todo_test -U dw_todo -f create_db.sql
