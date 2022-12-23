package Model.Statement;

import Exceptions.MyException;
import Model.ADT.MyDict;
import Model.ProgramState.ProgState;
import Model.Type.IType;

public class NoOpStmt implements IStmt{
    @Override
    public ProgState execute(ProgState state) {
        return null;
    }

    @Override
    public MyDict<String, IType> typecheck(MyDict<String, IType> typeEnv) throws MyException {
        return typeEnv;
    }
}
