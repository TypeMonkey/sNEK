package jg.cs.runtime.values;

import jg.cs.common.Type;

public class IntValue extends Value<Long> {

  public IntValue(Long actualValue) {
    super(actualValue, Type.INT);
  }

  @Override
  public String toString() {
    return actualValue.toString();
  }
}
