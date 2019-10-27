package jg.cs.compile;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jg.cs.compile.errors.DuplicateFunctionException;
import jg.cs.compile.nodes.Expr;
import jg.cs.compile.nodes.FunctDefExpr;

/**
 * A collection of Expr that make-up a sNEK program
 * @author Jose Guaro
 *
 */
public class Program {

  private final String fileName;
  private final Map<FunctionSignature, FunctDefExpr> fileFunctions;  
  private final List<Expr> exprList; //this will include file functions
  
  private Program(String fileName, 
      Map<FunctionSignature,FunctDefExpr> fileFunctions,
      List<Expr> exprList) {
      
    this.fileName = fileName;
    this.fileFunctions = fileFunctions;
    this.exprList = exprList;
  }
  
  public String getFileName() {
    return fileName;
  }

  public Map<FunctionSignature, FunctDefExpr> getFileFunctions() {
    return fileFunctions;
  }

  public List<Expr> getExprList() {
    return exprList;
  }

  public static Program formProgram(String fileName, List<Expr> exprs) {
    HashMap<FunctionSignature, FunctDefExpr> functionDefs = new HashMap<>();
    
    for (Expr expr : exprs) {
      if (expr instanceof FunctDefExpr) {
        FunctDefExpr functDef = (FunctDefExpr) expr;
        
        if (functionDefs.containsKey(functDef.getIdentity().getSignature())) {
          throw new DuplicateFunctionException(functDef.getLeadToken(), functDef.getIdentity().getSignature(), fileName);
        }
        functionDefs.put(functDef.getIdentity().getSignature(), functDef);
      }
    }
    
    return new Program(fileName, functionDefs, exprs);
  }
  
}
