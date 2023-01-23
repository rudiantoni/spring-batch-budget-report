# spring-batch-budget-report
- Exercise answer, using both multiple delimited file readers with related domain classes and database as datasource..
- Using double PostgreSQL database for metadata and application data persisting.
- Using multiple delimited files as an data source option.
- Dockerized project: both application and database runs in separate containers.
- Automation oriented: most settings are done via shell scripts and environment variables.

## Requirements

- **Linux**
    - Docker
        - Instructions: [Install Docker Engine on Ubuntu | Docker Documentation](https://docs.docker.com/engine/install/ubuntu/)
    - Docker compose
        - Instructions: [Install the Compose standalone | Docker Documentation](https://docs.docker.com/compose/install/other/)
    - (Optional) Java (JRE/JDK)
        - Only needed if you wish to run the application locally, with or without any IDE aid.
        - Recommended JRE or JDK version 17.
        - Instructions: [OpenJDK: Download and install](https://openjdk.org/install/)
- **Windows**
    - Docker and Docker Compose plugin
        - Instructions: [Install on Windows | Docker Documentation](https://docs.docker.com/desktop/install/windows-install/)
    - (Optional) Java (JRE/JDK)
        - Only needed if you wish to run the application locally, with or without any IDE aid.
        - Recommended JRE or JDK version 17.
        - Instructions: [Java Downloads | Oracle](https://www.oracle.com/java/technologies/downloads/)

## Running

- **Linux**: Run the file *deploy.sh* with: `sh deploy.sh`.
- **Windows**: Run the file *deploy.bat* with: `deploy.bat` in an command prompt window with administrator privileges.
- For didatic purposes (like connecting and checking the database, checking logs, etc), the application doesn't clean the created containers automatically when finishes it's execution, so just follow the *Stopping* section instructions in order to fully stop and clean the application containers.

### Other settings
- Between various other settings available in the file *environment.env*, the ones that you may be interested are the following:
    - **DB_INIT_FILES**: database initialize files folder, as soon as the database starts, it will run any non hidden *.sh* and *.sql* files inside this root folder.
    - **USE_FILE**: sets the data source mode, true for file and false for database. Default: false
> Currently the database initialize files folder at: *./docker/db_init_files*. It's mounted as a volume, sharing its contents with the database container.

> Currently the data source files (appointments*.txt) is located at: *./docker/datasource_files*. It's mounted as a volume, sharing its contents with the application container.

> If you change the */opt/app/* portion in the environment variables, you will need to change the *./docker/docker-compose.yml* file in order to share the right volume.

## Stopping

### Linux
- **Linux**: Run the file *stop.sh* with: `sh stop.sh`
- **Windows**: Run the file *stop.bat* with: `stop.bat` in an command prompt window with administrator privileges.
    - This is intendedd to fully stop and remove the application containers.
    - You don't need to stop the application if you want to run it again.

Log image with working example:
- **Linux**: [spring-batch-budget-report](https://i.imgur.com/RXUcnVS.png)
- **Windows**: [spring-batch-budget-report](xxx)
