import java.util.List;

public interface BackendInterface {
  public String getShortestPath(String orgin, String destination);
  public boolean addAirport(String airport);
  public boolean addAirline(String orgin, String destination, int distance);
  public boolean removeAirport(String airport);
  public boolean removeAirline(String orgin, String destination);
}
