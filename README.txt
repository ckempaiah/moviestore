Requires following software
Maven 2.1  or later
Mysql 5.0 or later
git 1.7 or later

Steps to build and run the project
* Download the project from github
* cd to the project root directory
* open the project in the desired IDE
* if using eclipse execute "mvn eclipse:eclipse" so it builds required .project and .classpath files
* In Intellij one can open the project using the pom.xml file.
* First time execution of mvn commands would require internet connection as it downloads all the libraries from the external repository
* change the connection properties in src\main\resources\META-INF\spring\database.properties
* modify property src\main\resources\META-INF\persistence.xml <property name="hibernate.hbm2ddl.auto" value="create-drop"/>
to create fresh schema every time, there are couple of more options follow the instructions in the file.
* execute "mvn clean install"
* execute "mvn jetty:run"
* Make sure webapp comes up without errors
* access the portal using http://www.localhost.com:8080/movie
* the default username/password is admin/admin


Additional commands
* mvn clean -> cleans project artifacts
* mvn compile -> just compiles the project
* connect to local mysql on command line
mysql -h <host> -u <username> -p <password>
example: mysql -h localhost -u root -p sa


