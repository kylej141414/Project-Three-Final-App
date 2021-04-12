import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.Enumeration;

public class FlightGraph implements BackendInterface {
  private CS400Graph<String> airportsGraph;
  private List<String> airportsList;
  private List<Airline> airlinesList;


  public FlightGraph(String[] args) throws FileNotFoundException {
    this(new FileReader("/Users/jacksoncamp/Downloads/connections.csv"), new FileReader("/Users/jacksoncamp/Downloads/connections.csv"));
  }

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

  private void airportsGraph() {
    airportsGraph = new CS400Graph<String>();

    // add all airport vertices to graph
    for (String airport : airportsList) {
      airportsGraph.insertVertex(airport);
    }

    // add all airline edges to graph
    for (Airline airline : airlinesList) {
      airportsGraph.insertEdge(airline.getStart(),airline.getEnd(),airline.getDistance());     
    }
  }

  /**
   * It will return a string which contains the name (or code) of airports and the total distance 
   */
  @Override
  public String getShortestPath(String orgin, String destination) {
    int distance = airportsGraph.getPathCost(orgin, destination);
    String airports = airportsGraph.shortestPath(orgin,destination).toString();
     
    return "Airports: " + airports + "Distance: " + distance;
  }

  @Override
  public boolean addAirport(String airport) {
    try {
      airportsGraph.insertVertex(airport);
      airportsList.add(airport);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean addAirline(String orgin, String destination, int distance) {
    try {
      Airline n = new Airline(orgin, destination, distance);
      airportsGraph.insertEdge(orgin, destination, distance);
      airlinesList.add(n);
      return true;
    }
    catch(Exception e) {
      return false;
    }
  }

  @Override
  public boolean removeAirport(String airport) {
    try {
      airportsGraph.removeVertex(airport);
      airportsList.remove(airport);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean removeAirline(String orgin, String destination) {
    try {
      for(int i = 0; i < airlinesList.size(); i++) {
        if(airlinesList.get(i).getStart().equals(orgin) && airlinesList.get(i).getEnd().equals(destination)) {
          airlinesList.remove(i);
        }
      }
      airportsGraph.removeEdge(orgin, destination);
      return true;
    }
    catch(Exception e) {
      return false;
    }
  }

  public List<String> getAirportsList() {
    return airportsList;
  }


  public List<Airline> getAirlinesList() {
    return airlinesList;
  }

}
