package Repo;

import Exceptions.MiscException;
import Exceptions.MyException;
import Model.ADT.*;
import Model.ProgramState.ProgState;
import Model.Statement.IStmt;
import Model.Value.IVal;

import java.io.*;
import java.util.ArrayList;

public class MyRepo<T> implements IRepo<T> {
    int thread_id=1;
    MyList<T> elems;
    MyList<ProgState> threads;
    String filename;
    public MyRepo(String filename) {
        this.elems = new MyList<T>();
        this.threads = new MyList<ProgState>();
        this.filename=filename;
    }

    @Override
    public int getSize() {
        return elems.size();
    }

    @Override
    public ArrayList<T> getAll() {
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
    public void logPrgStateExec(ProgState prog) throws MyException {
        try {
            //ProgState prog = (ProgState) getCrtPrg();
            MyStack<IStmt> ExeStack = (MyStack<IStmt>) prog.getStk();
            MyDict<String, IVal> SymTable = (MyDict<String, IVal>) prog.getSymTable();
            MyList<IVal> OutList =(MyList<IVal>) prog.getOut();
            MyDict<String, BufferedReader> FileTable =( MyDict<String, BufferedReader>) prog.getFileTable();
            SmartDict<Integer,IVal> Heap = (SmartDict<Integer, IVal>) prog.getHeap();
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            logFile.println("Id: " + prog.getId());
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
    public MyList<ProgState> getThreads() {
        return threads;
    }


    @Override
    public void addThreads(ProgState prog){
        prog.setId(thread_id);
        threads.add(prog);
        thread_id++;
    }

    @Override
    public void setThreads(MyList<ProgState> thr) {
        for(ProgState e : thr.getAll()){
            if(e.getId()==0){
                e.setId(thread_id);
                thread_id++;
            }
        }
        this.threads = thr;
    }

    @Override
    public T getCrtPrg() {
        return elems.get(elems.size()-1);
    }
}
