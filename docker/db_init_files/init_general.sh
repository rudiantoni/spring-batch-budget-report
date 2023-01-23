STD_PG_USER=$POSTGRES_USER
STD_PG_DB=$POSTGRES_DB
APP_DATA_DB=$DB_DATASOURCE_DATABASE
APP_DATA_USER=$DB_DATASOURCE_USERNAME
SQL_FILE_DB_CREATION=/docker-entrypoint-initdb.d/.sql/database_creation.sql
SQL_FILE_APP_DB_GENERAL=/docker-entrypoint-initdb.d/.sql/app_data_db_general.sql

echo
echo "Running SQL file ($SQL_FILE_DB_CREATION) to database ($STD_PG_DB) with user ($STD_PG_USER)."
psql --set=dbname=$APP_DATA_DB --echo-all --username $STD_PG_USER --dbname $STD_PG_DB --file $SQL_FILE_DB_CREATION

echo
echo "Running SQL file ($SQL_FILE_APP_DB_GENERAL) to database ($APP_DATA_DB) with user ($APP_DATA_USER)."
psql --echo-all --username $APP_DATA_USER --dbname $APP_DATA_DB --file $SQL_FILE_APP_DB_GENERAL
