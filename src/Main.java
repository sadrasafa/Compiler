import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

/**
 * Created by safa on 7/3/17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "";
        Scanner scanner = new Scanner(System.in);
        filePath = scanner.nextLine();
        ScannerCompiler myScanner = new ScannerCompiler(filePath);
        char a;
        String token = "";
        while (!(token = myScanner.getToken()).equals("EOF")) {
            System.out.println(token + " ");
        }

        Parser parser = new Parser();
    }
}
