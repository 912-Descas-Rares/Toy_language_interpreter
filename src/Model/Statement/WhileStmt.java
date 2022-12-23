package Model.Statement;

import Exceptions.MyException;
import Model.ADT.*;
import Model.Expression.IExp;
import Model.ProgramState.ProgState;
import Model.Type.BoolType;
import Model.Type.IType;
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
            return null;
        } else {
            throw new MyException("Condition is not a boolean, BOO");
        }
    }

    @Override
    public MyDict<String, IType> typecheck(MyDict<String, IType> typeEnv) throws MyException {
        IType typeExp = exp.typecheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            stmt.typecheck(typeEnv);
            return typeEnv;
        } else {
            throw new MyException("The condition of WHILE is not of bool type. I don't know what you had in mind.");
        }
    }

    @Override
    public String toString() {
        return "while(" + exp.toString() + "){" + stmt.toString() + "}";
    }
}
