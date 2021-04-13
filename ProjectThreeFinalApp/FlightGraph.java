// --== CS400 File Header Information ==--
// Name: Jackson Camp
// Email: jecamp@wisc.edu
// Team: Red
// Role: Front End Developer
// TA: Xinyi Ju
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * This class implements the back end for the Flight Scheduler project
 * 
 * @author jacksoncamp
 *
 */
public class FlightGraph implements BackendInterface {
  private CS400Graph<String> airportsGraph;
  private List<String> airportsList;
  private List<Airline> airlinesList;


  /**
   * This method uses the array in the CSV file and reads each index
   * 
   * @param args is the array of CSV files
   * @throws FileNotFoundException thrown if file not found
   */
  public FlightGraph(String[] args) throws FileNotFoundException {
    this(new FileReader("connections.csv"),
        new FileReader("connections.csv"));
  }

  /**
   * This method instantiates the filereader that the data wrangler creates
   * 
   * @param inputReader  filereader that reads the CSV file
   * @param inputReader2 rilereader that reads the CSV file
   */
  public FlightGraph(Reader inputReader, Reader inputReader2) {
    AirlineReader reader = new AirlineReader();
    AirlineReader reader2 = new AirlineReader();
    try {
      airportsList = reader.getAllAirports(inputReader);
      airlinesList = reader2.getAllAirlines(inputReader2);
    } catch (DataFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    airportsGraph();
  }

  /**
   * This method inserts all the airports/vertexes and airlines/edges into the graph
   * 
   */
  private void airportsGraph() {
    airportsGraph = new CS400Graph<String>();

    // add all airport vertices to graph
    for (String airport : airportsList) {
      airportsGraph.insertVertex(airport);
    }

    // add all airline edges to graph
    for (Airline airline : airlinesList) {
      airportsGraph.insertEdge(airline.getStart(), airline.getEnd(), airline.getDistance());
    }
  }

  /**
   * This method returns a string which contains the name of airports and the total distance of the
   * shortest path between two airports
   * 
   * @param orgin       String of the origin airport
   * @param destination String of the destination airport
   * @return a string which contains the name of airports and the total distance of the shortest
   *         path between two airports
   */
  public String getShortestPath(String orgin, String destination) {
    int distance = airportsGraph.getPathCost(orgin, destination);
    String airports = airportsGraph.shortestPath(orgin, destination).toString();

    return "Airports: " + airports + " | Distance: " + distance;
  }


  /**
   * This method adds an airport/vertex to the graph
   * 
   * @param airport String of the airport we are adding
   * @return true or false depending on if the airport was correctly added
   */
  public boolean addAirport(String airport) {
    try {
      airportsGraph.insertVertex(airport);
      airportsList.add(airport);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * This method adds an airline/edge to the graph
   * 
   * @param orgin       String of the origin airport in airline
   * @param destination String of the destination airport in airline
   * @param distance    The int distance of the airline between the two airports
   * @return true or false depending on if the airline was correctly added
   */
  public boolean addAirline(String orgin, String destination, int distance) {
    try {
      Airline n = new Airline(orgin, destination, distance);
      airportsGraph.insertEdge(orgin, destination, distance);
      airlinesList.add(n);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * This method removes an airport/vertex from the graph
   * 
   * @param airport String of the airport we are removing
   * @return true or false depending on if the airport was removed correctly
   */
  public boolean removeAirport(String airport) {
    try {
      airportsGraph.removeVertex(airport);
      airportsList.remove(airport);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * This method removes an airline/edge from the graph
   * 
   * @param orgin       String of the origin airport of the airline
   * @param destination String of the destination airport of the airline
   * @return true or false depending on if the airport was removed correctly
   */
  public boolean removeAirline(String orgin, String destination) {
    try {
      for (int i = 0; i < airlinesList.size(); i++) {
        if (airlinesList.get(i).getStart().equals(orgin)
            && airlinesList.get(i).getEnd().equals(destination)) {
          airlinesList.remove(i);
        }
      }
      airportsGraph.removeEdge(orgin, destination);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * This method returns the list of airport strings in the graph
   * 
   * @return list of airport strings in the graph
   */
  public List<String> getAirportsList() {
    return airportsList;
  }


  /**
   * This method returns the list of airline strings in the graph
   * 
   * @return list of airline strings in the graph
   */
  public List<String> getAirlinesList() {
    List<String> list = new ArrayList<String>();

    for (Airline a : airlinesList) {
      list.add(a.toString());
    }
    return list;
  }
}


