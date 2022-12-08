package Model.Expression;

import Exceptions.MyException;
import Model.ADT.MyDict;
import Model.ADT.SmartDict;
import Model.Type.RefType;
import Model.Value.IVal;
import Model.Value.RefVal;

public class ReadHExp implements IExp{
    IExp exp;

    public ReadHExp(IExp e){
        this.exp = e;
    }

    @Override
    public IVal eval(MyDict<String, IVal> tbl, SmartDict<Integer, IVal> hp) throws MyException {
        IVal val = exp.eval(tbl, hp);
        if(val.getType().equals(new RefType(null))){
            RefVal refVal = (RefVal)val;
            if(hp.isDefined(refVal.getAddr())){
                return hp.lookup(refVal.getAddr());
            }
            else throw new MyException("Address not defined in heap, lad. Did you run of scotch?");
        }
        else throw new MyException("Variable ref type and expression ref type are not the same. Not ideal, sir, not ideal.");
    }
    @Override
    public String toString() {
        return "rH(" + exp.toString()  + ")";
    }
}
