import org.junit.jupiter.api.Test;
import java.io.StringReader;
import java.util.List;
import static org.junit.Assert.*;

// --== CS400 File Header Information ==--
// Name: Brendan Chang
// Email: bchang32@wisc.edu
// Team: Red
// Role: Front End Developer
// TA: Xinyi Ju
// Lecturer: Florian Heimerl
// Notes to Grader:

public class DataWranglerTests {
  /**
   * Tests if the getAllAirports method returns the correct number of airports
   */
  @Test
  public void testGetAllAirportsSize() {
    try {
      AirlineReader readerToTest = new AirlineReader();
      List<String> listAirports = readerToTest.getAllAirports(new StringReader(
          "Airplane 1, Airplane 2, Distance\n" + "A, B,1\n" + "C, D,2\n" + "E, F,3\n"));
      assertEquals(listAirports.size(), 3);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests if the getAllAirlines method returns the correct number of airlines
   */
    @Test
  public void testGetAllAirlinesSize() {
    try {
      AirlineReader readerToTest = new AirlineReader();
      List<Airline> listAirlines =
          readerToTest.getAllAirlines(new StringReader("Airplane 1, Airplane 2, Distance\n"
              + "A, B,1\n" + "C, D,2\n" + "E, F,3\n" + "G, H,4\n"));
      assertEquals(listAirlines.size(), 4);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests if the getStart method in the Airline class returns the correct starting airport
   */
  @Test
  public void testGetStart() {
    try {
      Airline airlineToTest = new Airline("A", "B",10);
      assertEquals(airlineToTest.getStart(), "A");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests if the getEnd method in the Airline class returns the correct ending airport
   */
    @Test
  public void testGetEnd() {
    try {
      Airline airlineToTest = new Airline("A", "B",10);
      assertEquals(airlineToTest.getEnd(), "B");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests if the getDistance method in the Airline class returns the correct distance
   */
  @Test
  public void testGetDistance() {
    try {
      Airline airlineToTest = new Airline("A", "B", 10);
      assertEquals(airlineToTest.getDistance(), 10);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
