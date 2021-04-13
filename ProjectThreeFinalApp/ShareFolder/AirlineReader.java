import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

// --== CS400 File Header Information ==--
// Name: Brendan Chang
// Email: bchang32@wisc.edu
// Team: Red
// Role: Front End Developer
// TA: Xinyi Ju
// Lecturer: Florian Heimerl
// Notes to Grader:

public class AirlineReader implements AirlineReaderInterface {
  /**
   * This method returns a String Arraylist of all the airports in the graph
   * 
   * @author Brendan Chang
   * @param inputFileReader is the file we are reading through (connections.csv)
   */
  @Override
  public ArrayList<String> getAllAirports(Reader inputFileReader)
      throws IOException, DataFormatException {
    ArrayList<String> airports = new ArrayList<String>();
    BufferedReader reader = new BufferedReader(inputFileReader);

    try {
      reader.readLine();
    } catch (IOException e1) {
      throw new IOException("Error: file cannot be opened or read!");
    }
    int lineNumber = 1;
    String line;
    // Parses each line
    while ((line = reader.readLine()) != null) {
      lineNumber++;
      String[] split = line.split(",");
      // Makes sure it has more than 2 commas, which are first two values
      if (split.length < 2) {
        throw new DataFormatException(
            "ERROR: Line number " + lineNumber + " has incorrect format.");
      }
      String newAirport = new String(split[0]);
      boolean check = true;
      // Can only be one airline
      for (int j = 0; j < airports.size(); j++) {
        if (airports.get(j).equals(newAirport)) {
          check = false;
        }
      }
      if (check == true) {
        airports.add(newAirport);
      }
    }
    return airports;
  }

  /**
   * This method returns an Airline Arraylist of all the airlines in the graph
   * 
   * @author Brendan Chang
   * @param inputFileReader is the file we are reading through (connections.csv)
   */
  @Override
  public ArrayList<Airline> getAllAirlines(Reader inputFileReader)
      throws IOException, DataFormatException {
    ArrayList<Airline> airlines = new ArrayList<Airline>();
    BufferedReader reader = new BufferedReader(inputFileReader);

    try {
      reader.readLine();
    } catch (IOException e1) {
      throw new IOException("Error: file cannot be opened or read!");
    }
    int lineNumber = 1;
    String line;
    // Parses each line
    while ((line = reader.readLine()) != null) {
      lineNumber++;
      String[] split = line.split(",");
      // Makes sure it has more than 2 commas, which are first two values
      if (split.length < 2) {
        throw new DataFormatException(
            "ERROR: Line number " + lineNumber + " has incorrect format.");
      }
      Airline newAirline = new Airline(split[0], split[1], Integer.valueOf(split[2]));
      boolean check = true;
      // Can only be one airline
      for (int j = 0; j < airlines.size(); j++) {
        if (airlines.get(j).equals(newAirline)) {
          check = false;
        }
      }
      if (check == true) {
        airlines.add(newAirline);
      }
    }
    return airlines;
  }
}
