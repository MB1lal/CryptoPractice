**CryptoPractice**
-

A test project which included front-end and back-end tests of coin https://coinmarketcap.com .

These tests use Cucumber-Java.


**How to execute:**
-

1. Frontend:

Front end tests can be executed by running the runner class or using following command in terminal :

        mvn clean install -Dtest=frontend.driver.Runner
        
2. Backend:

Back end tests can be executed by running the runner class or using following command in terminal :

        mvn clean install -Dtest=backend.driver.Runner`


**Known Issues:**
- 
1. Extent reporting is not fully functional.
2. Firefox driver is not yet added.