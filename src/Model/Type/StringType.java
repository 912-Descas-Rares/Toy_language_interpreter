package Model.Type;

import Model.Value.IVal;
import Model.Value.StringVal;

public class StringType implements IType{

    public static IVal defaultValue() {
        return new StringVal("");
    }

    @Override
    public boolean equals(Object another){
        return another instanceof StringType;
    }
    @Override
    public String toString() { return "string";}
}
