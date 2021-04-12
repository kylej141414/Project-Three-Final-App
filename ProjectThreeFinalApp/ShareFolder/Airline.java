public class Airline {
  private String start;
  private String end;
  private int distance;
  public Airline(String start, String end, int distance) {
      this.start = start;
      this.end = end;
      this.distance = distance;
  }   
  public String getStart() {
      return this.start;
  }
  public String getEnd() {
      return this.end;
  }
  public int getDistance() {
      return this.distance;
  }
  public String toString() {
      return start + " --> " + end + " Distance: " + distance;
  }
}
