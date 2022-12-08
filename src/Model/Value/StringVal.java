package Model.Value;

import Model.Type.IType;
import Model.Type.StringType;

public class StringVal implements IVal{
    String val;
    @Override
    public IType getType() {
        return new StringType();
    }
    public StringVal (String s)
    {
        this.val=s;
    }
    @Override
    public IVal defaultValue() {
        return new StringVal("");
    }

    public String getVal() {return val;}
    @Override
    public String toString() {return val;}
}
