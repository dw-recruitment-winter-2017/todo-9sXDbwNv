#!/bin/sh

# dropdb dw_todo 
# dropuser dw_todo

# Update apt and install postgres and java runtime
sudo apt-get update
sudo apt-get install postgresql
sudo apt-get install default-jre

# Create a user, db, and table for todo app
echo "Creating database"
createuser -s dw_todo
createdb dw_todo -U dw_todo
psql -d dw_todo -U dw_todo -f create_db.sql
