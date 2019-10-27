package jg.cs.compile.nodes.atoms;

import jg.cs.compile.Type;
import net.percederberg.grammatica.parser.Token;

public class Typ extends Atom<Type>{
  
  public Typ(Token typeToken) {
    super(typeToken, Type.valueOf(typeToken.getImage().toUpperCase()));
  }

}
