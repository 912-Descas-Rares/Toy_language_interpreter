package Model.Statement;

import Exceptions.MyException;
import Model.ADT.*;
import Model.Expression.IExp;
import Model.ProgramState.ProgState;
import Model.Type.BoolType;
import Model.Value.BoolVal;
import Model.Value.IVal;

import java.io.BufferedReader;
import java.io.IOException;

public class WhileStmt implements IStmt {


    IExp exp;
    IStmt stmt;

    public WhileStmt(IExp e, IStmt s) {
        exp = e;
        stmt = s;
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyStack<IStmt> exeStack = (MyStack<IStmt>) state.getStk();
        MyDict<String, IVal> symTable = (MyDict<String, IVal>) state.getSymTable();
        SmartDict<Integer, IVal> heap = (SmartDict<Integer, IVal>) state.getHeap();
        IVal val = exp.eval(symTable, heap);
        if (val.getType().equals(new BoolType())) {
            BoolVal cond = (BoolVal) val;
            if (cond.getVal()) {
                exeStack.push(this);
                exeStack.push(stmt);
            }
            return state;
        } else {
            throw new MyException("Condition is not a boolean, BOO");
        }
    }

    @Override
    public String toString() {
        return "while(" + exp.toString() + "){" + stmt.toString() + "}";
    }
}
