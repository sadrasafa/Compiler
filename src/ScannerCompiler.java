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
    String lastToken = "";
    InputStream inputStream;
    Charset charset = Charset.defaultCharset();
    Reader buffer, reader;

    ArrayList<Character> separators = new ArrayList<>();

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

        separators.add(';');
        separators.add(',');
        separators.add('}');
        separators.add('{');
        separators.add('(');
        separators.add(')');
        separators.add('[');
        separators.add(']');
        separators.add('+');
        separators.add('-');
        separators.add('*');
        separators.add('/');
        separators.add('&');
        separators.add('=');
        separators.add('<');
        separators.add(' ');
        separators.add('\n');
        separators.add((char) 13);

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

        for (int i = 0; i < code.length(); i++) {
            System.out.println((int) code.charAt(i));
        }

    }


    private char readChar() throws IOException {
        char toReturn = code.charAt(lookahead);
        lookahead++;
        return toReturn;
    }


    public String getToken() throws IOException {

//        System.out.println("lookahead = " + lookahead);
        String token = null;
        System.out.println("lastToken = " + lastToken);
        String returningToken = "";


        char inputChar;

        while (Character.isWhitespace(inputChar = readChar())) ;
        char nextChar;

        if (separators.contains(inputChar)) {
            switch (inputChar) {
                case '+':
                    if(lastToken.equals("ID") ||
                            lastToken.equals("NUM") ||
                            lastToken.equals("RIGHTPAR") ||
                            lastToken.equals("RIGHTBRACKET")) {
                        returningToken = "PLUS"; // TODO: 04/07/17 -5 + 3 -> MIUNUS 5 PLUS 3 :\
                    }
                    break;
                case '-':
                    if(lastToken.equals("ID") ||
                            lastToken.equals("NUM") ||
                            lastToken.equals("RIGHTPAR") ||
                            lastToken.equals("RIGHTBRACKET")) {
                        returningToken = "MINUS"; // TODO: 04/07/17 -5 + 3 -> MIUNUS 5 PLUS 3 :\
                    }
                    break;

                case '(':
                    returningToken = "LEFTPAR";
                    break;
                case ')':
                    returningToken = "RIGHTPAR";
                    break;
                case '[':
                    returningToken = "LEFTBRACKET";
                    break;
                case ']':
                    returningToken = "RIGHTBRACKET";
                    break;
                case '{':
                    returningToken = "LEFTAK";
                    break;
                case '}':
                    returningToken = "RIGHTAK";
                    break;
                case '*':
                    returningToken = "TIMES";
                    break;
                case '/':
                    nextChar = readChar();
                    if (nextChar == '*') {
//                        boolean found = false;
                        while (true) {
                            while ((readChar()) != '*') ;
                            if ((readChar()) == '/') {
                                return getToken();
                            }
                        }
                    } else {
                        lookahead--;
                        returningToken = "DIVIDED";
                    }
                    break;
                case ';':
                    returningToken = "SEMICOLON";
                    break;
                case ',':
                    returningToken = "COMMA";
                    break;
                case '<':
                    returningToken = "LESSTHAN";
                    break;
                case '&':
                    nextChar = readChar();
                    if (nextChar == '&')
                        returningToken = "AND";
                    else {
                        // TODO: 04/07/17 ERROR! No & operator!
                    }
                    break;
                case '=':
                    nextChar = readChar();
                    if (nextChar == '=')
                        returningToken = "EQUALS";
                    else {
                        lookahead--;
                        returningToken = "ASSIGNMENT";
                    }
                    break;
            }

        }
        if(returningToken.equals("")) {

            //not keyword
            System.out.println("ENTERED");
            if (Character.isLetter(inputChar)) {
                lookahead--;
                token = getVar();
            } else if (Character.isDigit(inputChar) || inputChar == '-' || inputChar == '+') {
                lookahead--;
                token = getNum();
            }
//        token = token.substring(0, token.length() - 1);

            System.out.println("token = " + token);

            if (token.matches("[a-zA-Z][\\w\\d]*")) {

                if (token.equals("if")) {
                    returningToken = "IF";
                } else if (token.equals("else")) {
                    returningToken = "ELSE";
                } else if (token.equals("while")) {
                    returningToken = "WHILE";
                } else if (token.equals("void")) {
                    returningToken = "VOID";
                } else if (token.equals("int")) {
                    returningToken = "INT";
                } else if (token.equals("return")) {
                    returningToken = "RETURN";
                } else if (token.equals("EOF")) {
                    returningToken = "EOF";
                } else {
                    returningToken = "ID";
                }

            } else if (token.matches("[+-]?\\d+")) {
                returningToken = "NUM";
            } else {
                // TODO: 04/07/17 ERROR!!
            }


        }
        if (token != null && token.equals(""))
            returningToken = getToken();

        lastToken = returningToken;
        return returningToken;
    }

    private String getVar() throws IOException {

        String token = "";
        char inputChar;

        inputChar = readChar();

        while (lookahead < code.length() && !separators.contains(inputChar)) {


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
        boolean sign = false;

        inputChar = readChar();

        if(inputChar == '-' || inputChar == '+'){
            sign = true;
        }

        while (lookahead < code.length() && (!separators.contains(inputChar) || sign)) {

            sign = false;
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
