# Developer Instructions

## Spring Profiles

 - `work-h2db` will use an in-memory H2 database and have Hibernate initialize it.
 - `test-h2db` will use an in-memory H2 database and have Liquibase initialize it.
 - `test-psql` will use the local PostgreSQL container and have Liquibase initialize it.

### Visual Code:  Running or Debugging the Application

1. Go to the "Run and Debug" mode on the sidebar. ( triangle start button )
2. Just under the main application menu there is a "RUN AND DEBUG" profile selection.
3. Choose a mode matching the Spring profile definition above you wish to use.
4. Go!

The application will start using the backend you chose with the expected initialization.  The `work-h2db` profile is good for rapidly changing entity definitions.  The initial data could be loaded the "Hibernate way," but is not yet setup.
The two (2) testing modes use Liquibase to initialize the database, with the `test-h2db` using an in-memory H2 database while the `test-psql` is using the PostgreSQL container database.  The PostgreSQL database may need to be manually started first.

### Visual Code:  Running or Debugging the Application Unit Tests

1. Go to the "Testing" mode on the sidebar. ( the laboratory beaker )
2. To the left of a line on the displayed test hierarchy, hover, and then choose the "Run Test", "Debug Test", or "Run Test with Coverage" option to get testing.

Currently the application Visual Code `settings.json` for the project is using the `test` profile.  This includes the `src/test/resources/application.yaml` configuration file which includes the `work-h2db` profile as well.  This means that the application will be using a Hibernate initialized in-memory H2 database.  This should give good flexibility i rapidly developing changes in the database.  We'll need to do a Liquibase difference prior to final testing so we can verify the database migration process.

## Maven Profiles

- `default`
- `personal-<initials>`
- `release`
- `services` will include the docker containers in the effective pom, to start, stop, amd build them.
- ....

### Typical Maven Commands

- `mvn -Pservices docker:start` will build and start the service containers:  PostgreSQL and Keycloak.
- `mvn -Pservices docker:stop`  will stop the containers.
- `mvn versions:display-property-updates` will check for dependencies and plugins items that can easily be upgraded.
- `mvn -Dspring.profiles.active=test,work-h2db install` equivilent to the Visual Code testing mode above.
- `mvn liquibase:....`

NOTE:  The maven tests will work the same with the same database initialization.  Right now the setup for Visual Code uses `work-h2db` and its Hibernate initialization.  Maven will get the same result if you use the same Spring profile.  The Liquibase intialization has more data.

## Maven Versioning

The version via Maven is driven by the recent git tags.  I can explain, but it is not something to worry about.  It will be unique for each set of builds against a given commit because of the unique git hashcode.  You will notice a 'dirty' in the string if you have not yet commited your changes to your local git.