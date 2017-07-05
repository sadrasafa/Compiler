import java.io.*;
import java.nio.charset.Charset;
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

    ArrayList<Character> invalidInVarOrNum = new ArrayList<>();

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
        seperators.add('=');
        seperators.add('<');
        seperators.add(' ');

        invalidInVarOrNum.add('@');
        invalidInVarOrNum.add('$');
        invalidInVarOrNum.add('!'); // what about '!=' ?
        invalidInVarOrNum.add('%');
        invalidInVarOrNum.add('^');
        invalidInVarOrNum.add('&');          //no rightBracket
        invalidInVarOrNum.add('{');
        invalidInVarOrNum.add('}');
        invalidInVarOrNum.add('~');


        System.out.println("code = " + code);

    }


    private char readChar() throws IOException {
        char toReturn = code.charAt(lookahead);
        lookahead++;
        return toReturn;
    }


    public String getToken() throws IOException {

        System.out.println("lookahead = " + lookahead);
        String token = null;


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

                case '<':
                    return "LESSTHAN";

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

        if (Character.isLetter(inputChar)) {
            lookahead--;
            token = getVar();
        } else if (Character.isDigit(inputChar)) {
            lookahead--;
            token = getNum();
        }
//        token = token.substring(0, token.length() - 1);

        System.out.println("token = " + token);

        if (token.matches("[a-zA-Z][\\w\\d]*")) {

            if (token.equals("if")) {
                return "IF";
            } else if (token.equals("else")) {
                return "ELSE";
            } else if (token.equals("while")) {
                return "WHILE";
            } else if (token.equals("void")) {
                return "VOID";
            } else if (token.equals("int")) {
                return "INT";
            } else if (token.equals("return")) {
                return "RETURN";
            } else if (token.equals("EOF")) {
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
        return token;

    }

    private String getVar() throws IOException {

        String token = "";
        char inputChar;

        inputChar = readChar();

        while (lookahead < code.length() && !seperators.contains(inputChar)) {


            token += inputChar;
            inputChar = readChar();

//            System.out.println("token temp = " + token);

            if (invalidInVarOrNum.contains(inputChar)) {
                token = "";
                inputChar = readChar();
            }
        }
        lookahead--;

        return token;
    }


    private String getNum() throws IOException {

        String token = "";
        char inputChar;
        String temp = "";

        inputChar = readChar();

        while (lookahead < code.length() && !seperators.contains(inputChar)) {


            token += inputChar;
            inputChar = readChar();
//            System.out.println("token temp = " + token);
            temp = "" + inputChar;
            if (invalidInVarOrNum.contains(inputChar) || temp.matches("[a-zA-Z]")) {
                token = "";
                inputChar = readChar();

            }

        }
        lookahead--;

        return token;
    }

}
