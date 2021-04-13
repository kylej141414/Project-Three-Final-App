// --== CS400 File Header Information ==--
// Name: Kyle Sung
// Email: kesung2@wisc.edu
// Team: Red
// Role: Front End Developer
// TA: Xinyi Ju
// Lecturer: Florian Heimerl
// Notes to Grader:

import org.junit.Test;
import static org.junit.Assert.fail;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * This class tests the FrontendInterface.java class and tests the implementation of the frontend
 * interface
 * 
 * @author kyle sung
 *
 */
public class TestFrontend {

  /**
   * This test tests if the frontend interface will correctly enter the remove airport mode when the
   * user enters "r".
   * 
   */
  @Test
  public void test1() {
 // Runs the front end and redirects its output to a string
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      // set the input stream to our input 
      String input = "r";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));

      // instantiate when front end is implemented
      String[] example = new String[2];
      example[0] = "Kyle Airport";
      example[1] = "Luke Airport";
      FrontendInterface frontend = new FrontendInterface(new FlightGraph(example));
      frontend.baseMenu();
      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      System.out.println(appOutput);
      if (!appOutput.contains("Welcome to New Airport Insert Mode!")) {
        // test fails
        fail("Failure: Incorrect output");
      }


    } catch (Exception e) {
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      fail("Failure: Didn't terminate properly");
    }
  }

  /**
   * This test tests if the frontend interface will correctly enter the airport adding mode when
   * inputting "a"
   * 
   */
  @Test
  public void test2() {
    // Runs the front end and redirects its output to a string
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      // set the input stream to our input (with an q to test of the program exists)
      String input = "a";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));

      // instantiate when front end is implemented
      String[] example = new String[2];
      example[0] = "Kyle Airport";
      example[1] = "Luke Airport";
      FrontendInterface frontend = new FrontendInterface(new FlightGraph(example));
      frontend.baseMenu();
      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      System.out.println(appOutput);
      if (!appOutput.contains("Welcome to New Airport Insert Mode!")) {
        // test fails
        fail("Failure: Incorrect output");
      }
    } catch (Exception e) {
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      fail("Failure: Didn't terminate properly");
    }
  }

  /**
   * This test tests if the frontend interface will correctly enter the shortest path calculation
   * mode when the user enters "c".
   * 
   */
  @Test
  public void test3() {
    // Runs the front end and redirects its output to a string
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      // set the input stream to our input (with an q to test of the program exists)
      String input = "c";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));

      // instantiate when front end is implemented
      String[] example = new String[2];
      example[0] = "Kyle Airport";
      example[1] = "Luke Airport";
      FrontendInterface frontend = new FrontendInterface(new FlightGraph(example));
      frontend.baseMenu();
      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      System.out.println(appOutput);
      if (!appOutput.contains("Welcome to the Shortest Route Calculator!")) {
        // test fails
        fail("Failure: Incorrect output");
      }
    } catch (Exception e) {
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      fail("Failure: Didn't terminate properly");
    }
  }
}
