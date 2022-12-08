package Model.Statement;

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

public class NewHStmt implements IStmt{

    String var_name;
    IExp exp;

    public NewHStmt(String v, IExp valExp) {
        this.var_name = v;
        this.exp = valExp;
    }

    @Override
    public ProgState execute(ProgState state) throws MyException, IOException {
        MyDict<String, IVal> symTbl = (MyDict<String, IVal>) state.getSymTable();
        SmartDict<Integer, IVal> heap = (SmartDict<Integer, IVal>) state.getHeap();
        if(symTbl.isDefined(var_name) && symTbl.lookup(var_name).getType().equals(new RefType(null))){
            RefVal var_val = (RefVal) symTbl.lookup(var_name);
            IVal exp_val = exp.eval(symTbl, heap);
            if(((RefType)var_val.getType()).getInner().equals(exp_val.getType())){
                int t= heap.put(exp_val);
                symTbl.update(var_name, new RefVal(t, exp_val.getType()));
            }
            else throw new TypeException("Variable ref type and expression ref type are not the same. Not ideal, sir, not ideal.");
        }
        else throw new TypeException("Variable not defined in symbol table, lad. Did you run of scotch?");

        return state;
    }
    @Override
    public String toString(){
        return "new("+var_name+","+exp.toString()+")";
    }
}
