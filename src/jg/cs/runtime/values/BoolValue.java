package jg.cs.runtime.values;

import jg.cs.common.Type;

public class BoolValue extends Value<Boolean>{

  public BoolValue(Boolean actualValue) {
    super(actualValue, Type.BOOL);
  }

  @Override
  public String toString() {
    return actualValue.toString();
  }

}
