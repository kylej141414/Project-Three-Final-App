run: compile

#compile should compile the code for the project by running the frontend
compile: FrontendInterface.class FlightGraph.class AirlineReader.class Airline.class
	java FrontendInterface

FrontendInterface.class: FrontendInterface.java
	javac FrontendInterface.java
	
FlightGraph.class: FlightGraph.java
	javac FlightGraph.java
	
AirlineReader.class: AirlineReader.java
	javac AirlineReader.java
	
Airline.class: Airline.java
	javac Airline.java

#compiles the TEST CASES
test: testData testBackend testFrontend

#this tests the frontend and frontend tests
testFrontend: TestFrontend.class
	java -jar junit5.jar -cp . --scan-classpath -n TestFrontend
TestFrontend.class: TestFrontend.java
	javac -cp .:junit5.jar TestFrontend.java

#this tests the backend and backend tests
testBackend: BackEndDeveloperTests.class
	java -jar junit5.jar -cp . --scan-classpath -n BackEndDeveloperTests
BackEndDeveloperTests.class: BackEndDeveloperTests.java
	javac -cp .:junit5.jar BackEndDeveloperTests.java

#this tests the data wrangler and data wrangler tests
testData: DataWranglerTests.class
	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTests
DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java

#cleans the code
clean:
	$(RM) *.class
