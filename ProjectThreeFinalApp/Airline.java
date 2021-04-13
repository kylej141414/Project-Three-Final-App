// --== CS400 File Header Information ==--
// Name: Brendan Chang
// Email: bchang32@wisc.edu
// Team: Red
// Role: Front End Developer
// TA: Xinyi Ju
// Lecturer: Florian Heimerl
// Notes to Grader:

public class Airline {
  private String start;
  private String end;
  private int distance;

  /**
   * This is the constructor for the Airline Class
   * 
   * @author Brendan Chang
   * @param start    is the starting point of the airline
   * @param end      is the ending point of the airline
   * @param distance is the distance from the starting and ending airline
   */
  public Airline(String start, String end, int distance) {
    this.start = start;
    this.end = end;
    this.distance = distance;
  }

  /**
   * This method returns the starting airline
   * 
   * @author Brendan Chang
   */
  public String getStart() {
    return this.start;
  }

  /**
   * This method returns the ending airline
   * 
   * @author Brendan Chang
   */
  public String getEnd() {
    return this.end;
  }

  /**
   * This method returns the distance between the two airlines
   * 
   * @author Brendan Chang
   */
  public int getDistance() {
    return this.distance;

  }
  
  /**
   * 
   */
  public String toString() {
    return start + " --> " + end + " | Distance: " + distance;
  }
}
