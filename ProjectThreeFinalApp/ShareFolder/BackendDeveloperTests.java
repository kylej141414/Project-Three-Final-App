// --== CS400 File Header Information ==--
// Name: Jackson Camp
// Email: jecamp@wisc.edu
// Team: Red
// Role: Back End Developer
// TA: Xinyi Ju
// Lecturer: Florian Heimerl
// Notes to Grader:

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.fail;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;

/**
 * This class tests the FlightGraph.java class and tests the implementation of the addAirport,
 * removeAirport, addAirline, removeAirline, and getShortestPath
 * 
 * @author jacksoncamp
 *
 */
public class BackEndDeveloperTests {

  /**
   * This method tests the implementation of the addAirport method by adding three airports to the
   * graph and checking to see if they were added correctly to the graph.
   */
  @Test
  public void testAddAirport() {
    String input = "Airport1,Airport2,Distance\n";
    StringReader a = new StringReader(input);
    StringReader b = new StringReader(input);
    FlightGraph graph = new FlightGraph(a, b);

    // add airport 1
    graph.addAirport("John F. Kennedy International Airport");
    if (!graph.getAirportsList().get(0).equals("John F. Kennedy International Airport")) {
      fail("Failure: Airport 1 not added correctly");
    }

    // add airport 2
    graph.addAirport("O'Hare International Airport");
    if (!graph.getAirportsList().get(1).equals("O'Hare International Airport")) {
      fail("Failure: Airport 2 not added correctly");
    }

    // add airport 3
    graph.addAirport("San Francisco International Airport");
    if (!graph.getAirportsList().get(2).equals("San Francisco International Airport")) {
      fail("Failure: Airport 3 not added correctly");
    }
  }

  /**
   * This method tests the implementation of the removeAirport method by adding three airports to
   * the graph and then removing them one by one until there is none left. Each time an airport is
   * removed from the graph this method checks to see if the list of airports in the graph is
   * correct.
   */
  @Test
  public void testRemoveAirport() {
    String input = "Airport1,Airport2,Distance\n";
    StringReader a = new StringReader(input);
    StringReader b = new StringReader(input);
    FlightGraph graph = new FlightGraph(a, b);

    // add 3 airports
    graph.addAirport("John F. Kennedy International Airport");
    graph.addAirport("O'Hare International Airport");
    graph.addAirport("San Francisco International Airport");

    // remove airport 1
    graph.removeAirport("John F. Kennedy International Airport");
    if (!graph.getAirportsList().get(0).equals("O'Hare International Airport")
        || !graph.getAirportsList().get(1).equals("San Francisco International Airport")
        || graph.getAirportsList().size() != 2) {
      fail("Failure: Airport 1 not removed correctly");
    }

    // remove airport 2
    graph.removeAirport("O'Hare International Airport");
    if (!graph.getAirportsList().get(0).equals("San Francisco International Airport")
        || graph.getAirportsList().size() != 1) {
      fail("Failure: Airport 2 not removed correctly");
    }

    // remove airport 3
    graph.removeAirport("San Francisco International Airport");
    if (graph.getAirportsList().size() != 0) {
      fail("Failure: Airport 3 not removed correctly");
    }
  }

  /**
   * This method tests the implementation of the addAirline method by adding three airports and
   * three airlines to the graph and then checking to see if they were correctly added to the graph
   */
  @Test
  public void testAddAirline() {
    String input = "Airport1,Airport2,Distance\n";
    StringReader a = new StringReader(input);
    StringReader b = new StringReader(input);
    FlightGraph graph = new FlightGraph(a, b);

    // add 3 airports
    graph.addAirport("John F. Kennedy International Airport");
    graph.addAirport("O'Hare International Airport");
    graph.addAirport("San Francisco International Airport");

    // add 3 airlines
    graph.addAirline("John F. Kennedy International Airport", "O'Hare International Airport", 5);
    graph.addAirline("San Francisco International Airport", "John F. Kennedy International Airport",
        10);
    graph.addAirline("San Francisco International Airport", "O'Hare International Airport", 4);

    if (!graph.getAirlinesList().get(0).equals(
        "John F. Kennedy International Airport --> O'Hare International Airport | Distance: 5")) {
      fail("Failure: Airline 1 not added correctly");
    }
    if (!graph.getAirlinesList().get(1).equals(
        "San Francisco International Airport --> John F. Kennedy International Airport | Distance: 10")) {
      fail("Failure: Airline 2 not added correctly");
    }
    if (!graph.getAirlinesList().get(2).equals(
        "San Francisco International Airport --> O'Hare International Airport | Distance: 4")) {
      fail("Failure: Airline 3 not added correctly");
    }

  }

  /**
   * This method tests the implementation of the removeAirline method by adding three airports and
   * airlines to the graph and then removing the airlines one by one until there is none left. Each
   * time an airport is removed from the graph this method checks to see if the list of airports in
   * the graph is correct.
   */
  @Test
  public void testRemoveAirline() {
    String input = "Airport1,Airport2,Distance\n";
    StringReader a = new StringReader(input);
    StringReader b = new StringReader(input);
    FlightGraph graph = new FlightGraph(a, b);

    // add 3 airports
    graph.addAirport("John F. Kennedy International Airport");
    graph.addAirport("O'Hare International Airport");
    graph.addAirport("San Francisco International Airport");

    // add 3 airlines
    graph.addAirline("John F. Kennedy International Airport", "O'Hare International Airport", 5);
    graph.addAirline("San Francisco International Airport", "John F. Kennedy International Airport",
        10);
    graph.addAirline("San Francisco International Airport", "O'Hare International Airport", 4);

    // remove airline 1
    graph.removeAirline("John F. Kennedy International Airport", "O'Hare International Airport");
    if (!graph.getAirlinesList().get(0).equals(
        "San Francisco International Airport --> John F. Kennedy International Airport | Distance: 10")
        || graph.getAirlinesList().size() != 2) {
      fail("Failure 1: Airline 1 not removed correctly");
    }
    if (!graph.getAirlinesList().get(1).equals(
        "San Francisco International Airport --> O'Hare International Airport | Distance: 4")
        || graph.getAirlinesList().size() != 2) {
      fail("Failure 2: Airline 1 not removed correctly");
    }

    // remove airline 2
    graph.removeAirline("San Francisco International Airport",
        "John F. Kennedy International Airport");
    if (!graph.getAirlinesList().get(0).equals(
        "San Francisco International Airport --> O'Hare International Airport | Distance: 4")
        || graph.getAirlinesList().size() != 1) {
      fail("Failure 2: Airline 1 not removed correctly");
    }

    // remove airline 3
    graph.removeAirline("San Francisco International Airport", "O'Hare International Airport");
    if (graph.getAirlinesList().size() != 0) {
      fail("Failure: Airlines not removed correctly");
    }
  }

  /**
   * This method tests the implementation of the getShortestPath method by adding 8 airports and 9
   * airline connections between them in the graph. From airport 4 the shortest paths to all of the
   * remaining airports with a connection are checked. The list of airports and the cost of each
   * shortest path are checked.
   */
  @Test
  public void testGetShortestPath() {
    String input = "Airport1,Airport2,Distance\n";
    StringReader a = new StringReader(input);
    StringReader b = new StringReader(input);
    FlightGraph graph = new FlightGraph(a, b);
    graph.addAirport("0");
    graph.addAirport("1");
    graph.addAirport("2");
    graph.addAirport("3");
    graph.addAirport("4");
    graph.addAirport("5");
    graph.addAirport("6");
    graph.addAirport("7");

    graph.addAirline("2", "1", 4);
    graph.addAirline("4", "2", 9);
    graph.addAirline("3", "1", 3);
    graph.addAirline("1", "6", 8);
    graph.addAirline("5", "6", 7);
    graph.addAirline("6", "5", 1);
    graph.addAirline("3", "7", 9);
    graph.addAirline("4", "7", 1);
    graph.addAirline("3", "5", 6);

    // check path from 4 to 1
    if (!graph.getShortestPath("4", "1").equals("Airports: [4, 2, 1] | Distance: 13")) {
      fail("Failure: Shortest path from 4 to 0 not correct");
    }
    // check path from 4 to 2
    if (!graph.getShortestPath("4", "2").equals("Airports: [4, 2] | Distance: 9")) {
      fail("Failure: Shortest path from 4 to 2 not correct");
    }

    // check path from 4 to 4
    if (!graph.getShortestPath("4", "4").equals("Airports: [4] | Distance: 0")) {
      fail("Failure: Shortest path from 4 to 4 not correct");
    }
    // check path from 4 to 5
    if (!graph.getShortestPath("4", "5").equals("Airports: [4, 2, 1, 6, 5] | Distance: 22")) {
      fail("Failure: Shortest path from 4 to 5 not correct");
    }
    // check path from 4 to 6
    if (!graph.getShortestPath("4", "6").equals("Airports: [4, 2, 1, 6] | Distance: 21")) {
      fail("Failure: Shortest path from 4 to 6 not correct");
    }
    // check path from 4 to 7
    if (!graph.getShortestPath("4", "7").equals("Airports: [4, 7] | Distance: 1")) {
      fail("Failure: Shortest path from 4 to 7 not correct");
    }

  }
}


