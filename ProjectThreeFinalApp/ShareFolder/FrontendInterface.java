// --== CS400 File Header Information ==--
// Name: Kyle Sung
// Email: kesung2@wisc.edu
// Team: Red
// Role: Front End Developer
// TA: Xinyi Ju
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
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
      FileReader read = new FileReader("/Users/kylesung/Downloads/connections.csv");
      final FlightGraph flights = new FlightGraph(read);
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

    System.out.println("Main Mode:");

    System.out.println("Enter 'a' to add airport");
    System.out.println("Enter 'r' to remove airport");
    System.out.println("Enter 'x' to remove airline");
    System.out.println("Enter 'c' to calculate shortest route");
    System.out.println("Enter 'q' to quit");
    if (scanner.hasNext()) {
      navigation = scanner.nextLine();
      if (navigation.equals("a")) {
        System.out.println();
        newAirportMode(flights);
      } else if (navigation.equals("r")) {
        removeAirportMode(flights);
      } else if (navigation.equals("x")) {
        removeAirline(flights);
      } else if (navigation.equals("c")) {
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

  public void addAirline(FlightGraph airports) {
    String userInput;
    String userInput2;
    int userInput3;
    String userInput4 = "";
    boolean validOrigin = false;
    boolean validDestination = false;
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
      } else if (userInput4.equals("no")) { // breaks out without going home if no
        System.out.println();
      } else {
        System.out.println();
        System.out.println("Please enter either 'yes' or 'no'.");
      }
    }
  }

  public void removeAirline(FlightGraph airports) {
    String userInput;
    String userInput2;
    String userInput4 = "";
    boolean validOrigin = false;
    boolean validDestination = false;
    System.out.println("Welcome to Airline Removal Mode!");
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
      } else if (userInput4.equals("no")) { // breaks out without going home if no
        System.out.println();
      } else {
        System.out.println();
        System.out.println("Please enter either 'yes' or 'no'.");
      }
    }
  }

  public void newAirportMode(FlightGraph airports) {
    String userInput;
    String userInput2 = "";
    System.out.println("Welcome to New Airport Insert Mode!");
    System.out.println();
    System.out.println("Current Airports: " + airports.getAirportsList().toString());
    System.out.println();

    System.out.println("Please input the name of the Airport:");
    userInput = scanner.nextLine();
    airports.addAirport(userInput);
    System.out.println();
    String input = "";
    System.out
        .println("Would you like to add an airline connection to " + userInput + "? (yes/no)");
    while (!input.equals("yes") && !input.equals("no")) {
      input = scanner.nextLine();
      if (input.equals("yes")) {
        System.out.println();
        addAirline(flights); // calls function that can repeated add multiple connections
      } else if (input.equals("no")) {
        System.out.println();
      } else {
        System.out.println();
        System.out.println("Please enter a 'yes' or 'no'.");
      }
    }
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

  public void calcRouteMode(FlightGraph airlines) {
    String userInput;
    String userInput2;
    boolean validOrigin = false;
    boolean validDestination = false;
    System.out.println("Welcome to the Shortest Route Calculator!");
    System.out.println();
    System.out.println("Current Airlines: " + airlines.getAirlinesList().toString());
    System.out.println();
    System.out.println("Current Airports: " + airlines.getAirportsList().toString());
    System.out.println();

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
      String answer = airlines.getShortestPath(userInput, userInput2);
      System.out.println();
      System.out.println("Calculating...");
      System.out.println(answer);
      System.out.println();
    } else {
      System.out.println(
          "Please re-input the origin and destination airports, current cannot be found in directory");
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

  public void removeAirportMode(FlightGraph airlines) {
    String userInput;
    boolean validAirport = false;
    System.out.println("Welcome to Airport Removal Mode!");
    System.out.println();
    System.out.println("Current Airports: " + airlines.getAirportsList().toString());
    System.out.println();

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
    } else {
      System.out.println("The airport " + userInput + " could not be found, please re-enter");
      removeAirportMode(flights);
    }
  }
}
