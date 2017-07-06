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

    static SymbolTable symbolTable;

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

        symbolTable = new SymbolTable();

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
    }


    private char readChar() throws IOException {
        char toReturn = code.charAt(lookahead);
        lookahead++;
        return toReturn;
    }


    public Token getToken() throws IOException {

//        System.out.println("lookahead = " + lookahead);
        String token = null;
        int attr = 0;
//        System.out.println("lastToken = " + lastToken);
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
                            lastToken.equals("RIGHTBRACKET") && Character.isDigit(readChar())) {
//                        returningToken = "PLUS";
                        return new Token("PLUS", 0);
                    }
                    break;
                case '-':
                    if(lastToken.equals("ID") ||
                            lastToken.equals("NUM") ||
                            lastToken.equals("RIGHTPAR") ||
                            lastToken.equals("RIGHTBRACKET")) {
//                        returningToken = "MINUS";
                        return new Token("MINUS", 0);

                    }
                    break;

                case '(':
                    return new Token("LEFTPAR", 0);
//                returningToken = "LEFTPAR";
//                    break;
                case ')':
                    return new Token("RIGHTPAR", 0);
//                    returningToken = "RIGHTPAR";
//                    break;
                case '[':
                    return new Token("LEFTBRACKET", 0);
//                    returningToken = "LEFTBRACKET";
//                    break;
                case ']':
                    return new Token("RIGHTBRACKET", 0);
//                    returningToken = "RIGHTBRACKET";
//                    break;
                case '{':
                    return new Token("LEFTAK", 0);
//                    returningToken = "LEFTAK";
//                    break;
                case '}':
                    return new Token("RIGHTAK", 0);
//                    returningToken = "RIGHTAK";
//                    break;
                case '*':
                    return new Token("TIMES", 0);
//                    returningToken = "TIMES";
//                    break;
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
                        return new Token("DIVIDED", 0);
//                        returningToken = "DIVIDED";
                    }
//                    break;
                case ';':
                    return new Token("SEMICOLON", 0);
//                    returningToken = "SEMICOLON";
//                    break;
                case ',':
                    return new Token("COMMA", 0);
//                    returningToken = "COMMA";
//                    break;
                case '<':
                    return new Token("LESSTHAN", 0);
//                    returningToken = "LESSTHAN";
//                    break;
                case '&':
                    nextChar = readChar();
                    if (nextChar == '&')
                        return new Token("AND", 0);
//                        returningToken = "AND";
                    else {
                        // TODO: 04/07/17 ERROR! No & operator!
                    }
                    break;
                case '=':
                    nextChar = readChar();
                    if (nextChar == '=')
                        return new Token("EQUALS", 0);
//                        returningToken = "EQUALS";
                    else {
                        lookahead--;
                        return new Token("ASSIGNMENT", 0);
//                        returningToken = "ASSIGNMENT";
                    }
//                    break;
            }

        }
        if(returningToken.equals("")) {

            //not keyword
//            System.out.println("ENTERED");
            if (Character.isLetter(inputChar)) {
                lookahead--;
                token = getVar();
            } else if (Character.isDigit(inputChar) || inputChar == '-' || inputChar == '+') {
                lookahead--;
                token = getNum();
            }
//        token = token.substring(0, token.length() - 1);

//            System.out.println("token = " + token);

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
                return new Token(returningToken, Integer.parseInt(token));
            } else {
                // TODO: 04/07/17 ERROR!!
            }


        }
        if (token != null && token.equals(""))
            return getToken();


        if(token != null && !token.equals("") && !returningToken.equals("NUM")){
            attr = symbolTable.check(token, returningToken);
        }


        lastToken = returningToken;
        return new Token(returningToken, attr);
//        return returningToken;
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
