// --== CS400 File Header Information ==--
// Name: Jackson Camp
// Email: jecamp@wisc.edu
// Team: Red
// Role: Back End Developer
// TA: Xinyi Ju
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.util.List;

/**
 * This class is the interface for the Back End of this project
 * @author jacksoncamp
 *
 */
public interface BackendInterface {
  
  /**
   * This method returns a string which contains the name of airports and the total distance of the
   * shortest path between two airports
   * 
   * @param orgin       String of the origin airport
   * @param destination String of the destination airport
   * @return a string which contains the name of airports and the total distance of the shortest
   *         path between two airports
   */
  public String getShortestPath(String orgin, String destination);
  
  /**
   * This method adds an airport/vertex to the graph
   * 
   * @param airport String of the airport we are adding
   * @return true or false depending on if the airport was correctly added
   */
  public boolean addAirport(String airport);
  
  /**
   * This method adds an airline/edge to the graph
   * 
   * @param orgin       String of the origin airport in airline
   * @param destination String of the destination airport in airline
   * @param distance    The int distance of the airline between the two airports
   * @return true or false depending on if the airline was correctly added
   */
  public boolean addAirline(String orgin, String destination, int distance);
  
  /**
   * This method removes an airport/vertex from the graph
   * 
   * @param airport String of the airport we are removing
   * @return true or false depending on if the airport was removed correctly
   */
  public boolean removeAirport(String airport);
  
  /**
   * This method removes an airline/edge from the graph
   * 
   * @param orgin       String of the origin airport of the airline
   * @param destination String of the destination airport of the airline
   * @return true or false depending on if the airport was removed correctly
   */
  public boolean removeAirline(String orgin, String destination);
}
