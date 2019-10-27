package jg.cs.compile.nodes.atoms;

import jg.cs.compile.nodes.Expr;
import net.percederberg.grammatica.parser.Token;

public class Identifier extends Atom<String>{

  public Identifier(Token idenToken) {
    super(idenToken, idenToken.getImage());
  }
  
  @Override
  public String toString() {
    return "IDEN ~ "+getActualValue();
  }

}
