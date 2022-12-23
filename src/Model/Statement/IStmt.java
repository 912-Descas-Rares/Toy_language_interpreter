package Model.Statement;

import Exceptions.*;
import Model.ADT.MyDict;
import Model.ProgramState.ProgState;
import Model.Type.IType;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStmt {
    ProgState execute(ProgState state) throws MyException, IOException;

    MyDict<String, IType> typecheck(MyDict<String, IType> typeEnv) throws MyException;
}
