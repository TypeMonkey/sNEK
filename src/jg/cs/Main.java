package jg.cs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import jg.cs.compile.Program;
import jg.cs.compile.TypeChecker;
import jg.cs.compile.nodes.Expr;
import jg.cs.compile.parser.CopperHeadParser;
import jg.cs.compile.parser.CopperHeadTokenizer;
import jg.cs.compile.parser.ExprBuilder;
import jg.cs.runtime.Executor;
import jg.cs.runtime.errors.ExecException;
import jg.cs.runtime.values.Value;
import net.percederberg.grammatica.parser.ParseException;
import net.percederberg.grammatica.parser.ParserCreationException;
import net.percederberg.grammatica.parser.ParserLogException;
import net.percederberg.grammatica.parser.Token;
import net.percederberg.grammatica.parser.Tokenizer;

public class Main {

  /**
   * Main driver method for the sNEK interpreter
   * @param args - the string arguments to the interpreter
   */
  public static void main(String[] args) {
    if (args.length == 1 && getFileExtension(args[0]).equals("snek")) {
      File targetFile = new File(args[0]);
      if (targetFile.exists() && targetFile.canRead()) {
        
        List<Token> tokens = null;
        try {
          tokens = tokenizeSource(targetFile);
        } catch (FileNotFoundException e) {
          System.err.println("sNEK: Provided source file is either nonexistant or unreadble!");
          System.exit(-1);
        } catch (ParseException e) {
          System.err.println("sNEK: "+e.getMessage());
          System.exit(-1);
        }
        
        if (tokens == null) {
          System.err.println("Fatal Error: Couldn't create tokenizer! Exiting....");
          System.exit(-1);
        }

        List<Expr> components = null;
        try {
          components = parse(tokens);
        } catch (ParserLogException e) {
          System.err.println("sNEK: "+e.getMessage());
          System.exit(-1);
        }
        
        if (components == null) {
          System.err.println("Fatal Error: Couldn't create parser! Exiting....");
          System.exit(-1);
        }

        Program program = Program.formProgram(targetFile.getName(), components);
        //System.out.println(components);

        //System.out.println("-------------TYPE CHECKING-------------");
        TypeChecker typeChecker = new TypeChecker(program);
        typeChecker.checkType();
        
        //System.out.println("-------------EXECUTING-------------");
        Executor executor = new Executor(program);
        try {
          Value<?> result = executor.execute();
        } catch (ExecException e) {
          System.err.println(e.getMessage());
          System.err.println("Exiting......");
          System.exit(-1);
        }
        
        //System.out.println(" -----> FINAL: "+result);
      }
      else {
        System.err.println("sNEK: Provided source file is either nonexistant or unreadble!");
      }
    }
    else {
      System.err.println("sNEK: Missing .snek file as argument!");
    }
  }
  
  /**
   * Returns the extension of a file, without the "."
   * @param fileName - the name of the file
   * @return the file extension, or the empty string if no extension can't be found
   */
  public static String getFileExtension(String fileName) {
    int dotLI = fileName.lastIndexOf(".");
    if (dotLI < 0) {
      return "";
    }
    return fileName.substring(dotLI+1, fileName.length());
  }
  
  /**
   * Tokenizes a CrazedSnake program
   * @param source - the entire source program as a string
   * @return a list of Tokens
   * @throws ParseException - if an unrecognizable token was detected
   * @throws FileNotFoundException 
   */
  public static List<Token> tokenizeSource(File source) throws ParseException, FileNotFoundException{
    try {
      ArrayList<String> nonCommentLines = new ArrayList<String>();
      BufferedReader reader = new BufferedReader(new FileReader(source));
      
      String temp = null;
      try {
        while ((temp = reader.readLine()) != null) {
          nonCommentLines.add(temp);
        }
      } catch (IOException e) {
        System.err.println("sNEK: IO Error encountered while reading "+source.getName());
      }
      
      Tokenizer tokenizer = new CopperHeadTokenizer(new FileReader(source));
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
