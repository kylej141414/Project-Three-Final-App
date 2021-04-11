import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public interface AirlineReaderInterface{
    public ArrayList<String> getAllAirports(Reader inputFileReader) throws IOException, DataFormatException;
    
    public ArrayList<Airline> getAllAirlines(Reader inputFileReader) throws IOException, DataFormatException;
}
