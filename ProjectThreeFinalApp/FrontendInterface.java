// --== CS400 File Header Information ==--
// Name: Kyle Sung
// Email: kesung2@wisc.edu
// Team: Red
// Role: Front End Developer
// TA: Xinyi Ju
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class implements the frontend for the Airline Scheduler program
 * 
 * @author Kyle Sung
 *
 */
public class FrontendInterface {
  private FlightGraph flights;
  private Scanner scanner;

  public FrontendInterface(FlightGraph backend) {
    this.flights = backend;
    scanner = new Scanner(System.in);
  }


  /**
   * This method runs the FrontendInterface class and initializes the FlightGraph flights from
   * backend
   * 
   * @author Kyle Sung
   * @param args is the array of CSV files
   */
  public static void main(String[] args) throws IOException {

    try {
      // final Backend movies = new Backend(args);
      // FileReader read = new FileReader("/Users/jacksoncamp/Downloads/events.csv");
      // FileReader read = new FileReader("/Users/kylesung/Downloads/connections.csv");
      final FlightGraph flights = new FlightGraph(args);
      FrontendInterface frontend = new FrontendInterface(flights);

      frontend.baseMenu();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }



  /**
   * This method outputs the welcome message and initiates the base menu display
   * 
   */
  public void baseMenu() {
    System.out.println("Welcome to the Flight Scheduler");
    baseMenuDisplay();
  }

  /**
   * This method displays the instructions for the base menu and takes in the users input for which
   * mode to enter
   * 
   */
  public void baseMenuDisplay() {
    String navigation;

    System.out.println("Main Menu:");

    System.out.println("[z] to view all airlines");
    System.out.println("[k] to view all airports");
    System.out.println("[a] to add airport");
    System.out.println("[e] to add an airline");
    System.out.println("[r] to remove airport");
    System.out.println("[x] to remove airline");
    System.out.println("[c] to calculate shortest route");
    System.out.println("[q] to quit");
    if (scanner.hasNext()) {
      navigation = scanner.nextLine();
      if (navigation.equals("a")) {
        System.out.println();
        newAirportMode(flights);
      } else if (navigation.equals("e")) {
        System.out.println();
        addAirline(flights);
      } else if (navigation.equals("k")) {
        System.out.println();
        displayAirports(flights.getAirportsList(), flights);
      } else if (navigation.equals("z")) {
        System.out.println();
        displayAirlines(flights.getAirlinesList(), flights);
      } else if (navigation.equals("r")) {
        System.out.println();
        removeAirportMode(flights);
      } else if (navigation.equals("x")) {
        System.out.println();
        removeAirline(flights);
      } else if (navigation.equals("c")) {
        System.out.println();
        calcRouteMode(flights);
      } else if (navigation.equals("q")) {
        System.out.println();
        System.out.println("Thanks for using the Flight Scheduler!");
        return;
      } else {
        System.out.println("You did not enter a valid input");
        System.out.println();

        baseMenuDisplay();
      }
    }
  }

  /**
   * This method displays the output for a certain list of events. It allows the user to parse
   * through multiple pages of airlines if needed
   * 
   * @param list     List<Airline> the list of events from a specific category
   * @param airlines FlightGraph airlines is the BackendInterface that was created in the main
   *                 method
   */
  public void displayAirlines(List<String> list, FlightGraph airlines) {
    if (list.isEmpty()) {
      System.out.println("There are no events with this criteria");
      return;
    }

    int numPages;
    // gets number of pages
    if (list.size() % 5 == 0) {
      // System.out.println(list.size());
      numPages = list.size() / 5;
      // System.out.println(numPages);
    } else {
      numPages = list.size() / 5;
      numPages++;
    }
    System.out.println("Welcome to the Airline Viewer!");
    System.out.println("Please enter a page number to view the airlines");
    String pageNums = "Page ";
    for (int u = 1; u <= numPages; u++) {
      pageNums = pageNums + " " + u;
    }
    System.out.println(pageNums);
    int reply = scanner.nextInt(); // page number user wants to go to
    // selectedPage = Integer.valueOf(reply);
    System.out.println(numPages);
    displayAirlinesHelper(list, flights, reply, numPages);
  }

  /**
   * This method displays the output for a certain list of events. It allows the user to parse
   * through multiple pages of airports if needed
   * 
   * @param list     List<String> the list of events from a specific category
   * @param airlines FlightGraph airlines is the BackendInterface that was created in the main
   *                 method
   */
  public void displayAirports(List<String> list, FlightGraph airlines) {
    if (list.isEmpty()) {
      System.out.println("There are no events with this criteria");
      return;
    }

    int numPages;
    // gets number of pages
    if (list.size() % 5 == 0) {
      // System.out.println(list.size());
      numPages = list.size() / 5;
      // System.out.println(numPages);
    } else {
      numPages = list.size() / 5;
      numPages++;
    }
    System.out.println("Welcome to the Airports Viewer!");
    System.out.println("Please enter a page number to view the airports");
    String pageNums = "Page ";
    for (int u = 1; u <= numPages; u++) {
      pageNums = pageNums + " " + u;
    }
    System.out.println(pageNums);
    int reply = scanner.nextInt(); // page number user wants to go to
    // selectedPage = Integer.valueOf(reply);
    System.out.println(numPages);
    displayAirportsHelper(list, flights, reply, numPages);
  }

  /**
   * This method is a helper method for the displayAirlines method. It handles the functionality of
   * the user flipping through multiple pages of airlines
   * 
   * @param list     List<Airlines> the list of Events we are flipping through
   * @param airlines FlightGraph airlines is the BackendInterface that was created in the main
   *                 method
   * @param page     the specific page that we are viewing the airlines for
   * @param numPages the total number of pages in the airlines list
   */
  public void displayAirlinesHelper(List<String> list, FlightGraph airlines, int page,
      int numPages) {

    int previousEventCount = (page - 1) * 5;

    int numElementsOnLastPage = list.size() % 5;
    System.out.println();
    System.out.println("Page " + page + ":");

    int numElementsOnFirstPage;
    if (list.size() < 5) {
      numElementsOnFirstPage = list.size();
    } else {
      numElementsOnFirstPage = 5;
    }

    // if page selected if first page
    if (page == 1) {
      for (int i = 0; i < numElementsOnFirstPage; i++) {
        System.out.println("Airline " + (i + 1) + ": " + airlines.getAirlinesList().get(i));
      }
    }

    // if page selected is the last page
    else if (numPages == page) {
      for (int h = 0; h < numElementsOnLastPage; h++) {
        int num = previousEventCount + h;
        System.out.println("Airline " + (h + 1) + ": " + airlines.getAirlinesList().get(num));
      }
    }

    else {
      for (int j = 0; j < 5; j++) {
        int num = previousEventCount + j;
        System.out.println("Airline " + (j + 1) + ": " + airlines.getAirlinesList().get(num));
      }
    }

    System.out.println();
    System.out.println("Enter 'x' to return to base menu");
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter another page # to view another page of airlines");

    if (scanner.hasNext()) {
      String reply = scanner.nextLine();

      if (reply.equals("x")) {
        System.out.println();
        baseMenu();
        return;
      }
      int newPage = Integer.valueOf(reply);
      displayAirlinesHelper(list, flights, newPage, numPages);
    }
    String pageNums = "Page ";
    for (int u = 1; u <= numPages; u++) {
      pageNums = pageNums + " " + u;
    }
    System.out.println(pageNums);

  }

  /**
   * This method is a helper method for the displayAirports method. It handles the functionality of
   * the user flipping through multiple pages of airports
   * 
   * @param list     List<String> the list of airports we are flipping through
   * @param airports FlightGraph airports is the BackendInterface that was created in the main
   *                 method
   * @param page     the specific page that we are viewing the airports for
   * @param numPages the total number of pages in the airports list
   */
  public void displayAirportsHelper(List<String> list, FlightGraph airports, int page,
      int numPages) {

    int previousEventCount = (page - 1) * 5;

    int numElementsOnLastPage = list.size() % 5;
    System.out.println();
    System.out.println("Page " + page + ":");

    int numElementsOnFirstPage;
    if (list.size() < 5) {
      numElementsOnFirstPage = list.size();
    } else {
      numElementsOnFirstPage = 5;
    }

    // if page selected if first page
    if (page == 1) {
      for (int i = 0; i < numElementsOnFirstPage; i++) {
        System.out.println("Airport " + (i + 1) + ": " + airports.getAirportsList().get(i));
      }
    }

    // if page selected is the last page
    else if (numPages == page) {
      for (int h = 0; h < numElementsOnLastPage; h++) {
        int num = previousEventCount + h;
        System.out.println("Airport " + (h + 1) + ": " + airports.getAirportsList().get(num));
      }
    }

    else {
      for (int j = 0; j < 5; j++) {
        int num = previousEventCount + j;
        System.out.println("Airport " + (j + 1) + ": " + airports.getAirportsList().get(num));
      }
    }

    System.out.println();
    System.out.println("Enter 'x' to return to base menu");
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter another page # to view another page of airports");

    if (scanner.hasNext()) {
      String reply = scanner.nextLine();

      if (reply.equals("x")) {
        System.out.println();
        baseMenu();
        return;
      }
      int newPage = Integer.valueOf(reply);
      displayAirportsHelper(list, flights, newPage, numPages);
    }
    String pageNums = "Page ";
    for (int u = 1; u <= numPages; u++) {
      pageNums = pageNums + " " + u;
    }
    System.out.println(pageNums);

  }
  /**
   * this method is to define the addAirline mode to the program which enables the user to add an
   * airline or a connection between two airports
   * 
   * @param FlightGraph airports
   */
  public void addAirline(FlightGraph airports) {
    String userInput;
    String userInput2;
    int userInput3;
    String userInput4 = "";
    boolean validOrigin = false;
    boolean validDestination = false;
    System.out.println("Welcome to Airline Insert Mode!");
    System.out.println();
    System.out.println("Current Airports: ");
    System.out.println(showAirports(flights));
    System.out.println("Please input the name of origin airport you are connecting to:");
    userInput = scanner.nextLine();
    System.out.println("Please input the name of destination airport you are connecting to:");
    userInput2 = scanner.nextLine();
    /**
     * this for loop checks if the airports inputted exist in the current directory of airports
     */
    for (int i = 0; i < airports.getAirportsList().size(); i++) {
      if (airports.getAirportsList().get(i).equals(userInput)) {
        validOrigin = true;
      }
      if (airports.getAirportsList().get(i).equals(userInput2)) {
        validDestination = true;
      }
    }
    if (validDestination && validOrigin) {
      System.out.println("Please input the distance between " + userInput + " and " + userInput2);
      userInput3 = scanner.nextInt();
      airports.addAirline(userInput, userInput2, userInput3);
      System.out.println("Successfully connected " + userInput + " and " + userInput2);
      System.out.println();
    } else {
      System.out.println("The airports given were not found in the database! Please re-input.");
      System.out.println();
      addAirline(flights);
    }
    System.out.println("Would you like to add another connection? (yes/no)");
    while (!userInput4.equals("yes") && !userInput4.equals("no")) {
      userInput4 = scanner.nextLine();
      if (userInput4.equals("yes")) {
        System.out.println();
        addAirline(flights);
      } else if (userInput4.equals("no")) {
        System.out.println();
        baseMenuDisplay();
      } else {
        System.out.println();
        System.out.println("Please enter either 'yes' or 'no'.");
      }
    }
  }
  
  /**
   * this method is to define the removeAirline mode to the program which enables the user to remove
   * and airline or connection between two airports
   * 
   * @param FlightGraph airports
   */
  public void removeAirline(FlightGraph airports) {
    String userInput;
    String userInput2;
    String userInput4 = "";
    boolean validOrigin = false;
    boolean validDestination = false;
    System.out.println("Welcome to Airline Removal Mode!");
    System.out.println();
    System.out.println("Current Airports: ");
    System.out.println(showAirports(flights));
    System.out.println("Please input the name of origin airport you are removing:");
    userInput = scanner.nextLine();
    System.out.println("Please input the name of destination airport you are removing:");
    userInput2 = scanner.nextLine();
    /**
     * this for loop checks if the airports inputted exist in the current directory of airports
     */
    for (int i = 0; i < airports.getAirportsList().size(); i++) {
      if (airports.getAirportsList().get(i).equals(userInput)) {
        validOrigin = true;
      }
      if (airports.getAirportsList().get(i).equals(userInput2)) {
        validDestination = true;
      }
    }
    if (validDestination && validOrigin) {
      System.out.println("Successfully removed airline from " + userInput + " and " + userInput2);
      System.out.println();
    } else {
      System.out.println("The airports given were not found in the database! Please re-input.");
      System.out.println();
      removeAirline(flights);
    }
    System.out.println("Would you like to remove another connection? (yes/no)");
    while (!userInput4.equals("yes") && !userInput4.equals("no")) {
      userInput4 = scanner.nextLine();
      if (userInput4.equals("yes")) {
        System.out.println();
        removeAirline(flights);
      } else if (userInput4.equals("no")) {
        System.out.println();
        baseMenuDisplay();
      } else {
        System.out.println();
        System.out.println("Please enter either 'yes' or 'no'.");
      }
    }
  }
  
  /**
   * this method helps define the newAirportMode which enables the user to add a new airport to the
   * current airport directory with a specified name
   * 
   * @param FlightGraph airports
   */
  public void newAirportMode(FlightGraph airports) {
    String userInput;
    String userInput2 = "";
    System.out.println("Welcome to New Airport Insert Mode!");
    System.out.println();
    System.out.println("Current Airports: ");
    System.out.println(showAirports(flights));

    System.out.println("Please input the name of the Airport:");
    userInput = scanner.nextLine();
    airports.addAirport(userInput);
    System.out.println();
    System.out.println("Successfully added airport " + userInput + " to the database!");
    System.out.println();

    System.out.println("Would you like to add another airport? (yes/no)");
    while (!userInput2.equals("yes") && !userInput2.equals("no")) {
      userInput2 = scanner.nextLine();
      if (userInput2.equals("yes")) {
        System.out.println();
        newAirportMode(flights);
      } else if (userInput2.equals("no")) {
        System.out.println();
        baseMenuDisplay();
      } else {
        System.out.println();
        System.out.println("Please enter a 'yes' or 'no'.");
      }
    }
  }

  /**
   * this method is designed to simplify the sorting of airports when displaying them to the user.
   * Acts as a toString to display the long list of airports in a more comfortable vertical fashion
   * 
   * @param FlightGraph airports
   * @return String toReturn
   */
  public String showAirports(FlightGraph airports) {
    String toReturn = "";
    for(int i = 0; i < airports.getAirportsList().size(); i++) {
      toReturn += (i+1) + "." + " " + airports.getAirportsList().get(i) + "\n";
    }
    return toReturn;
  }
  
  /**
   * this method is designed to define the calculate mode which enables the user to calculate the
   * distance between two airports
   * 
   * @param FlightGraph airlines
   */
  public void calcRouteMode(FlightGraph airlines) {
    String userInput;
    String userInput2;
    boolean validOrigin = false;
    boolean validDestination = false;
    System.out.println("Welcome to the Shortest Route Calculator!");
    System.out.println();
    System.out.println("Current Airports: ");
    //System.out.println(airlines.getAirportsList().toString());
    System.out.println(showAirports(flights));

    System.out.println("Please input the name of origin airport:");
    userInput = scanner.nextLine();
    System.out.println("Please input the name of destination airport:");
    userInput2 = scanner.nextLine();
    /**
     * this for loop checks if the airports inputted exist in the current directory of airports
     */
    for (int i = 0; i < airlines.getAirportsList().size(); i++) {
      if (airlines.getAirportsList().get(i).equals(userInput)) {
        validOrigin = true;
      }
      if (airlines.getAirportsList().get(i).equals(userInput2)) {
        validDestination = true;
      }
    }
    if (validOrigin && validDestination) {
      try {
      String answer = airlines.getShortestPath(userInput, userInput2);
      System.out.println();
      System.out.println("Calculating...");
      System.out.println(answer);
      }catch (NoSuchElementException e1){
        System.out.println();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("ERROR: there is no connection between these two airports, please re-enter");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();
        calcRouteMode(flights);
      }
      System.out.println();
    } else {
      System.out.println(
          "Please re-input the origin and destination airports, airports cannot be found in directory");
      calcRouteMode(flights);
    }
    System.out.println("Press 'c' to calculate again or 'b' to exit!");
    String input = "";
    while (!input.equals("c") && !input.equals("b")) {
      input = scanner.nextLine();
      if (input.equals("c")) {
        calcRouteMode(flights);
      } else if (input.equals("b")) {
        System.out.println();
        baseMenuDisplay();
      } else {
        System.out.println();
        System.out.println("Please enter either 'c' or 'b'.");
      }
    }
  }

  /**
   * this method is designed to define the removeAirport mode which enables the user to remove any
   * airport that is currently defined in the airport directory
   * 
   * @param FlightGraph airlines
   */
  public void removeAirportMode(FlightGraph airlines) {
    String userInput;
    String userInput4 = "";
    boolean validAirport = false;
    System.out.println("Welcome to Airport Removal Mode!");
    System.out.println();
    System.out.println("Current Airports: ");
    System.out.println(showAirports(flights));

    System.out.println("Please input the name of the Airport:");
    System.out.println();
    userInput = scanner.nextLine();
    /**
     * for loop to check if the specified airport exists in the list before removing it
     */
    for (int i = 0; i < airlines.getAirportsList().size(); i++) {
      if (airlines.getAirportsList().get(i).equals(userInput)) {
        validAirport = true;
      }
    }
    if (validAirport) {
      airlines.removeAirport(userInput);
      System.out.println("Successfully removed the airport " + userInput);
      System.out.println();
    } else {
      System.out.println("The airport " + userInput + " could not be found, please re-enter");
      removeAirportMode(flights);
    }
    System.out.println("Would you like to remove another airport? (yes/no)");
    while (!userInput4.equals("yes") && !userInput4.equals("no")) {
      userInput4 = scanner.nextLine();
      if (userInput4.equals("yes")) {
        System.out.println();
        removeAirportMode(flights);
      } else if (userInput4.equals("no")) {
        System.out.println();
        baseMenuDisplay();
      } else {
        System.out.println();
        System.out.println("Please enter either 'yes' or 'no'.");
      }
    }
  }
}
