package Repo;

import Exceptions.MyException;
import Model.ADT.MyList;
import Model.ProgramState.ProgState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public interface IRepo<T> {

    int getSize();
    ArrayList<T> getAll();
    T get(int i)throws Exception;
    void add( T e);
    void remove( T e) throws Exception;
    boolean isFull();
    void logPrgStateExec(ProgState prog) throws MyException;
    MyList<ProgState> getThreads();
    void setThreads(MyList<ProgState> threads);
    T getCrtPrg();

    void addThreads(ProgState prog);
}
