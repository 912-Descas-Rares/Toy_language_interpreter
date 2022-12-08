package Repo;

import Exceptions.MiscException;
import Exceptions.MyException;
import Model.ADT.*;
import Model.ProgramState.ProgState;
import Model.Statement.IStmt;
import Model.Value.IVal;

import java.io.*;
import java.util.Vector;

public class MyRepo<T> implements IRepo<T>{

    MyList<T> elems;
    String filename;
    public MyRepo(String filename) {
        this.elems = new MyList<T>();
        this.filename=filename;
    }

    @Override
    public int getSize() {
        return elems.size();
    }

    @Override
    public Vector<T> getAll() {
        return elems.getAll();
    }

    @Override
    public T get(int i) throws Exception {
        return elems.get(i);
    }

    @Override
    public void add(T e){
        elems.add(e);
    }

    @Override
    public void remove(T e) {
        elems.remove(e);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void logPrgStateExec() throws MyException {
        try {
            ProgState prog = (ProgState) getCrtPrg();
            MyStack<IStmt> ExeStack = (MyStack<IStmt>) prog.getStk();
            MyDict<String, IVal> SymTable = (MyDict<String, IVal>) prog.getSymTable();
            MyList<IVal> OutList =(MyList<IVal>) prog.getOut();
            MyDict<String, BufferedReader> FileTable =( MyDict<String, BufferedReader>) prog.getFileTable();
            SmartDict<Integer,IVal> Heap = (SmartDict<Integer, IVal>) prog.getHeap();
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            logFile.println("");
            logFile.println("ExeStack:");
            logFile.println(ExeStack.toString());
            logFile.println("SymTable:");
            logFile.println(SymTable.toString());
            logFile.println("Out:");
            logFile.println(OutList.toString());
            logFile.println("FileTable:");
            logFile.println(FileTable.toString());
            logFile.println("Heap:");
            logFile.println(Heap.toString());
            logFile.close();
        }
        catch( IOException e){
            throw new MiscException(e.getMessage());
        }
    }

    @Override
    public T getCrtPrg() {
        return elems.get(elems.size()-1);
    }
}
