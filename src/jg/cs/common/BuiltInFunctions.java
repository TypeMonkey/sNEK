package jg.cs.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jg.cs.common.Type;

/**
 * This class holds all the FunctionIdentities that are
 * provided as "built-in" function in sNEK such as the following:
 *  - input : -> void >>>> string
 *  - print : -> string >>> string
 *  - println : -> string >>> string
 *  - inc : -> int >>> int
 *  - dec : -> int >>> int
 *  - isInt : -> (int or boo or str) >>> bool
 *  - isBool : ->  (int or bool or str) >>> bool
 *  - isStr : ->  (int or bool or str) >>> bool
 *  - type : -> (int or boolor str) >>> str (returns the type name as a string)
 * @author Jose
 *
 */
public enum BuiltInFunctions {
    
  /**
   * Blocks for an input string on standard input. Returns input as a string
   */
  INPUT(new FunctionIdentity(new FunctionSignature("input", Type.VOID), Type.STRING)),
  
  /**
   * Prints a string to stdout, without a new line at the end
   */
  PRINT(new FunctionIdentity(new FunctionSignature("print", Type.STRING), Type.STRING)),
  
  /**
   * Prints a string to stdout, with a new line at the end
   */
  PRINTLN(new FunctionIdentity(new FunctionSignature("println", Type.STRING), Type.STRING)),
  
  /**
   * Returns an integer with incremented by one
   */
  INC(new FunctionIdentity(new FunctionSignature("inc", Type.INT), Type.INT)),
  
  /**
   * Returns an integer with decremented by one
   */
  DEC(new FunctionIdentity(new FunctionSignature("dec", Type.INT), Type.INT)),
  
  /**
   * Returns the string representation of an integer
   */
  TO_STR_I(new FunctionIdentity(new FunctionSignature("toStr", Type.INT), Type.STRING)),
  
  /**
   * Returns the string representation of a boolean
   */
  TO_STR_B(new FunctionIdentity(new FunctionSignature("toStr", Type.BOOL), Type.STRING)),
  
  ISINT_I(new FunctionIdentity(new FunctionSignature("isInt", Type.INT), Type.BOOL)),
  
  ISINT_B(new FunctionIdentity(new FunctionSignature("isInt", Type.BOOL), Type.BOOL)),
  
  ISINT_S(new FunctionIdentity(new FunctionSignature("isInt", Type.STRING), Type.BOOL)),
  
  ISBOOL_I(new FunctionIdentity(new FunctionSignature("isBool", Type.INT), Type.BOOL)),
  
  ISBOOL_B(new FunctionIdentity(new FunctionSignature("isBool", Type.BOOL), Type.BOOL)),
  
  ISBOOL_S(new FunctionIdentity(new FunctionSignature("isBool", Type.STRING), Type.BOOL)),
  
  ISSTR_I(new FunctionIdentity(new FunctionSignature("isStr", Type.INT), Type.BOOL)),
  
  ISSTR_B(new FunctionIdentity(new FunctionSignature("isStr", Type.BOOL), Type.BOOL)),
  
  ISSTR_S(new FunctionIdentity(new FunctionSignature("isStr", Type.STRING), Type.BOOL)),
  
  TYPE_I(new FunctionIdentity(new FunctionSignature("type", Type.INT), Type.STRING)),
  
  TYPE_B(new FunctionIdentity(new FunctionSignature("type", Type.BOOL), Type.STRING)),
  
  TYPE_S(new FunctionIdentity(new FunctionSignature("type", Type.STRING), Type.STRING));
  
  private final FunctionIdentity identity;
  
  public static final Map<FunctionSignature, BuiltInFunctions> BUILT_IN_MAP;
  static {
    HashMap<FunctionSignature, BuiltInFunctions> temp = new HashMap<>();
    
    for (BuiltInFunctions funcs : BuiltInFunctions.values()) {
      temp.put(funcs.identity.getSignature(), funcs);
    }
    
    BUILT_IN_MAP = Collections.unmodifiableMap(temp);
  }

  
  private BuiltInFunctions(FunctionIdentity identity) {
    this.identity = identity;
  }
  
  public FunctionIdentity getIdentity() {
    return identity;
  }
}
