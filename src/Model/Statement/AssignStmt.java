package Model.Statement;

import Model.ADT.MyDict;
import Model.ADT.MyStack;
import Model.ADT.SmartDict;
import Model.Expression.IExp;
import Exceptions.*;
import Model.ProgramState.ProgState;
import Model.Type.IType;
import Model.Value.IVal;

public class AssignStmt implements IStmt{
    String id;
    IExp exp;

    public AssignStmt(String v, IExp iExp) {
        id=v;
        exp=iExp;
    }

    @Override
    public String toString(){ return id+"="+ exp.toString();}

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyDict<String, IVal> symTbl= (MyDict<String, IVal>) state.getSymTable();
        SmartDict<Integer, IVal> heap = (SmartDict<Integer, IVal>) state.getHeap();

        if (symTbl.isDefined(id)) {
            IVal val = exp.eval(symTbl,heap);
            IType typId = (symTbl.lookup(id)).getType();
            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else
                throw new TypeException("declared type of variable" + id + " and type of the assigned expression do not match");
        }
        else throw new VarException("the used variable" +id + " was not declared before");
        return null;
    }

    @Override
    public MyDict<String, IType> typecheck(MyDict<String, IType> typeEnv) throws MyException {
        IType typeVar = typeEnv.lookup(id);
        IType typeExp = exp.typecheck(typeEnv);
        if (typeVar.equals(typeExp))
            return typeEnv;
        else
            throw new TypeException("Variable is not of value type, don't drink and drive!");
    }
    }

