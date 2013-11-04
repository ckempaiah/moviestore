=================Project Setup ==================
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

* Aruna Project Notes Start
---------------------------

-----------------------------------------------------------------------------------------------------
1. Install Maven in the System 

Download form Apache site: http://maven.apache.org/download.cgi
File Name: apache-maven-3.1.1-bin.zip

Go to Environment Variables --> Set Maven Home Variable as M2_HOME as C:\work\apache-maven-3.1.1 

Set PATH variable to C:\work\apache-maven-3.1.1\bin

Validate from Command Prompt --- "mvn -version"

RESULT should be:
------------------------------------------------------------------------------------------------------
Apache Maven 3.1.1 (0728685237757ffbf44136acec0402957f723d9a; 2013-09-17 08:22:2
2-0700)
Maven home: C:\"your_dir"\apache-maven-3.1.1
Java version: 1.7.0, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.7.0\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows nt (unknown)", version: "6.2", arch: "amd64", family: "windows
"
-----------------------------------------------------------------------------------------------------
2. Open cmd prompt and Go to Project Home Directory
C:\"your_dir"\movie-store

mvn clean --> To clean all the target class files
mvn install --> To compile the source files

Result:
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 41.679s
[INFO] Finished at: Sat Nov 02 10:37:28 PDT 2013
[INFO] Final Memory: 25M/293M
[INFO] ------------------------------------------------------------------------


---------------------------------------------------------------------------------

3. Add Tomcat Plugin in the Root POM.xml 

 <build>
     <plugins>
		<plugin>
          <groupId>org.apache.tomcat.maven</groupId>
          <artifactId>tomcat7-maven-plugin</artifactId>
          <version>2.1</version>
        </plugin>
----------------------------------------------------------------------------------
4. mvn tomcat7:run
----------------------------------------------------------------------------------
5. To open in Eclipse :
In cmd prompt execute command: mvn eclipse:eclipse

This will generate .project and .classpath in your project directory.
----------------------------------------------------------------------------------
6. now open eclipse, create a new workspace (if needed).

Go to Window --> preferences --> Maven --> Installations

Uncheck the embedded and click add.
browse the directory where maven is installed and say ok.

Now we have set the maven directory in eclipse instead of a plugin
----------------------------------------------------------------------------------
7. Import the project into eclipse
----------------------------------------------------------------------------------
8. Run the project by right clicking on pom.xml --> 
maven clean
maven install
maven build -->tomcat:run
----------------------------------------------------------------------------------

Aruna Project Notes Ends



