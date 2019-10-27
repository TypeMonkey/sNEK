package jg.cs.compile.errors;

import jg.cs.common.FunctionSignature;
import jg.cs.compile.nodes.FunctionCall;
import jg.cs.compile.nodes.atoms.Identifier;
import net.percederberg.grammatica.parser.Token;

public class UnresolvableComponentException extends RuntimeException {
  
  public UnresolvableComponentException(Identifier identifier, String fileName) {
    super("Error: Cannot find the variable '"+identifier+"' , "
        + "at <ln: "+identifier.getLeadLnNumber()+
        ", col: "+identifier.getLeadColNumber()+
        ", "+fileName+">");
  }
  
  public UnresolvableComponentException(FunctionSignature call, Token callLoc, String fileName) {
    super("Error: Cannot find the function '"+call.getName()+"' , "
        + "at <ln: "+callLoc.getStartLine()+
        ", col: "+callLoc.getStartColumn()+
        ", "+fileName+">");
  }
}
