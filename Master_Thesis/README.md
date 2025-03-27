# Master Thesis implementation #

## General info ##

This repository serves as the codebase for my master thesis. It contains both the database coding with sample data and the ETL code generation software.

## Technologies ##
The databases are coded for MS SQL Server and use so-called database projects for easier building, publishing in VS Code / Azure Data Studio. The ETL generator software is coded in Java 22, using Gradle, Spring Boot, Spring Data JDBC.

## Setup ##

### Database ###

#### MS SQL Server
For development, I used a docker image of MS SQL Server 2022. Run the image like the following code snipped and adapt where required.

  ```bash
  docker run --name "SQL-Server" -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=aStrong(!)Password" -p 1433:1433 -d mcr.microsoft.com/mssql/server:2022-latest
  ```

#### Database Projects

The database files are located in 'Databases'. Each database is implemented as a SQL server project in Azure Data Studio. More on this soon-to-be dead software later on.\
Open the project in Azure Data Studio (by selecting the sqlproj file), then publish the database to a running SQL server instance. Select the database in the project view, right-click and select publish.

Shortly after finishing the implementation and the thesis transcript, Microsoft published that it is discontinuing Azure Data Studion and users should migrate to its base plattform VS Code, requiring 1-2 additional extension. Therefore, the usage of VS Code is prefered to Azure Data Studio.\
See: https://learn.microsoft.com/en-gb/azure-data-studio/whats-happening-azure-data-studio


### ETL Generator Software
The Software is done with Java 22 and Spring. In theory, everything should also work with Java 17, as  far as I am aware. However, the at the time latest and greatest variant was used.

#### Setup applications properties

For each database (KnowledgeBase, TemplateDB, sources) an additional input into the `application.properties` file is required. It is located in `src/main/ressources` as a template file.
First of all, remove the `-template` in the file name, as the software will throw an error on run. Next, fill in the information for TemplateDB and KnowledgeDB, to avoid an error on startup.

```properties
eu.sternbauer.db-config.knowledgebase.url=jdbc:<server-type>://<address>:<port>;database=<db>;trustServerCertificate=true
eu.sternbauer.db-config.knowledgebase.username=<username>
eu.sternbauer.db-config.knowledgebase.password=<password>
eu.sternbauer.db-config.knowledgebase.driver-class-name=<jdbc driver>

eu.sternbauer.db-config.datatarget.url=<address>,<port>
eu.sternbauer.db-config.datatarget.username=<username>
eu.sternbauer.db-config.datatarget.password=<password>
eu.sternbauer.db-config.datatarget.driver-class-name=<jdbc driver>
eu.sternbauer.db-config.datatarget.database_name=<KB name of data target>

eu.sternbauer.db_config.datasource[0].url=<address>,<port>
eu.sternbauer.db_config.datasource[0].username=<username>
eu.sternbauer.db_config.datasource[0].password=<password>
eu.sternbauer.db_config.datasource[0].driver_class_name=<jdbc driver>
eu.sternbauer.db-config.datasource[0].database_name=<KB name of data source>
```

For each additional datasource, add more entries with an increase index counter, e.g. [1], [2], [3] etc.

#### Run
Use your favourite IDE or editor, for example IntelliJ IDEA or VS Code. As this is a Gradle project, you can just use the IDE's integrated compile / run for Gradle or manually run the build/run commands

### Additional remarks
More on the internals in the thesis transcript or in the code itself

## Credits ##
Many thanks to Professor Josef Küng for his supervision of my thesis
