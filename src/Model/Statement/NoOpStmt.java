package Model.Statement;

import Model.ProgramState.ProgState;

public class NoOpStmt implements IStmt{
    @Override
    public ProgState execute(ProgState state) {
        return state;
    }
}
