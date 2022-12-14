package Model.ADT;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public interface IList<T> {
    void add(T elem);
    void remove(T elem);
    T get(int pos);
    void remove(int pos);
    int size();
    ArrayList<T> getAll();
    String toString();

}
