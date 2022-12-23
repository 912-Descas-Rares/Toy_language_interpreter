package Model.Expression;

import Exceptions.MyException;
import Model.ADT.MyDict;
import Model.ADT.SmartDict;
import Model.Type.IType;
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
    public IType typecheck(MyDict<String, IType> typeEnv) throws MyException {
        IType type = exp.typecheck(typeEnv);
        if(type instanceof RefType){
            RefType refType = (RefType)type;
            return refType.getInner();
        }
        else throw new MyException("How did a non ref value get into a read heap expression? Something's off and i can feel it.");
    }

    @Override
    public String toString() {
        return "rH(" + exp.toString()  + ")";
    }
}
