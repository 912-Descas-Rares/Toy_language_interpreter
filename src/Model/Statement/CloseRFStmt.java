package Model.Statement;

import Exceptions.MiscException;
import Exceptions.MyException;
import Exceptions.TypeException;
import Model.ADT.MyDict;
import Model.ADT.SmartDict;
import Model.Expression.IExp;
import Model.ProgramState.ProgState;
import Model.Type.IType;
import Model.Type.StringType;
import Model.Value.IVal;
import Model.Value.StringVal;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFStmt implements IStmt{
    IExp exp;


    public CloseRFStmt(IExp e){
        this.exp=e;
    }
    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyDict<String,IVal> symTbl= (MyDict<String,IVal> )state.getSymTable();
        MyDict<String, BufferedReader> fileTable= (MyDict<String, BufferedReader>)state.getFileTable();
        SmartDict<Integer, IVal> heap = (SmartDict<Integer, IVal>) state.getHeap();

        try {
            IVal val = exp.eval(symTbl,heap);
            if (val.getType().equals(new StringType())) {
                String s = ((StringVal) val).getVal();
                if (fileTable.isDefined(s)) {
                    BufferedReader buffer = fileTable.lookup(s);
                    buffer.close();
                    fileTable.remove(s);
                } else throw new MiscException("File not in file table, maybe look in another one? What a mess");
            } else throw new TypeException("File name should be a string, you know?");
            return null;
        }
        catch (IOException e){
            throw new MiscException(e.getMessage());
        }
    }

    @Override
    public MyDict<String, IType> typecheck(MyDict<String, IType> typeEnv) throws MyException {
        IType type = exp.typecheck(typeEnv);
        if(type.equals(new StringType())){
            return typeEnv;
        }
        else throw new TypeException("File name should be a string, you know?");
    }

    @Override
    public String toString() {
        return "closeRFile("+exp.toString()+")";
    }
}
