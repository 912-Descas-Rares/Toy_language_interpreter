package Model.ADT;

import java.util.Vector;

public interface IList<T> {
    void add(T elem);
    void remove(T elem);
    T get(int pos);
    void remove(int pos);
    int size();
    Vector<T> getAll();
    String toString();
}
