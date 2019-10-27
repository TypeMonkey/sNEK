package jg.cs;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jg.cs.compile.Program;
import jg.cs.compile.TypeChecker;
import jg.cs.compile.nodes.Expr;
import jg.cs.compile.parser.CopperHeadParser;
import jg.cs.compile.parser.CopperHeadTokenizer;
import jg.cs.compile.parser.ExprBuilder;
import jg.cs.runtime.Executor;
import jg.cs.runtime.values.Value;
import net.percederberg.grammatica.parser.ParseException;
import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ParserLogException;
import net.percederberg.grammatica.parser.Token;
import net.percederberg.grammatica.parser.Tokenizer;

public class Main {

  public static void main(String[] args) throws Exception {
    String test = "";
    Scanner scanner = new Scanner(new File("sample.nek"));
    while (scanner.hasNextLine()) {
      test += scanner.nextLine()+System.lineSeparator();
    }
    scanner.close();   
    
    System.out.println("to parse: "+test);

    List<Token> tokens = tokenizeSource(test);
    if (tokens == null) {
      System.err.println("Fatal Error: Couldn't create tokenizer! Exiting....");
      System.exit(-1);
    }

    System.out.println("ALL: "+tokens);

    List<Expr> components = parse(tokens);
    if (components == null) {
      System.err.println("Fatal Error: Couldn't create parser! Exiting....");
      System.exit(-1);
    }

    Program program = Program.formProgram("testProg", components);
    System.out.println(components);

    System.out.println("-------------TYPE CHECKING-------------");
    TypeChecker typeChecker = new TypeChecker(program);
    System.out.println("RESULT: "+typeChecker.checkType());
    
    System.out.println("-------------EXECUTING-------------");
    Executor executor = new Executor(program);
    Value<?> result = executor.execute();
    
    System.out.println(" -----> FINAL: "+result);
  }
  
  /**
   * Tokenizes a CrazedSnake program
   * @param source - the entire source program as a string
   * @return a list of Tokens
   * @throws ParseException - if an unrecognizable token was detected
   */
  public static List<Token> tokenizeSource(String source) throws ParseException{
    try {
      Tokenizer tokenizer = new CopperHeadTokenizer(new StringReader(source));
      ArrayList<Token> allTokens = new ArrayList<>();

      Token current = null;
      while((current = tokenizer.next()) != null) {
        allTokens.add(current);
      }

      return allTokens;
    } catch (ParserCreationException e) {
      return null;
    }
  }
  
  /**
   * Parses a list of tokens into a coherent abstract sturcture in the form 
   * of a list of expressions
   * @param tokens - list of Tokens to gather tokens from
   * @return a list of Expr
   * @throws ParserLogException - if invalid syntax was detected
   */
  public static List<Expr> parse(List<Token> tokens) throws ParserLogException{
    try {
      ExprBuilder builder = new ExprBuilder();
      CopperHeadParser parser = new CopperHeadParser(null, builder);

      parser.parseFromTokenList(tokens);
      
      return new ArrayList<>(builder.getStackNodes());
    } catch (ParserCreationException e) {
      return null;
    }
  }
}
