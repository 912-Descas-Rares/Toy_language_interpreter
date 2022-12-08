package Model.Type;

import Model.Value.BoolVal;
import Model.Value.IVal;

public class BoolType implements  IType{

    public static IVal defaultValue() {
        return new BoolVal(false);
    }
    @Override
    public boolean equals(Object another) {
        return another instanceof BoolType;
    }
    @Override
    public String toString() { return "boolean";}
}
