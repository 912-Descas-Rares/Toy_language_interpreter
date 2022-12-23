package Model.Statement;

import Exceptions.MyException;
import Model.ADT.IDict;
import Model.ADT.IStack;
import Model.ADT.MyDict;
import Model.ADT.MyStack;
import Model.ProgramState.ProgState;
import Model.Type.IType;
import Model.Value.IVal;
import Model.Value.StringVal;

import java.io.IOException;

public class ForkStmt  implements IStmt{
    IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }


    @Override
    public ProgState execute(ProgState state) throws MyException {  //TODO reference for all but symTable
        MyDict<String,IVal> tempSymTable= new MyDict<String,IVal>();
        tempSymTable.setContent(state.getSymTable().getContent());
        IStack<IStmt> hisStack= new MyStack<IStmt>();
        return new ProgState(hisStack,
                tempSymTable,
                state.getOut(),
                stmt,
                state.getFileTable(),
                state.getHeap());
    }

    @Override
    public MyDict<String, IType> typecheck(MyDict<String, IType> typeEnv) throws MyException {
        stmt.typecheck(typeEnv);
        return typeEnv;
    }
}
