08/24/24 - initial setup of workspace environment
  - spent about an hour configuring a new, empty workspace, updating visual code, getting java setup, etc.
  - used the spring initializer to setup a very basic project space
  - checked into git for a first timestamp, about 1.5 hours used.
  - manually created a file to create tables, load tables with tiny amount of data
  - hooked up liquibase, generate database docs and change set w/ data to initialize database
  - configured base properties for liquibase, changes to application.yaml file
  - all green on running of tests
  - installed jgit ver to grab versions from git tags
  - things look smoot to begin making the entities to work with the existing tables
  - starting again
  - stopped, was having issues getting the test to run correctly, errors, on EX_LOANPROGRAM
  - starting again, stopped, starting: issue is just getting testing going
  - got it to work in testing, was trying too many things
. . .
  - check the git log for some details as didn't update this file
  - changed the initial database to add some fields, tables
. . .
08/29/24 
  - briefly worked to complete the entities
  - commented out test code to get it to run and complete some test
  - need to work on the test code now
08/30/24
  - got some of the repositories to work; need manually add routines as not defaults not auto per quarkus
  - had issue with @query syntax, no need for quotes like actual sql
  - shifted to @datajpatests
  - don't really need to set show-sql because @datajpatest turns on
  - needed to add testentitymanager bean to test class
  - should be able to pick up speed now
08/31/24
  - working on test suite
  - fixed the messed up column names
  - loads rows into all test tables
  - test transaction turned off so saves test data
