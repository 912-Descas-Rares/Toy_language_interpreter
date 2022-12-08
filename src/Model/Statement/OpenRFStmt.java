package Model.Statement;

import Exceptions.MiscException;
import Exceptions.MyException;
import Exceptions.TypeException;
import Model.ADT.MyDict;
import Model.ADT.SmartDict;
import Model.Expression.IExp;
import Model.ProgramState.ProgState;
import Model.Type.StringType;
import Model.Value.IVal;
import Model.Value.StringVal;

import java.io.*;

public class OpenRFStmt implements  IStmt{
    IExp exp;

    public OpenRFStmt(IExp exp){
        this.exp=exp;
    }
    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyDict<String, IVal> symTbl = (MyDict<String, IVal>) state.getSymTable();
        SmartDict<Integer, IVal> heap = (SmartDict<Integer, IVal>) state.getHeap();
        MyDict<String, BufferedReader> fileTable = (MyDict<String, BufferedReader>) state.getFileTable();
        try{
            IVal val = exp.eval(symTbl,heap);
            if (val.getType().equals(new StringType())) {
                String file = ((StringVal) val).getVal();
                if (!fileTable.isDefined(file)) {
                    BufferedReader buffer = new BufferedReader(new FileReader(file));
                    fileTable.put(file, buffer);
                } else throw new MiscException("You cannot peel a banana twice! ﴾͡๏̯͡๏﴿ ");
            } else throw new TypeException("File name must be a string, you fool! (ง'̀-'́)ง");
            return state;
        }
        catch (FileNotFoundException e){
            throw new MiscException(e.getMessage());
        }

    }
    @Override
    public String toString() {
        return "openRFile("+exp.toString()+")";
    }
}
