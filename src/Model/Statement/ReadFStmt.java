package Model.Statement;

import Exceptions.MiscException;
import Exceptions.MyException;
import Exceptions.TypeException;
import Model.ADT.MyDict;
import Model.ADT.SmartDict;
import Model.Expression.IExp;
import Model.ProgramState.ProgState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IVal;
import Model.Value.IntVal;
import Model.Value.StringVal;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class ReadFStmt implements  IStmt{
    IExp exp;
    String varName;
    public ReadFStmt(IExp e, String v){
        this.exp=e;
        this.varName=v;
    }
    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyDict<String, IVal> symTbl=(MyDict<String, IVal>) state.getSymTable();
        SmartDict<Integer, IVal> heap = (SmartDict<Integer, IVal>) state.getHeap();
        MyDict<String, BufferedReader> fileTable= (MyDict<String, BufferedReader>) state.getFileTable();
        try{
            if (symTbl.isDefined(varName) && symTbl.lookup(varName).getType().equals(new IntType()) ) {
                IVal val= exp.eval(symTbl,heap);
                if (val.getType().equals(new StringType())){
                    String file = ((StringVal) val).getVal();
                    BufferedReader buffer = fileTable.lookup(file);
                    String s=buffer.readLine();
                    int value;
                    if(Objects.equals(s, "")){
                        value=((IntVal)IntType.defaultValue()).getVal();
                    }
                    else {
                        value=Integer.parseInt(s);
                    }
                    symTbl.update(varName, new IntVal(value));
                }
                else throw new TypeException("The file should have a name, you know? (ﾟ∩ﾟ)");
            }else throw new TypeException("Only int variables can be assigned, we still dumb ಠ⌣ಠ");
            return state;
        }
        catch (IOException e){
            throw new MiscException(e.getMessage());
        }
    }
    @Override
    public String toString() {
        return "readFile("+exp.toString()+','+varName+")";
    }
}
