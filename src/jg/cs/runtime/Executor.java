package jg.cs.runtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jg.cs.common.BuiltInFunctions;
import jg.cs.common.FunctionSignature;
import jg.cs.common.OperatorKind;
import jg.cs.common.Type;
import jg.cs.compile.Program;
import jg.cs.compile.errors.TypeMismatchException;
import jg.cs.compile.errors.UnresolvableComponentException;
import jg.cs.compile.nodes.BinaryOpExpr;
import jg.cs.compile.nodes.Expr;
import jg.cs.compile.nodes.FunctDefExpr;
import jg.cs.compile.nodes.FunctionCall;
import jg.cs.compile.nodes.IdenTypeValTuple;
import jg.cs.compile.nodes.IfExpr;
import jg.cs.compile.nodes.LetExpr;
import jg.cs.compile.nodes.SetExpr;
import jg.cs.compile.nodes.WhileExpr;
import jg.cs.compile.nodes.atoms.BinaryOperator;
import jg.cs.compile.nodes.atoms.Bool;
import jg.cs.compile.nodes.atoms.Identifier;
import jg.cs.compile.nodes.atoms.Int;
import jg.cs.compile.nodes.atoms.Str;
import jg.cs.runtime.values.Value;

public class Executor {
  
  private final Program program;
  
  public Executor(Program program) {
    this.program = program;
  }
  
  public Value<?> execute(){
    System.out.println("---FMAP: "+program.getFileFunctions());
    Value<?> latest = null;
    for (Expr component : program.getExprList()) {
      latest = checkExpr(component, new ArrayList<>(), fwrap(program.getFileFunctions()));
    }   
    
    return latest;
  }
  
  private Type checkExpr(Expr expr, 
      List<Map<String, IdenTypeValTuple>> others, 
      List<Map<FunctionSignature, FunctDefExpr>> fenv) {
    System.out.println("TARGET: "+expr);
    if (expr instanceof Int) {
      return Type.INT;
    }
    else if (expr instanceof Bool) {
      return Type.BOOL;
    }
    else if (expr instanceof Str) {
      return Type.STRING;
    }
    else if (expr instanceof Identifier) {
      return checkIdentifier((Identifier) expr, others, fenv);
    }
    else if (expr instanceof FunctDefExpr) {
      FunctDefExpr functDef = (FunctDefExpr) expr;
      System.out.println("--------CHECKED: "+checkedFunctions);
      if (checkedFunctions.contains(functDef.getIdentity().getSignature())) {
        return functDef.getReturnType().getActualValue();
      }
      return checkFuncDef(functDef, others, fenv);
    }
    else if (expr instanceof LetExpr) {
      return checkLet((LetExpr) expr, others, fenv);
    }
    else if (expr instanceof IfExpr) {
      return checkIf((IfExpr) expr, others, fenv);
    }
    else if (expr instanceof BinaryOpExpr) {
      return checkBinaryOperation((BinaryOpExpr) expr, others, fenv);
    }
    else if (expr instanceof SetExpr) {
      return checkSetExpr((SetExpr) expr, others, fenv);
    }
    else if (expr instanceof FunctionCall) {
      return checkFunctionCall((FunctionCall) expr, others, fenv);
    }
    else if (expr instanceof WhileExpr) {
      return checkWhileLoop((WhileExpr) expr, others, fenv);
    }
    
    System.out.println("unknown? "+expr.getClass());
    return null;
  }
  
  private Type checkBinaryOperation(BinaryOpExpr binaryOpExpr, 
      List<Map<String, IdenTypeValTuple>> others, 
      List<Map<FunctionSignature, FunctDefExpr>> fenv) {
    System.out.println("----BIN OP: "+binaryOpExpr);
    Type leftType = checkExpr(binaryOpExpr.getLeft(), others, fenv);
    Type rightType = checkExpr(binaryOpExpr.getRight(), others, fenv);
    
    BinaryOperator operator = binaryOpExpr.getOperator();
    OperatorKind operatorKind = operator.getActualValue();
    
    //arithmetic operators
    HashSet<OperatorKind> arithOps = new HashSet<OperatorKind>();
    arithOps.addAll(Arrays.asList(OperatorKind.PLUS, OperatorKind.MINUS, OperatorKind.TIMES));
    
    if (arithOps.contains(operatorKind)) {
      if (leftType != Type.INT) {
        System.out.println("----LEFT TYPE: "+leftType);
        throw new TypeMismatchException(operatorKind, 
            binaryOpExpr.getLeft(), leftType, Type.INT, program.getFileName());
      }
      else if (rightType != Type.INT) {
        throw new TypeMismatchException(operatorKind, 
            binaryOpExpr.getRight(), rightType, Type.INT, program.getFileName());
      }
      else {
        return Type.INT;
      }
    }
    else if (operatorKind == OperatorKind.EQUAL) {
      return Type.BOOL;
    }
    else {
      //comparison operator
      if (leftType != Type.INT) {
        throw new TypeMismatchException(operatorKind, 
            binaryOpExpr.getLeft(), leftType, Type.INT, program.getFileName());
      }
      else if (rightType != Type.INT) {
        throw new TypeMismatchException(operatorKind, 
            binaryOpExpr.getRight(), rightType, Type.INT, program.getFileName());
      }
      else {
        return Type.BOOL;
      }
    }
  }
  
  private Type checkFunctionCall(FunctionCall call, 
      List<Map<String, IdenTypeValTuple>> others, 
      List<Map<FunctionSignature, FunctDefExpr>> fenv) {
    
    System.out.println("****** FUN CALL "+call);
    Type [] argTypes = new Type[call.getArgCount()];
    int i = 0;
    for(Expr argument : call.getArguments()) {
      argTypes[i] = checkExpr(argument, others, fenv);
      System.out.println("    ---> ARG["+i+"]:  "+argument);
      i++;
    }
    
    FunctionSignature signature = new FunctionSignature(
        call.getFuncName().getImage(), 
        argTypes);
    
    for (Map<FunctionSignature, FunctDefExpr> fmap : fenv) {
      if (fmap.containsKey(signature)) {
        Type retType = fmap.get(signature).getReturnType().getActualValue();
        System.out.println(" --->!!! FOUND TYPE: "+retType);
        return retType;
      }
    }
    
    //if function isn't found, then check built ins
    if (BuiltInFunctions.BUILT_IN_MAP.containsKey(signature)) {
      return BuiltInFunctions.BUILT_IN_MAP.get(signature).getReturnType();
    }
    
    System.out.println("FINDING: "+signature);
    System.out.println("  MAPS: "+fenv);
    throw new UnresolvableComponentException(signature, 
        call.getLeadToken(), 
        program.getFileName());
  }
  
  private Type checkIf(IfExpr ifExpr, 
      List<Map<String, IdenTypeValTuple>> others, 
      List<Map<FunctionSignature, FunctDefExpr>> fenv) {
    Type actualTypeOfCond = checkExpr(ifExpr.getCondition(), others, fenv);
    if (actualTypeOfCond != Type.BOOL) {
      throw new TypeMismatchException(ifExpr.getLeadToken(), 
          Type.BOOL, actualTypeOfCond, program.getFileName());
    }
    
    //check if both consequences have the same type
    Type trueConseq = checkExpr(ifExpr.getTrueConseq(), others, fenv);
    Type falseConseq = checkExpr(ifExpr.getFalseConseq(), others, fenv);
    
    if (trueConseq != falseConseq) {
      throw new TypeMismatchException(ifExpr.getLeadToken(), 
          trueConseq, 
          falseConseq, 
          program.getFileName());
    }
    
    return trueConseq;
  }
  
  private Type checkWhileLoop(WhileExpr whileExpr, 
      List<Map<String, IdenTypeValTuple>> others, 
      List<Map<FunctionSignature, FunctDefExpr>> fenv) {
    Type actualTypeOfCond = checkExpr(whileExpr.getCondition(), others, fenv);
    if (actualTypeOfCond != Type.BOOL) {
      throw new TypeMismatchException(whileExpr.getLeadToken(), 
          Type.BOOL, actualTypeOfCond, program.getFileName());
    }
    
    //now, check all statements
    Type latestType = null;
    
    LinkedHashMap<FunctionSignature, FunctDefExpr> localFuncMap = new LinkedHashMap<>();
    
    //if last statement is func def, throw error
    Expr latest = null;
    for(Expr statement : whileExpr.getExpressions()) {
      if (statement instanceof FunctDefExpr) {
        FunctDefExpr functDefExpr = (FunctDefExpr) statement;
        latestType = checkFuncDef(functDefExpr, others, fconcatToFront(localFuncMap, fenv));
        localFuncMap.put(functDefExpr.getIdentity().getSignature(), functDefExpr);
      }
      else {
        latest = statement;
        latestType = checkExpr(statement, others, fenv);
      }
    }
    
    if (latest instanceof FunctDefExpr) {
      throw new TypeMismatchException((FunctDefExpr) latest, program.getFileName());
    }
    
    return latestType;
  }
  
  private Type checkLet(LetExpr expr, 
      List<Map<String, IdenTypeValTuple>> others, 
      List<Map<FunctionSignature, FunctDefExpr>> fenv) {
    LinkedHashMap<String, IdenTypeValTuple> localEnv = new LinkedHashMap<>();
   
    System.out.println("---LET: "+expr.getExpressions());
    
    //load all local variables. At each local variable, evaluate the type
    //of its value with the current localEnv
    
    for (IdenTypeValTuple var : expr.getVars().values()) {
      Type typeOfValue = checkExpr(var.getValue(), concatToFront(localEnv, others) , fenv);
      if (var.getType() != typeOfValue) {
        throw new TypeMismatchException(var, typeOfValue, program.getFileName());
      }
      
      localEnv.put(var.getIdentifier().getActualValue(), var);
    }
    System.out.println("----DONE CHECKING LET");
    //now, check all statements
    Type latestType = null;
    
    LinkedHashMap<FunctionSignature, FunctDefExpr> localFuncMap = new LinkedHashMap<>();
    
    for(Expr statement : expr.getExpressions()) {
      if (statement instanceof FunctDefExpr) {
        FunctDefExpr functDefExpr = (FunctDefExpr) statement;
        latestType = checkFuncDef(functDefExpr, concatToFront(localEnv, others), fconcatToFront(localFuncMap, fenv));
        localFuncMap.put(functDefExpr.getIdentity().getSignature(), functDefExpr);
      }
      else {
        latestType = checkExpr(statement, concatToFront(localEnv, others), fconcatToFront(localFuncMap, fenv));
      }
    }
    
    return latestType;
  }
  
  private Type checkFuncDef(FunctDefExpr expr, 
      List<Map<String, IdenTypeValTuple>> others, 
      List<Map<FunctionSignature, FunctDefExpr>> fenv) {
    LinkedHashMap<String, IdenTypeValTuple> localEnv = new LinkedHashMap<>();
   
    //load all parameters variables. At each parameter, evaluate the type
    //of its value with the current localEnv
    
    for (IdenTypeValTuple var : expr.getParameters().values()) {
      localEnv.put(var.getIdentifier().getActualValue(), var);
    }
    
    //now, check all statements   
    LinkedHashMap<FunctionSignature, FunctDefExpr> localFuncMap = new LinkedHashMap<>();
    
    Type lastType = null;
    for(Expr statement : expr.getExpressionsExprs()) {
      if (statement instanceof FunctDefExpr) {
        FunctDefExpr functDefExpr = (FunctDefExpr) statement;
        localFuncMap.put(functDefExpr.getIdentity().getSignature(), functDefExpr);
        lastType = checkFuncDef(functDefExpr, concatToFront(localEnv, others), fconcatToFront(localFuncMap, fenv));
      }
      else {
        lastType = checkExpr(statement, concatToFront(localEnv, others), fconcatToFront(localFuncMap, fenv));
      }
    }
    
    if (expr.getReturnType().getActualValue() == Type.VOID ||
        lastType == expr.getReturnType().getActualValue()) {
      checkedFunctions.add(expr.getIdentity().getSignature());
      return lastType;
    }
    throw new TypeMismatchException(expr.getLeadToken(),
        expr.getReturnType().getActualValue(), lastType, program.getFileName());
  }
  
  private Type checkSetExpr(SetExpr setExpr, 
      List<Map<String, IdenTypeValTuple>> env, 
      List<Map<FunctionSignature, FunctDefExpr>> fenv) {
    Type varType = checkIdentifier(setExpr.getIdentifier(), env, fenv);
    
    Type valueType = checkExpr(setExpr.getValue(), env, fenv);
    
    if (varType == valueType) {
      return varType;
    }
    throw new TypeMismatchException(setExpr.getLeadToken(), 
        varType, 
        valueType, 
        program.getFileName());
  }
  
  private Type checkIdentifier(Identifier identifier, 
      List<Map<String, IdenTypeValTuple>> env, 
      List<Map<FunctionSignature, FunctDefExpr>> fenv) {
    for (Map<String, IdenTypeValTuple> map : env) {
      if (map.containsKey(identifier.getActualValue())) {
        return map.get(identifier.getActualValue()).getType();
      }
    }
    throw new UnresolvableComponentException(identifier, program.getFileName());
  }
  
  private List<Map<String, IdenTypeValTuple>> concatToFront(
      Map<String, IdenTypeValTuple> newMap, 
      List<Map<String, IdenTypeValTuple>> others){
    ArrayList<Map<String, IdenTypeValTuple>> newEnv = new ArrayList<>();
    newEnv.add(newMap);
    newEnv.addAll(others);
    
    return newEnv;
  }
  

  private List<Map<FunctionSignature, FunctDefExpr>> fconcatToFront(
      Map<FunctionSignature, FunctDefExpr> newMap, 
      List<Map<FunctionSignature, FunctDefExpr>> others){
    ArrayList<Map<FunctionSignature, FunctDefExpr>> newEnv = new ArrayList<>();
    newEnv.add(newMap);
    newEnv.addAll(others);
    
    return newEnv;
  }

  private List<Map<FunctionSignature, FunctDefExpr>> fwrap(Map<FunctionSignature, FunctDefExpr> newMap){
    ArrayList<Map<FunctionSignature, FunctDefExpr>> newEnv = new ArrayList<>();
    newEnv.add(newMap);
    
    return newEnv;
  }
  
}
