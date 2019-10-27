package jg.cs.compile.nodes.atoms;

import jg.cs.common.Type;
import net.percederberg.grammatica.parser.Token;

public class Typ extends Atom<Type>{
  
  public Typ(Token typeToken) {
    super(typeToken, Type.valueOf(typeToken.getImage().toUpperCase()));
  }

}
