import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by safa on 7/3/17.
 */
public class ScannerCompiler {

    String fileName;
    File file;
    InputStream inputStream;
    Charset charset = Charset.defaultCharset();
    Reader buffer, reader;
    public ScannerCompiler(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        file = new File(fileName);
        inputStream = new FileInputStream(file);
        reader = new InputStreamReader(inputStream, charset);
        buffer = new BufferedReader(reader);
    }



    public char readChar() throws IOException {
        int toRead;
        char toReturn = ' ';

        if((toRead = buffer.read()) != -1){
            toReturn = (char) toRead;
        }

        return toReturn;
    }
}
