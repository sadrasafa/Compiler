import com.sun.xml.internal.bind.v2.TODO;

import java.io.*;
import java.nio.charset.Charset;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;

/**
 * Created by safa on 7/3/17.
 */
public class ScannerCompiler {

    // TODO: 04/07/17 Make symbol table!!!!

    String fileName;
    File file;
    InputStream inputStream;
    Charset charset = Charset.defaultCharset();
    Reader buffer, reader;

    ArrayList<Character> seperators = new ArrayList<>();

    String code = "";

    int lookahead;

    public ScannerCompiler(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        file = new File(fileName);
        inputStream = new FileInputStream(file);
        reader = new InputStreamReader(inputStream, charset);
        buffer = new BufferedReader(reader);

        lookahead = 0;

        try {
            int toRead;
            char readChar = ' ';

            while ((toRead = buffer.read()) != -1) {
                readChar = (char) toRead;
                code += readChar;
            }
        } catch (IOException e) {
            System.out.println("No file found!");
        }

        seperators.add(';');
        seperators.add(',');
        seperators.add('}');
        seperators.add('{');
        seperators.add('(');
        seperators.add(')');
        seperators.add('[');
        seperators.add(']');
        seperators.add('+');
        seperators.add('-');
        seperators.add('*');
        seperators.add('/');
        seperators.add('&');
        seperators.add('$');
        seperators.add('=');
        seperators.add(' ');

        System.out.println("code = " + code);

    }


    private char readChar() throws IOException {
        char toReturn = code.charAt(lookahead);
        lookahead++;
        return toReturn;
    }


    public String getToken() throws IOException {

//        System.out.println("lookahead = " + lookahead);
        String token = "";



        char inputChar;

        while (Character.isWhitespace(inputChar = readChar())) ;
        char nextChar;

        if (seperators.contains(inputChar)) {
            switch (inputChar) {
                case '+':
                    return "PLUS";

                case '-':
                    return "MINUS"; // TODO: 04/07/17 -5 + 3 -> MIUNUS 5 PLUS 3 :\

                case '(':
                    return "LEFTPAR";

                case ')':
                    return "RIGHTPAR";

                case '[':
                    return "LEFTBRACKET";

                case ']':
                    return "RIGHTBRACKET";

                case '{':
                    return "LEFTAK";

                case '}':
                    return "RIGHTAK";

                case '*':
                    return "TIMES";

                case '/':
                    return "DIVIDED";

                case ';':
                    return "SEMICOLON";

                case ',':
                    return "COMMA";

                case '&':
                    nextChar = readChar();
                    if (nextChar == '&')
                        return "AND";
                    else {
                        // TODO: 04/07/17 ERROR! No & operator!
                    }
                    break;
                case '=':
                    nextChar = readChar();
                    if (nextChar == '=')
                        return "EQUALS";
                    else {
                        lookahead--;
                        return "ASSIGNMENT";
                    }
            }

        }

        //not keyword

        while (lookahead < code.length() && !seperators.contains(inputChar)) {
            token += inputChar;
            inputChar = readChar();
        }
        lookahead--;

//        token = token.substring(0, token.length() - 1);

        System.out.println("token = " + token);

        if (token.matches("[a-zA-Z][\\w\\d]*")) {

            if (token.equals("if")) {
                return "IF";
            } else if (token.equals("else")) {
                return "ELSE";
            } else if(token.equals("EOF")){
                return "EOF";
            }

            return "ID";

        } else if (token.matches("[+-]?\\d+")) {
            return "NUM";
        } else {
            // TODO: 04/07/17 ERROR!!
        }

        /*
        if (Character.isLetter(inputChar)) {
            while (!seperators.contains(inputChar)) {
                token += inputChar;
                inputChar = readChar();
            }
            if (lookahead > 0)
                lookahead--;
        } else if (Character.isDigit(inputChar)) {
            while
        }
        */
        return null;

    }

}
