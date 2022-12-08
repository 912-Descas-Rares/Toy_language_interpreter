package Model.Value;

import Model.Type.IType;
import Model.Type.IntType;

public class IntVal implements IVal {
    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public  IVal defaultValue() {
        return new IntVal(0);
    }

    int val;
    public IntVal(int v){val=v;}
    public int getVal() {return val;}
    @Override
    public String toString() {return Integer.toString(val);}

}
