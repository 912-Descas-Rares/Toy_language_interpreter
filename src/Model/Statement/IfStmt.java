package Model.Statement;

import Model.ADT.MyDict;
import Model.ADT.SmartDict;
import Model.Expression.IExp;
import Exceptions.*;
import Model.ProgramState.ProgState;
import Model.Value.BoolVal;
import Model.Value.IVal;

import java.io.IOException;

public class IfStmt implements IStmt{
    IExp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(IExp e, IStmt stmt1, IStmt stmt2) {
        exp=e;
        thenS=stmt1;
        elseS=stmt2;
    }

    @Override
    public String toString(){
        return "(if("+ exp.toString()+") then(" +thenS.toString()+") else("+elseS.toString()+"))";}
    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyDict<String, IVal> symTbl= (MyDict<String, IVal>) state.getSymTable();
        SmartDict<Integer, IVal> heap = (SmartDict<Integer, IVal>) state.getHeap();
        try{
            BoolVal logic = (BoolVal)exp.eval(symTbl,heap);
            if(logic.getVal())
            {
                thenS.execute(state);
            }
            else{
                elseS.execute(state);
            }
            return null;
        }
        catch(IOException e){
            throw new MyException(e.getMessage());
        }
    }
}
