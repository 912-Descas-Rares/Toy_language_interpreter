package Repo;

import Exceptions.MyException;

import java.io.IOException;
import java.util.Vector;

public interface IRepo<T> {

    int getSize();
    Vector<T> getAll();
    T get(int i)throws Exception;
    void add( T e);
    void remove( T e) throws Exception;
    boolean isFull();
    void logPrgStateExec() throws MyException;
    T getCrtPrg();
}
