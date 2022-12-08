package Model.Expression;

import Exceptions.MyException;
import Model.ADT.MyDict;
import Exceptions.MiscException;
import Model.ADT.SmartDict;
import Model.Value.IVal;

public class VarExp implements IExp{
    String id;

    public VarExp(String v) {
        id=v;
    }

    @Override
    public IVal eval(MyDict<String, IVal> tbl, SmartDict<Integer,IVal> hp) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
