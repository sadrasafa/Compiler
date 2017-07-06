import java.util.*;

/**
 * Created by safa on 7/4/17.
 */
public class Grammar {
    private ArrayList<Symbol> symbols;
    private ArrayList<Production> productions;
    private ArrayList<ItemSet> LRcollections;

    public static final Symbol symDollar = new Symbol("$", true);
    public static final Symbol symEps = new Symbol("epsilon", true);
    Symbol symS = new Symbol("S", false); //augmented start symbol should be names symS !
    public Grammar() {
//        createSimpleGrammar();
//        createAdvancedGrammar();
//        createDefaultGrammar();
        createGrammar1();
//        computeFirst();
//        computeFollow();
//        printPoxlarinMaanisi();
//        printFirst();
//        printFollow();

    }

    private void printPoxlarinMaanisi() {
        for (Symbol sym: symbols) {
            System.out.println(sym.getPox()+" : "+sym.getName());
        }
    }
    private void printFirst() {
        System.out.println("FIIIRST:");
        for (Symbol nt: getNonTerminals()) {
            System.out.print(nt.getPox()+" = "+nt.getName()+" : ");
            for (Symbol firsts: nt.getFirst()) {
                System.out.print(firsts.getPox()+" ,");
            }
            System.out.println();
        }
    }

    private void printFollow() {
        System.out.println("FOLLOWWWWWWWWW");
        for (Symbol nt: getNonTerminals()){
            System.out.print(nt.getPox()+" = "+nt.getName()+" : ");
            for (Symbol follows: nt.getFollow()) {
                System.out.print(follows.getPox()+" ,");
            }
            System.out.println();
        }
    }


    private void createGrammar1() {

        Symbol symProgram= new Symbol("Program", false);
        Symbol symDeclarationList = new Symbol("DeclarationList", false);
        Symbol symEOF = new Symbol("EOF", true);
        Symbol symDeclaration = new Symbol("Declaration", false);
        Symbol symVarDeclaration = new Symbol("VarDeclaration", false);
        Symbol symFunDeclaration = new Symbol("FunDeclaration", false);
//        Symbol symTypeSpecifier = new Symbol("TypeSpecifier", false);
        Symbol symID = new Symbol("ID", true);
        Symbol symOpenBrace = new Symbol("[", true);
        Symbol symNum = new Symbol("NUM", true);
        Symbol symCloseBrace = new Symbol("]", true);
        Symbol symInt = new Symbol("int", true);
        Symbol symFunRetType = new Symbol("FunReturnType", false);
        Symbol symParams = new Symbol("Params", false);
        Symbol symCompoundStmt = new Symbol("CompoundStmt", false);
        Symbol symVoid = new Symbol("void", true);
        Symbol symParamList = new Symbol("ParamList", false);
        Symbol symComma = new Symbol(",", true);
        Symbol symParam = new Symbol("Param", false);
        Symbol symOpenAk = new Symbol("{", true);
        Symbol symLocalDeclarations = new Symbol("LocalDeclarations", false);
        Symbol symStatementList = new Symbol("StatementList", false);
        Symbol symCloseAk = new Symbol("}", true);
        Symbol symStatement = new Symbol("Statement", false);
        Symbol symExpressionStmt = new Symbol("ExpressionStmt", false);
        Symbol symSelectionStmt = new Symbol("SelectionStmt", false);
        Symbol symIterationStmt = new Symbol("IterationStmt", false);
        Symbol symReturnStmt = new Symbol("ReturnStmt", false);
        Symbol symVar = new Symbol("Var", false);
        Symbol symAssign = new Symbol("=", true);
        Symbol symExpression = new Symbol("Expression", false);
        Symbol symSemicolon = new Symbol(";", true);
        Symbol symIf = new Symbol("if", true);
        Symbol symOpenPar = new Symbol("(", true);
        Symbol symGenExpression = new Symbol("GenExpression", false);
        Symbol symClosePar = new Symbol(")", true);
        Symbol symElse = new Symbol("else", true);
        Symbol symWhile = new Symbol("while", true);
        Symbol symReturn = new Symbol("return", true);
        Symbol symRelExpression = new Symbol("RelExpression", false);
        Symbol symAnd = new Symbol("&&", true);
        Symbol symRelTerm = new Symbol("RelTerm", false);
        Symbol symEquals = new Symbol("==", true);
        Symbol symLess = new Symbol("<", true);
        Symbol symAddOp = new Symbol("AddOp", false);
        Symbol symTerm = new Symbol("Term", false);
        Symbol symPlus = new Symbol("+", true);
        Symbol symMinus = new Symbol("-", true);
        Symbol symMulOp = new Symbol("MulOp", false);
        Symbol symTimes = new Symbol("*", true);
        Symbol symDivision = new Symbol("/", true);
        Symbol symFactor = new Symbol("Factor", false);
        Symbol symCall = new Symbol("Call", false);
        Symbol symArgs = new Symbol("args", false);
        Symbol symArgsList = new Symbol("ArgList", false);

        symbols = new ArrayList<>(Arrays.asList(symProgram, symDeclarationList, symEOF, symDeclaration, symVarDeclaration));
        symbols.addAll(new ArrayList<>(Arrays.asList(symFunDeclaration, symID, symOpenBrace, symNum, symCloseBrace,symInt)));
        symbols.addAll(new ArrayList<>(Arrays.asList(symFunRetType, symParams, symCompoundStmt, symVoid, symParamList, symComma, symParam, symOpenAk, symCloseAk)));
        symbols.addAll(new ArrayList<>(Arrays.asList(symLocalDeclarations, symStatementList, symStatement, symExpressionStmt, symSelectionStmt, symIterationStmt, symReturnStmt)));
        symbols.addAll(new ArrayList<>(Arrays.asList(symVar, symAssign, symExpression, symSemicolon, symIf, symOpenPar, symGenExpression, symClosePar)));
        symbols.addAll(new ArrayList<>(Arrays.asList(symElse, symWhile, symReturn, symRelExpression, symAnd, symRelTerm, symEquals, symLess, symAddOp)));
        symbols.addAll(new ArrayList<>(Arrays.asList(symTerm, symPlus, symMinus, symMulOp, symTimes, symDivision, symFactor, symCall, symArgs, symArgsList)));
        System.out.println("Symbols size: "+symbols.size());


        Production p0 = new Production(symS, new Symbol[]{symProgram});
        Production p1 = new Production(symProgram, new Symbol[]{symDeclarationList, symEOF});
        Production p2_1 = new Production(symDeclarationList, new Symbol[]{symDeclarationList, symDeclaration});
        Production p2_2 = new Production(symDeclarationList, new Symbol[]{symDeclaration});
        Production p3_1 = new Production(symDeclaration, new Symbol[]{symVarDeclaration});
        Production p3_2 = new Production(symDeclaration, new Symbol[]{symFunDeclaration});
        Production p4_1 = new Production(symVarDeclaration, new Symbol[]{symFunRetType, symID, symSemicolon});
        Production p4_2 = new Production(symVarDeclaration, new Symbol[]{symFunRetType, symID, symOpenBrace, symNum, symCloseBrace, symSemicolon});
//        Production p5 = new Production(symTypeSpecifier, new Symbol[]{symInt});
        Production p6 = new Production(symFunDeclaration, new Symbol[]{symFunRetType, symID, symOpenPar, symParams, symClosePar, symCompoundStmt});
        Production p7_1 = new Production(symFunRetType, new Symbol[]{symInt});
        Production p7_2 = new Production(symFunRetType, new Symbol[]{symVoid});
        Production p8_1 = new Production(symParams, new Symbol[]{symParamList});
        Production p8_2 = new Production(symParams, new Symbol[]{symVoid});
        Production p9_1 = new Production(symParamList, new Symbol[]{symParamList, symComma, symParam});
        Production p9_2 = new Production(symParamList, new Symbol[]{symParam});
        Production p10_1 = new Production(symParam, new Symbol[]{symFunRetType, symID});
        Production p10_2 = new Production(symParam, new Symbol[]{symFunRetType, symID, symOpenBrace, symCloseBrace});
        Production p11 = new Production(symCompoundStmt, new Symbol[]{symOpenAk, symLocalDeclarations, symStatementList, symCloseAk});
        Production p12_1 = new Production(symLocalDeclarations, new Symbol[]{symLocalDeclarations, symVarDeclaration});
        Production p12_2 = new Production(symLocalDeclarations);
        Production p13_1 = new Production(symStatementList, new Symbol[]{symStatementList, symStatement});
        Production p13_2 = new Production(symStatementList);
        Production p14_1 = new Production(symStatement, new Symbol[]{symExpressionStmt});
        Production p14_2 = new Production(symStatement, new Symbol[]{symCompoundStmt});
        Production p14_3 = new Production(symStatement, new Symbol[]{symSelectionStmt});
        Production p14_4 = new Production(symStatement, new Symbol[]{symIterationStmt});
        Production p14_5 = new Production(symStatement, new Symbol[]{symReturnStmt});
        Production p15_1 = new Production(symExpressionStmt, new Symbol[]{symVar, symAssign, symExpression, symSemicolon});
        Production p15_2 = new Production(symExpressionStmt, new Symbol[]{symSemicolon});
        Production p16_1 = new Production(symSelectionStmt, new Symbol[]{symIf, symOpenPar, symGenExpression, symClosePar, symStatement});
        Production p16_2 = new Production(symSelectionStmt, new Symbol[]{symIf, symOpenPar, symGenExpression, symClosePar, symStatement, symElse, symStatement});
        Production p17 = new Production(symIterationStmt, new Symbol[]{symWhile, symOpenPar, symGenExpression, symClosePar, symStatement});
        Production p18_1 = new Production(symReturnStmt, new Symbol[]{symReturn, symSemicolon});
        Production p18_2 = new Production(symReturnStmt, new Symbol[]{symReturn, symGenExpression, symSemicolon});
        Production p19_1 = new Production(symVar, new Symbol[]{symID});
        Production p19_2 = new Production(symVar, new Symbol[]{symID, symOpenBrace, symExpression, symCloseBrace});
        Production p20_1 = new Production(symGenExpression, new Symbol[]{symRelExpression});
        Production p20_2 = new Production(symGenExpression, new Symbol[]{symExpression});
        Production p21_1 = new Production(symRelExpression, new Symbol[]{symRelExpression, symAnd, symRelTerm});
        Production p21_2 = new Production(symRelExpression, new Symbol[]{symRelTerm});
        Production p22_1 = new Production(symRelTerm, new Symbol[]{symExpression, symEquals, symExpression});
        Production p22_2 = new Production(symRelTerm, new Symbol[]{symExpression, symLess, symExpression});
        Production p23_1 = new Production(symExpression, new Symbol[]{symExpression, symAddOp, symTerm});
        Production p23_2 = new Production(symExpression, new Symbol[]{symTerm});
        Production p24_1 = new Production(symAddOp, new Symbol[]{symPlus});
        Production p24_2 = new Production(symAddOp, new Symbol[]{symMinus});
        Production p25_1 = new Production(symTerm, new Symbol[]{symTerm, symMulOp, symFactor});
        Production p25_2 = new Production(symTerm, new Symbol[]{symFactor});
        Production p26_1 = new Production(symMulOp, new Symbol[]{symTimes});
        Production p26_2 = new Production(symMulOp, new Symbol[]{symDivision});
        Production p27_1 = new Production(symFactor, new Symbol[]{symOpenPar, symExpression, symClosePar});
        Production p27_2 = new Production(symFactor, new Symbol[]{symVar});
        Production p27_3 = new Production(symFactor, new Symbol[]{symCall});
        Production p27_4 = new Production(symFactor, new Symbol[]{symNum});
        Production p28 = new Production(symCall, new Symbol[]{symID, symOpenPar, symArgs, symClosePar});
        Production p29_1 = new Production(symArgs, new Symbol[]{symArgsList});
        Production p29_2 = new Production(symArgs);
        Production p30_1 = new Production(symArgsList, new Symbol[]{symArgsList, symComma, symExpression});
        Production p30_2 = new Production(symArgsList, new Symbol[]{symExpression});
        productions = new ArrayList<>(Arrays.asList(p0, p1, p2_1, p2_2, p3_1, p3_2, p4_1, p4_2, p6, p7_1, p7_2, p8_1, p8_2, p9_1, p9_2, p10_1, p10_2));
        productions.addAll(new ArrayList<>(Arrays.asList(p11, p12_1, p12_2, p13_1, p13_2, p14_1, p14_2, p14_3, p14_4, p14_5, p15_1, p15_2, p16_1, p16_2)));
        productions.addAll(new ArrayList<>(Arrays.asList(p17, p18_1, p18_2, p19_1, p19_2, p20_1, p20_2, p21_1, p21_2, p22_1, p22_2, p23_1, p23_2, p24_1, p24_2)));
        productions.addAll(new ArrayList<>(Arrays.asList(p25_1, p25_2, p26_1, p26_2, p27_1, p27_2, p27_3, p27_4, p28, p29_1, p29_2, p30_1, p30_2)));

//        printAllProductions();

        symS.setFirst(new ArrayList<>(Arrays.asList(symInt, symVoid)));
        symProgram.setFirst(new ArrayList<>(Arrays.asList(symInt, symVoid))); //A
        symDeclarationList.setFirst(new ArrayList<>(Arrays.asList(symInt, symVoid))); //B
        symDeclaration.setFirst(new ArrayList<>(Arrays.asList(symInt, symVoid))); //C
        symVarDeclaration.setFirst(new ArrayList<>(Arrays.asList(symInt, symVoid))); //D
        symFunDeclaration.setFirst(new ArrayList<>(Arrays.asList(symInt, symVoid))); //E
        symFunRetType.setFirst(new ArrayList<>(Arrays.asList(symInt, symVoid))); //F
        symParams.setFirst(new ArrayList<>(Arrays.asList(symInt, symVoid))); //G
        symCompoundStmt.setFirst(new ArrayList<>(Arrays.asList(symOpenAk))); //H
        symParamList.setFirst(new ArrayList<>(Arrays.asList(symInt, symVoid))); //I
        symParam.setFirst(new ArrayList<>(Arrays.asList(symInt, symVoid))); //J
        symLocalDeclarations.setFirst(new ArrayList<>(Arrays.asList(symEps, symInt, symVoid))); //K
        symStatementList.setFirst(new ArrayList<>(Arrays.asList(symEps, symWhile, symReturn, symID, symOpenAk, symSemicolon, symIf))); //L //L
        symStatement.setFirst(new ArrayList<>(Arrays.asList(symWhile, symReturn, symID, symOpenAk, symSemicolon, symIf))); //M
        symExpressionStmt.setFirst(new ArrayList<>(Arrays.asList(symID, symSemicolon))); //N
        symSelectionStmt.setFirst(new ArrayList<>(Arrays.asList(symIf))); //O
        symIterationStmt.setFirst(new ArrayList<>(Arrays.asList(symWhile))); //P
        symReturnStmt.setFirst(new ArrayList<>(Arrays.asList(symReturn))); //Q
        symVar.setFirst(new ArrayList<>(Arrays.asList(symID))); //R
        symExpression.setFirst(new ArrayList<>(Arrays.asList(symOpenPar, symID, symNum))); //S
        symGenExpression.setFirst(new ArrayList<>(Arrays.asList(symOpenPar, symID, symNum))); //T
        symRelExpression.setFirst(new ArrayList<>(Arrays.asList(symOpenPar, symID, symNum))); //U
        symRelTerm.setFirst(new ArrayList<>(Arrays.asList(symOpenPar, symID, symNum))); //V
        symAddOp.setFirst(new ArrayList<>(Arrays.asList(symPlus, symMinus))); //W
        symTerm.setFirst(new ArrayList<>(Arrays.asList(symOpenPar, symID, symNum))); //X
        symMulOp.setFirst(new ArrayList<>(Arrays.asList(symTimes, symDivision))); //Y
        symFactor.setFirst(new ArrayList<>(Arrays.asList(symOpenPar, symID, symNum))); //Z
        symCall.setFirst(new ArrayList<>(Arrays.asList(symID))); //@
        symArgs.setFirst(new ArrayList<>(Arrays.asList(symEps, symOpenPar, symID, symNum))); //#
        symArgsList.setFirst(new ArrayList<>(Arrays.asList(symOpenPar, symID, symNum))); //$

//        printPoxFirst();

        symS.setFollow(new ArrayList<>(Arrays.asList(symDollar)));
        symProgram.setFollow(new ArrayList<>(Arrays.asList(symDollar))); //A
        symDeclarationList.setFollow(new ArrayList<>(Arrays.asList(symEOF, symInt, symVoid))); //B
        symDeclaration.setFollow(new ArrayList<>(Arrays.asList(symEOF, symInt, symVoid))); //C
        symVarDeclaration.setFollow(new ArrayList<>(Arrays.asList(symEOF, symWhile, symReturn, symID, symInt, symVoid, symOpenAk, symCloseAk, symSemicolon, symIf))); //D
        symFunDeclaration.setFollow(new ArrayList<>(Arrays.asList(symEOF, symInt, symVoid))); //E
        symFunRetType.setFollow(new ArrayList<>(Arrays.asList(symID))); //F
        symParams.setFollow(new ArrayList<>(Arrays.asList(symClosePar))); //G
        symCompoundStmt.setFollow(new ArrayList<>(Arrays.asList(symElse, symEOF, symWhile, symReturn, symID, symInt, symVoid, symOpenAk, symCloseAk, symSemicolon, symIf))); //H
        symParamList.setFollow(new ArrayList<>(Arrays.asList(symClosePar, symComma))); //I
        symParam.setFollow(new ArrayList<>(Arrays.asList(symClosePar, symComma))); //J
        symLocalDeclarations.setFollow(new ArrayList<>(Arrays.asList(symWhile, symReturn, symID, symInt, symVoid, symOpenAk, symCloseAk, symSemicolon, symIf))); //K
        symStatementList.setFollow(new ArrayList<>(Arrays.asList(symWhile, symReturn, symID, symOpenAk, symCloseAk, symSemicolon, symIf))); //L
        symStatement.setFollow(new ArrayList<>(Arrays.asList(symElse, symWhile, symReturn, symID, symOpenAk, symCloseAk, symSemicolon, symIf))); //M
        symExpressionStmt.setFollow(new ArrayList<>(Arrays.asList(symElse, symWhile, symReturn, symID, symOpenAk, symCloseAk, symSemicolon, symIf))); //N
        symSelectionStmt.setFollow(new ArrayList<>(Arrays.asList(symElse, symWhile, symReturn, symID, symOpenAk, symCloseAk, symSemicolon, symIf))); //O
        symIterationStmt.setFollow(new ArrayList<>(Arrays.asList(symElse, symWhile, symReturn, symID, symOpenAk, symCloseAk, symSemicolon, symIf))); //P
        symReturnStmt.setFollow(new ArrayList<>(Arrays.asList(symElse, symWhile, symReturn, symID,  symOpenAk, symCloseAk, symSemicolon, symIf))); //Q
        symVar.setFollow(new ArrayList<>(Arrays.asList(symClosePar, symAnd, symEquals, symCloseBrace, symLess, symPlus, symMinus, symTimes, symDivision, symAssign, symSemicolon))); //R
        symExpression.setFollow(new ArrayList<>(Arrays.asList(symClosePar, symAnd, symEquals, symCloseBrace, symLess, symPlus, symMinus, symSemicolon))); //S
        symGenExpression.setFollow(new ArrayList<>(Arrays.asList(symClosePar, symSemicolon))); //T
        symRelExpression.setFollow(new ArrayList<>(Arrays.asList(symClosePar, symAnd, symSemicolon))); //U
        symRelTerm.setFollow(new ArrayList<>(Arrays.asList(symClosePar, symAnd, symSemicolon))); //V
        symAddOp.setFollow(new ArrayList<>(Arrays.asList(symOpenPar, symID, symNum))); // W
        symTerm.setFollow(new ArrayList<>(Arrays.asList(symClosePar, symAnd, symEquals, symCloseBrace, symLess, symPlus, symMinus, symTimes, symDivision, symSemicolon))); //X
        symMulOp.setFollow(new ArrayList<>(Arrays.asList(symOpenPar, symID, symNum))); //Y
        symFactor.setFollow(new ArrayList<>(Arrays.asList(symClosePar, symAnd, symEquals, symCloseBrace, symLess, symPlus, symMinus, symTimes, symDivision, symSemicolon))); //Z
        symCall.setFollow(new ArrayList<>(Arrays.asList(symClosePar, symAnd, symEquals, symCloseBrace, symLess, symPlus, symMinus, symTimes, symDivision, symSemicolon))); //@
        symArgs.setFollow(new ArrayList<>(Arrays.asList(symClosePar))); //#
        symArgsList.setFollow(new ArrayList<>(Arrays.asList(symComma, symClosePar))); //$

//        printPoxFollow();

    }
    private void printPoxFirst() {
        System.out.println("POX FIRSTLAR");
        for (Symbol nt: getNonTerminals()) {
            System.out.print(nt.getPox()+" :   ");
            for (Symbol first: nt.getFirst()) {
                System.out.print(first.getPox()+", ");
            }
            System.out.println();
        }
    }
    private void printPoxFollow() {
        System.out.println("POX FOLLOWLAR");
        for (Symbol nt: getNonTerminals()) {
            System.out.print(nt.getPox()+" :   ");
            for (Symbol follow: nt.getFollow()) {
                System.out.print(follow.getPox()+", ");
            }
            System.out.println();
        }
    }
    /*
    private void createDefaultGrammar() {
        Symbol symProgram= new Symbol("Program", false);
        Symbol symDeclarationList = new Symbol("DeclarationList", false);
        Symbol symEOF = new Symbol("EOF", true);
        Symbol symDeclaration = new Symbol("Declaration", false);
        Symbol symVarDeclaration = new Symbol("VarDeclaration", false);
        Symbol symFunDeclaration = new Symbol("FunDeclaration", false);
        Symbol symTypeSpecifier = new Symbol("TypeSpecifier", false);
        Symbol symID = new Symbol("ID", true);
        Symbol symOpenBrace = new Symbol("[", true);
        Symbol symNum = new Symbol("NUM", true);
        Symbol symCloseBrace = new Symbol("]", true);
        Symbol symInt = new Symbol("int", true);
        Symbol symFunRetType = new Symbol("FunReturnType", false);
        Symbol symParams = new Symbol("Params", false);
        Symbol symCompoundStmt = new Symbol("CompoundStmt", false);
        Symbol symVoid = new Symbol("void", true);
        Symbol symParamList = new Symbol("ParamList", false);
        Symbol symComma = new Symbol(",", true);
        Symbol symParam = new Symbol("Param", false);
        Symbol symOpenAk = new Symbol("{", true);
        Symbol symLocalDeclarations = new Symbol("LocalDeclarations", false);
        Symbol symStatementList = new Symbol("StatementList", false);
        Symbol symCloseAk = new Symbol("}", true);
        Symbol symStatement = new Symbol("Statement", false);
        Symbol symExpressionStmt = new Symbol("ExpressionStmt", false);
        Symbol symSelectionStmt = new Symbol("SelectionStmt", false);
        Symbol symIterationStmt = new Symbol("IterationStmt", false);
        Symbol symReturnStmt = new Symbol("ReturnStmt", false);
        Symbol symVar = new Symbol("Var", false);
        Symbol symAssign = new Symbol("=", true);
        Symbol symExpression = new Symbol("Expression", false);
        Symbol symSemicolon = new Symbol(";", true);
        Symbol symIf = new Symbol("if", true);
        Symbol symOpenPar = new Symbol("(", true);
        Symbol symGenExpression = new Symbol("GenExpression", false);
        Symbol symClosePar = new Symbol(")", true);
        Symbol symElse = new Symbol("else", true);
        Symbol symWhile = new Symbol("while", true);
        Symbol symReturn = new Symbol("return", true);
        Symbol symRelExpression = new Symbol("RelExpression", false);
        Symbol symAnd = new Symbol("&&", true);
        Symbol symRelTerm = new Symbol("RelTerm", false);
        Symbol symEquals = new Symbol("==", true);
        Symbol symLess = new Symbol("<", true);
        Symbol symAddOp = new Symbol("AddOp", false);
        Symbol symTerm = new Symbol("Term", false);
        Symbol symPlus = new Symbol("+", true);
        Symbol symMinus = new Symbol("-", true);
        Symbol symMulOp = new Symbol("MulOp", false);
        Symbol symTimes = new Symbol("*", true);
        Symbol symDivision = new Symbol("/", true);
        Symbol symFactor = new Symbol("Factor", false);
        Symbol symCall = new Symbol("Call", false);
        Symbol symArgs = new Symbol("args", false);
        Symbol symArgsList = new Symbol("ArgList", false);

        symbols = new ArrayList<>(Arrays.asList(symProgram, symDeclarationList, symEOF, symDeclaration, symVarDeclaration));
        symbols.addAll(new ArrayList<>(Arrays.asList(symFunDeclaration, symTypeSpecifier, symID, symOpenBrace, symNum, symCloseBrace,symInt)));
        symbols.addAll(new ArrayList<>(Arrays.asList(symFunRetType, symParams, symCompoundStmt, symVoid, symParamList, symComma, symParam, symOpenAk, symCloseAk)));
        symbols.addAll(new ArrayList<>(Arrays.asList(symLocalDeclarations, symStatementList, symStatement, symExpressionStmt, symSelectionStmt, symIterationStmt, symReturnStmt)));
        symbols.addAll(new ArrayList<>(Arrays.asList(symVar, symAssign, symExpression, symSemicolon, symIf, symOpenPar, symGenExpression, symClosePar)));
        symbols.addAll(new ArrayList<>(Arrays.asList(symElse, symWhile, symReturn, symRelExpression, symAnd, symRelTerm, symEquals, symLess, symAddOp)));
        symbols.addAll(new ArrayList<>(Arrays.asList(symTerm, symPlus, symMinus, symMulOp, symTimes, symDivision, symFactor, symCall, symArgs, symArgsList)));
        System.out.println("Symbols size: "+symbols.size());


        Production p0 = new Production(symS, new Symbol[]{symProgram});
        Production p1 = new Production(symProgram, new Symbol[]{symDeclarationList, symEOF});
        Production p2_1 = new Production(symDeclarationList, new Symbol[]{symDeclarationList, symDeclaration});
        Production p2_2 = new Production(symDeclarationList, new Symbol[]{symDeclaration});
        Production p3_1 = new Production(symDeclaration, new Symbol[]{symVarDeclaration});
        Production p3_2 = new Production(symDeclaration, new Symbol[]{symFunDeclaration});
        Production p4_1 = new Production(symVarDeclaration, new Symbol[]{symTypeSpecifier, symID, symSemicolon});
        Production p4_2 = new Production(symVarDeclaration, new Symbol[]{symTypeSpecifier, symID, symOpenBrace, symNum, symCloseBrace, symSemicolon});
        Production p5 = new Production(symTypeSpecifier, new Symbol[]{symInt});
        Production p6 = new Production(symFunDeclaration, new Symbol[]{symFunRetType, symID, symOpenPar, symParams, symClosePar, symCompoundStmt});
        Production p7_1 = new Production(symFunRetType, new Symbol[]{symInt});
        Production p7_2 = new Production(symFunRetType, new Symbol[]{symVoid});
        Production p8_1 = new Production(symParams, new Symbol[]{symParamList});
        Production p8_2 = new Production(symParams, new Symbol[]{symVoid});
        Production p9_1 = new Production(symParamList, new Symbol[]{symParamList, symComma, symParam});
        Production p9_2 = new Production(symParamList, new Symbol[]{symParam});
        Production p10_1 = new Production(symParam, new Symbol[]{symTypeSpecifier, symID});
        Production p10_2 = new Production(symParam, new Symbol[]{symTypeSpecifier, symID, symOpenBrace, symCloseBrace});
        Production p11 = new Production(symCompoundStmt, new Symbol[]{symOpenAk, symLocalDeclarations, symStatementList, symCloseAk});
        Production p12_1 = new Production(symLocalDeclarations, new Symbol[]{symLocalDeclarations, symVarDeclaration});
        Production p12_2 = new Production(symLocalDeclarations);
        Production p13_1 = new Production(symStatementList, new Symbol[]{symStatementList, symStatement});
        Production p13_2 = new Production(symStatementList);
        Production p14_1 = new Production(symStatement, new Symbol[]{symExpressionStmt});
        Production p14_2 = new Production(symStatement, new Symbol[]{symCompoundStmt});
        Production p14_3 = new Production(symStatement, new Symbol[]{symSelectionStmt});
        Production p14_4 = new Production(symStatement, new Symbol[]{symIterationStmt});
        Production p14_5 = new Production(symStatement, new Symbol[]{symReturnStmt});
        Production p15_1 = new Production(symExpressionStmt, new Symbol[]{symVar, symAssign, symExpression, symSemicolon});
        Production p15_2 = new Production(symExpressionStmt, new Symbol[]{symSemicolon});
        Production p16_1 = new Production(symSelectionStmt, new Symbol[]{symIf, symOpenPar, symGenExpression, symClosePar, symStatement});
        Production p16_2 = new Production(symSelectionStmt, new Symbol[]{symIf, symOpenPar, symGenExpression, symClosePar, symStatement, symElse, symStatement});
        Production p17 = new Production(symIterationStmt, new Symbol[]{symWhile, symOpenPar, symGenExpression, symClosePar, symStatement});
        Production p18_1 = new Production(symReturnStmt, new Symbol[]{symReturn, symSemicolon});
        Production p18_2 = new Production(symReturnStmt, new Symbol[]{symReturn, symGenExpression, symSemicolon});
        Production p19_1 = new Production(symVar, new Symbol[]{symVar});
        Production p19_2 = new Production(symVar, new Symbol[]{symID, symOpenBrace, symExpression, symCloseBrace});
        Production p20_1 = new Production(symGenExpression, new Symbol[]{symRelExpression});
        Production p20_2 = new Production(symGenExpression, new Symbol[]{symExpression});
        Production p21_1 = new Production(symRelExpression, new Symbol[]{symRelExpression, symAnd, symRelTerm});
        Production p21_2 = new Production(symRelExpression, new Symbol[]{symRelTerm});
        Production p22_1 = new Production(symRelTerm, new Symbol[]{symExpression, symEquals, symExpression});
        Production p22_2 = new Production(symRelTerm, new Symbol[]{symExpression, symLess, symExpression});
        Production p23_1 = new Production(symExpression, new Symbol[]{symExpression, symAddOp, symTerm});
        Production p23_2 = new Production(symExpression, new Symbol[]{symTerm});
        Production p24_1 = new Production(symAddOp, new Symbol[]{symPlus});
        Production p24_2 = new Production(symAddOp, new Symbol[]{symMinus});
        Production p25_1 = new Production(symTerm, new Symbol[]{symTerm, symMulOp, symFactor});
        Production p25_2 = new Production(symTerm, new Symbol[]{symFactor});
        Production p26_1 = new Production(symMulOp, new Symbol[]{symTimes});
        Production p26_2 = new Production(symMulOp, new Symbol[]{symDivision});
        Production p27_1 = new Production(symFactor, new Symbol[]{symOpenPar, symExpression, symClosePar});
        Production p27_2 = new Production(symFactor, new Symbol[]{symVar});
        Production p27_3 = new Production(symFactor, new Symbol[]{symCall});
        Production p27_4 = new Production(symFactor, new Symbol[]{symNum});
        Production p28 = new Production(symCall, new Symbol[]{symID, symOpenPar, symArgs, symClosePar});
        Production p29_1 = new Production(symArgs, new Symbol[]{symArgsList});
        Production p29_2 = new Production(symArgs);
        Production p30_1 = new Production(symArgsList, new Symbol[]{symArgsList, symComma, symExpression});
        Production p30_2 = new Production(symArgsList, new Symbol[]{symExpression});
        productions = new ArrayList<>(Arrays.asList(p0, p1, p2_1, p2_2, p3_1, p3_2, p4_1, p4_2, p5, p6, p7_1, p7_2, p8_1, p8_2, p9_1, p9_2, p10_1, p10_2));
        productions.addAll(new ArrayList<>(Arrays.asList(p11, p12_1, p12_2, p13_1, p13_2, p14_1, p14_2, p14_3, p14_4, p14_5, p15_1, p15_2, p16_1, p16_2)));
        productions.addAll(new ArrayList<>(Arrays.asList(p17, p18_1, p18_2, p19_1, p19_2, p20_1, p20_2, p21_1, p21_2, p22_1, p22_2, p23_1, p23_2, p24_1, p24_2)));
        productions.addAll(new ArrayList<>(Arrays.asList(p25_1, p25_2, p26_1, p26_2, p27_1, p27_2, p27_3, p27_4, p28, p29_1, p29_2, p30_1, p30_2)));

        printAllProductions();



    }*/

    private void printAllProductions() {
        for (Production p: productions) {
            p.printProduction();
        }
    }

    private void createAdvancedGrammar() {
        Symbol symStmts = new Symbol("Stmts", false);
        Symbol symStmt = new Symbol("Stmt", false);
        Symbol symSemicolon = new Symbol(";", true);
        Symbol symVar = new Symbol("Var", false);
        Symbol symEq = new Symbol("=", true);
        Symbol symId = new Symbol("id", true);
        Symbol symOpenBrace = new Symbol("[", true);
        Symbol symE = new Symbol("E", false);
        Symbol symCloseBrace = new Symbol("]", true);
        Symbol symOpenPar = new Symbol("(", true);
        Symbol symClosePar = new Symbol(")", true);
        symbols = new ArrayList<>(Arrays.asList(symS, symStmts, symStmt, symSemicolon, symVar,
                symId, symEq,symOpenBrace, symE, symCloseBrace, symOpenPar, symClosePar));

        Production p0 = new Production(symS, new Symbol[]{symStmts}, 0);
        Production p1 = new Production(symStmts, new Symbol[]{symStmt}, 1);
        Production p2 = new Production(symStmts, new Symbol[]{symStmts, symSemicolon, symStmt}, 2);
        Production p3 = new Production(symStmt, new Symbol[]{symVar, symEq, symE}, 3);
        Production p4 = new Production(symVar, new Symbol[]{symId, symOpenBrace, symE, symCloseBrace}, 4);
        Production p5 = new Production(symVar, new Symbol[]{symId}, 5);
        Production p6 = new Production(symE, new Symbol[]{symId}, 6);
        Production p7 = new Production(symE, new Symbol[]{symOpenPar, symE, symClosePar}, 7);
        productions = new ArrayList<>(Arrays.asList(p0, p1, p2, p3, p4, p5, p6, p7));


    }


    public ArrayList<Symbol> getSymbols() {
        return symbols;
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }

    private void createSimpleGrammar() {

        Symbol symE = new Symbol("E", false);
        Symbol symPlus = new Symbol("+", true);
        Symbol symT = new Symbol("T", false);
        Symbol symF = new Symbol("F", false);
        Symbol symMult = new Symbol("*", true);
        Symbol symOpenPar = new Symbol("(", true);
        Symbol symClosePar = new Symbol(")", true);
        Symbol symID = new Symbol("id", true);
        symbols = new ArrayList<>(Arrays.asList(symS, symE, symPlus, symT, symF, symMult, symOpenPar, symClosePar, symID, symDollar, symEps));

        Production p0 = new Production(symS, new Symbol[]{symE}, 0);
        Production p1 = new Production(symE, new Symbol[]{symE, symPlus, symT}, 1);
        Production p2 = new Production(symE, new Symbol[]{symT}, 2);
        Production p3 = new Production(symT, new Symbol[]{symT, symMult, symF}, 3);
        Production p4 = new Production(symT, new Symbol[]{symF}, 4);
        Production p5 = new Production(symF, new Symbol[]{symOpenPar, symE, symClosePar}, 5);
        Production p6 = new Production(symF, new Symbol[]{symID}, 6);

        productions = new ArrayList<>(Arrays.asList(p0, p1, p2, p3, p4, p5, p6));

    }

    public ItemSet getInitialItem() {
        return new ItemSet(closure(new Item(productions.get(0))) );
    }
    public ArrayList<Item> closure(Item item) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        return closure(items);
    }

    public ArrayList<Item> closure(ArrayList<Item> items) {
        ArrayList<Item> closure = new ArrayList<>();
        closure.addAll(items);
        boolean repeat;
        ListIterator<Item> itemsInClosure = closure.listIterator();
        Item newItem, item;
        Symbol B;
        while (itemsInClosure.hasNext() ) {
            repeat = false;
            item = itemsInClosure.next();
            B = item.getAfterDot();
            if (B==null || B.isTerminal())
                continue;
            for (Production production : productions) {
                if (!production.getLHS().equals(B))
                    continue;
                newItem = new Item(production);
                if (!closure.contains(newItem)) {
                    itemsInClosure.add(newItem);
                    repeat = true;
                }
            }
            if (repeat) {
                itemsInClosure = closure.listIterator();
            }
        }

//        for (Item item: closure)
//            item.printItem();

        return closure;
    }

    public ArrayList<Item> goTo(ArrayList<Item> items, Symbol X) {
        ArrayList<Item> toReturn = new ArrayList<>();
        ArrayList<Item> temp = new ArrayList<>();
        Item newItem;
        for (Item item: items) {
            int dotPlace = item.getDotPlace();
            if (X.equals(item.getAfterDot())) {
                newItem = new Item(item.getProduction(), dotPlace + 1);
                temp = closure(newItem);
//                toReturn.addAll(closure(newItem));
                for (Item p: temp) {
                    if (!toReturn.contains(p))
                        toReturn.add(p);
                }
            }
        }



        return toReturn;
    }
    public ArrayList<Item> goTo(Item item, Symbol X) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        return goTo(items, X);
    }


    public ArrayList<ItemSet> createLRWithQueue() {
        LRcollections = new ArrayList<>();
        LinkedList<ItemSet> queue = new LinkedList<>();
        ItemSet iis = getInitialItem();
        iis.setNumber(0);
        queue.add(iis);
        int n = 0;
//        int pox = 500;
        while (!queue.isEmpty()) {
//            pox--;
//            System.out.println(queue.size());
            ItemSet itemSet = queue.remove();
            LRcollections.add(itemSet);
//            itemSet.showThisItemSet();
            for (Symbol X: getSymbols()) {
                ArrayList<Item> items = goTo(itemSet.getItems(), X);
                if (items.size()==0)
                    continue;
                ItemSet newItemSet = new ItemSet(items);
                if (LRcollections.contains(newItemSet)) {
                    int k = LRcollections.indexOf(newItemSet);
                    newItemSet.setNumber(k);
                    itemSet.addToMap(X, newItemSet);
                }
                else if (queue.contains(newItemSet)) {
                    int j = queue.indexOf(newItemSet);
                    int k = queue.get(j).getNumber();
                    newItemSet.setNumber(k);
                    itemSet.addToMap(X, newItemSet);
                }
                else {
                    n++;
                    itemSet.addToMap(X, newItemSet);
                    newItemSet.setNumber(n);
                    queue.add(newItemSet);
                }
            }

        }
//        LRcollections.get(0).printItemSet();
//        for (ItemSet is : LRcollections) {
//            is.showThisItemSet();
//        }
//        for (ItemSet is: LRcollections) {
//            is.poxiGorsat();
//        }
        return LRcollections;
    }

    public ArrayList<ItemSet> createLR() {
        int n = 0;
        LRcollections = new ArrayList<>();
        LRcollections.add(getInitialItem());
        LRcollections.get(0).setNumber(0);
        ListIterator<ItemSet> listIterator = LRcollections.listIterator();
        boolean repeat;
        int t = 0;
        int numberOfAdds;
        while (listIterator.hasNext()) {
            repeat = false;
            ItemSet itemSet = listIterator.next();
//            System.out.println(itemSet.getNumber());
            if (itemSet.isChecked())
                continue;
            numberOfAdds = 0;
            for (Symbol X: getSymbols()) {
                ArrayList<Item> items = goTo(itemSet.getItems(), X);
                if (items.size()==0)
                    continue;
                ItemSet newItemSet = new ItemSet(items);

                if (!LRcollections.contains(newItemSet)) {
                    n++;
                    itemSet.addToMap(X, newItemSet);
                    LRcollections.add(newItemSet);
                    newItemSet.setNumber(n);
                    repeat = true;
                    numberOfAdds++;
                }
                else if (LRcollections.contains(newItemSet)) {
                    int k = LRcollections.indexOf(newItemSet);
                    newItemSet.setNumber(k);
                    itemSet.addToMap(X, newItemSet);
                }
            }
            itemSet.check();
            if (repeat) {
                listIterator = LRcollections.listIterator();
                t = 0;
            }
//            for (int i = 0; i <= numberOfAdds; i++) {
//                listIterator.previous();
//            }

        }


        LRcollections.get(0).printItemSet();
        System.out.println("LR CREATED");
        return LRcollections;
    }
    public ArrayList<Symbol> getTerminals() {
        ArrayList<Symbol> terminals = new ArrayList<>();
        for (Symbol s: symbols) {
            if (s.isTerminal())
                terminals.add(s);
        }
        return terminals;
    }
    public ArrayList<Symbol> getNonTerminals() {
        ArrayList<Symbol> nonTerminals = new ArrayList<>();
        for (Symbol s: symbols) {
            if (!s.isTerminal())
                nonTerminals.add(s);
        }
        return nonTerminals;
    }

    public void computeFirst() {
        for (Symbol symbol: symbols) {
            if (symbol.isFirstChecked())
                continue;
            first(symbol);
        }
//        for (Symbol symbol: symbols) {
//            System.out.println(symbol.getName()+":");
//            for (Symbol firstSymbol: symbol.getFirst()) {
//                System.out.print(firstSymbol.getName()+" ");
//            }
//            System.out.println();
//            System.out.println("____________________");
//        }
    }

    public void computeFollow() {
        for (Symbol symbol: symbols) {
            if (symbol.isFollowChecked())
                continue;
            follow(symbol);
        }
//        System.out.println("FOLLOWWWS:::");
//        for (Symbol symbol: getNonTerminals()) {
//            System.out.println(symbol.getName()+":");
//            for (Symbol followSymbol: symbol.getFollow()) {
//                System.out.print(followSymbol.getName()+" ");
//            }
//            System.out.println();
//            System.out.println("____________________");
//        }

    }

    private void first(Symbol A) {
        ArrayList<Symbol> toReturn = new ArrayList<>();
        if (A.isFirstChecked())
            return;
        A.setFirstChecked();
        if (A.isTerminal()) {
            toReturn.add(A);
        }
        else {

            for (Production production: productions) {
                if (!production.getLHS().equals(A))
                    continue;
                if (production.getRHS().length==0) {
                    toReturn.add(symEps);
                } else {
                    boolean hasEps = false;
                    int i;
                    for (i = 0; i < production.getRHS().length; i++) {
                        first(production.getRHS()[i]);
                        ArrayList<Symbol> temp = production.getRHS()[i].getFirst();
                                hasEps = false;
                        for (Symbol symbol: temp) {
                            if (symbol.equals(symEps))
                                hasEps=true;
                            else {
                                if (!toReturn.contains(symbol)) {
                                    toReturn.add(symbol);
                                }
                            }

                        }
                        if (!hasEps)
                            break;
                    }
                    if (i==production.getRHS().length && hasEps) {
                        toReturn.add(symEps);
                    }
                }
            }
        }
        A.setFirst(toReturn);
    }

    private void follow(Symbol A) {
        ArrayList<Symbol> toReturn = new ArrayList<>();
        if (A.isFollowChecked())
            return;
        A.setFollowChecked();
        if (A.equals(symS)) {
            A.addFollow(new ArrayList<>(Arrays.asList(symDollar)));
        }
        for (Production production: productions) {
            int i;
            for (i = 0; i < production.getRHS().length; i++) {
                Symbol Xi = production.getRHS()[i];
                if (Xi.isTerminal())
                    continue;
                if (i==production.getRHS().length) {
                    follow(production.getLHS());
                    ArrayList<Symbol> temp = production.getLHS().getFollow();
                    Xi.addFollow(temp);
                }
                else {
                    int j;
                    for (j = i+1; j < production.getRHS().length; j++) {
                        Symbol Xj = production.getRHS()[j];
                        Xi.addFollow(Xj.getFirst());
                        if (!Xj.getFirst().contains(symEps))
                            break;
                    }
                    if (j == production.getRHS().length) {
                        follow(production.getLHS());
                        ArrayList<Symbol> temp = production.getLHS().getFollow();
                        Xi.addFollow(temp);
                    }
                }

            }


        }


    }


    public ArrayList<HashMap<Symbol, ParseAction>> createParseTable() {

        ArrayList<ItemSet> states = createLRWithQueue();
        int size = states.size();
        ArrayList<HashMap<Symbol, ParseAction>> parseTable = new ArrayList<>(size);
        for (ItemSet itemSet: states) {
            HashMap<Symbol, ParseAction> row = new HashMap<>();
            for (Item item: itemSet.getItems()) {
                Symbol a = item.getAfterDot();
                if (a==null) {
                    if (item.getProduction().getLHS().getName().equals("S")) {
                        ParseAction action = new ParseAction(ParseAction.ACC, -1);
                        row.put(Grammar.symDollar, action);
                    }
                    else {

                        int productionNo = item.getProduction().getNumber();
                        for (Symbol z:   item.getProduction().getLHS().getFollow() ) {
                            ParseAction action = new ParseAction(ParseAction.REDUCE, productionNo);
                            checkConflict(parseTable,row, z, action);
                            row.put(z, action);
                        }
                    }
                }
                else {
                    if (!a.isTerminal())
                        continue;
                    ItemSet J = itemSet.getMap().get(a);
                    if (J==null)
                        continue;
                    int j = J.getNumber();
                    ParseAction action = new ParseAction(ParseAction.SHIFT, j);
                    checkConflict(parseTable, row, a, action);
                    row.put(a, action);
                }
                for (Symbol X: getNonTerminals()) {
                    ItemSet J = itemSet.getMap().get(X);
                    if (J==null)
                        continue;
                    int j = J.getNumber();
                    ParseAction action = new ParseAction(ParseAction.GOTO, j);
                    checkConflict(parseTable, row, X, action);
                    row.put(X, action);
                }

            }
            parseTable.add(row);
        }

//        int n = 0;
//        for (HashMap<Symbol, ParseAction> row: parseTable) {
//            System.out.println(n++);
//            for (Map.Entry<Symbol, ParseAction> pair: row.entrySet()) {
//                System.out.print(pair.getKey().getName()+" ");
//                pair.getValue().printAction();
//                System.out.print(" , ");
//            }
//            System.out.println();
//            System.out.println("_______________");
//
//        }


        return parseTable;
    }

    private void checkConflict(ArrayList<HashMap<Symbol, ParseAction>> parseTable, HashMap<Symbol, ParseAction> row,
                               Symbol s, ParseAction action ) {
//        if (row.get(s)!=null) {
//            if (!row.get(s).getActionText().equals(action.getActionText())) {
//                System.out.println("**********CONFLIIIICT in row " + parseTable.size()+"because of "+s.getName());
//                System.out.println("already had " + row.get(s).getActionText());
//                System.out.println("want to add " + action.getActionText());
//            }
//
//        }
    }


}
