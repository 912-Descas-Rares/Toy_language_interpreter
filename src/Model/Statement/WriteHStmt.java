package Model.Statement;

import Exceptions.MiscException;
import Exceptions.MyException;
import Exceptions.TypeException;
import Model.ADT.MyDict;
import Model.ADT.SmartDict;
import Model.Expression.IExp;
import Model.ProgramState.ProgState;
import Model.Type.RefType;
import Model.Value.IVal;
import Model.Value.RefVal;

import java.io.IOException;

public class WriteHStmt implements IStmt{
    String var_name;
    IExp exp;

    public WriteHStmt(String v, IExp e) {
        this.var_name = v;
        this.exp = e;
    }

    @Override
    public ProgState execute(ProgState state) throws MyException, IOException {
        MyDict<String, IVal> symTbl = (MyDict<String, IVal>) state.getSymTable();
        SmartDict<Integer, IVal> heap = (SmartDict<Integer, IVal>) state.getHeap();
        if(symTbl.isDefined(var_name) && symTbl.lookup(var_name).getType().equals(new RefType(null))){
            RefVal var_val = (RefVal)symTbl.lookup(var_name);
            if(heap.isDefined(var_val.getAddr())){
                IVal exp_val = exp.eval(symTbl, heap);
                if(((RefType)var_val.getType()).getInner().equals(exp_val.getType())){
                    heap.update(var_val.getAddr(), exp_val);
                }
                else throw new TypeException("Variable ref type and expression ref type are not the same. Not ideal, sir, not ideal.");
            }
            else throw new MiscException("Address not defined in heap, lad. Did you run of scotch?");
        }
        else throw new MiscException("Variable not defined or not RefVal. You seem tired. Go home, unwind.");
        return state;
    }
    
    @Override
    public String toString() {
        return "WriteHeap(" + var_name + ", " + exp.toString() + ")";
    }
}

