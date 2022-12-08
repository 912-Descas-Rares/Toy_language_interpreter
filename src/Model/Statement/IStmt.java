package Model.Statement;

import Exceptions.*;
import Model.ProgramState.ProgState;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStmt {
    ProgState execute(ProgState state) throws MyException, IOException;
}
