package Model.Type;

import Model.Value.IVal;
import Model.Value.IntVal;

public class IntType implements  IType{
    public static IVal defaultValue() {
        return new IntVal(0);
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof IntType;
    }
    @Override
    public String toString() { return "int";}
}
