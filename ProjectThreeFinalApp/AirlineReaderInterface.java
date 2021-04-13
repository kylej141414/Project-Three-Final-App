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

public interface AirlineReaderInterface {
  public ArrayList<String> getAllAirports(Reader inputFileReader)
      throws IOException, DataFormatException;

  public ArrayList<Airline> getAllAirlines(Reader inputFileReader)
      throws IOException, DataFormatException;
}
