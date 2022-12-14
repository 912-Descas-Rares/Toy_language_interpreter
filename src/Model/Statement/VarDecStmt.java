package Model.Statement;

import Exceptions.MyException;
import Model.ADT.MyDict;
import Model.ADT.MyStack;
import Model.ProgramState.ProgState;
import Exceptions.VarException;
import Model.Type.*;
import Model.Value.IVal;
import Model.Value.RefVal;

public class VarDecStmt implements IStmt{
    String id;
    IType type;
    public VarDecStmt(String v, IType iType) {
        id=v;
        type=iType;
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyDict<String, IVal> symTbl=(MyDict<String, IVal>) state.getSymTable();
        if (!symTbl.isDefined(id)) {
            if (type.equals(new IntType()))
                symTbl.put(id, IntType.defaultValue());
            else if (type.equals(new BoolType()))
                symTbl.put(id, BoolType.defaultValue());
            else if (type.equals(new StringType()))
                symTbl.put(id, StringType.defaultValue());
            else if(type.equals(new RefType(null)))
                symTbl.put(id, RefType.defaultValue(((RefType)type).getInner()));
            else throw new VarException("Weird type, i don't want to deal with it");
        }
        else throw new VarException("You never told me about " +id + " (ﾟ∩ﾟ)");
        return null;
    }

    @Override
    public String toString() {
        return type.toString() +" "+id;
    }
}
