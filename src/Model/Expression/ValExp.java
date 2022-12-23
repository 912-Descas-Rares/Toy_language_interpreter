package Model.Expression;

import Exceptions.MyException;
import Model.ADT.MyDict;
import Model.ADT.SmartDict;
import Model.Type.IType;
import Model.Value.IVal;

public class ValExp implements IExp{
    IVal e;

    public ValExp(IVal iVal) {
        e=iVal;
    }

    @Override
    public IVal eval(MyDict<String, IVal> tbl, SmartDict<Integer,IVal> hp) {
        return e;
    }

    @Override
    public IType typecheck(MyDict<String, IType> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public String toString() {
        return e.toString();
    }
}
