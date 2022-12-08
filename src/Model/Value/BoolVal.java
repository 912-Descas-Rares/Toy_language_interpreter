package Model.Value;

import Model.Type.BoolType;
import Model.Type.IType;

public class BoolVal implements IVal{
    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public IVal defaultValue() {
        return new BoolVal(false);
    }

    boolean val;
    public BoolVal(boolean v){val=v;}
    public boolean getVal() {return val;}
    @Override
    public String toString() {return Boolean.toString(val);}
}
