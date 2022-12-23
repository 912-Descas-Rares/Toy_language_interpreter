package Model.Statement;

import Exceptions.MyException;
import Model.ADT.MyDict;
import Model.ADT.MyStack;
import Model.ProgramState.ProgState;
import Model.Type.IType;

public class CompStmt implements IStmt{
    IStmt first, second;

    public CompStmt(IStmt iStmt1, IStmt iStmt2) {
        first=iStmt1;
        second=iStmt2;
    }

    public String toString()
    {           return "Composite("+first.toString() + "; " + second.toString()+")";            }
    @Override
    public ProgState execute(ProgState state) {
        MyStack<IStmt> stk=(MyStack<IStmt>) state.getStk();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public MyDict<String, IType> typecheck(MyDict<String, IType> typeEnv) throws MyException {
        return second.typecheck(first.typecheck(typeEnv));
    }
}
