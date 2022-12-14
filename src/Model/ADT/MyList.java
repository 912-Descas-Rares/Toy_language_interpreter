package Model.ADT;


import java.util.*;

public class MyList<T> implements IList<T>{
    ArrayList<T> elems;
    public MyList()
    {
        this.elems= new ArrayList<T>();

    }
    @Override
    public void add(T elem) {
        elems.add(elem);
    }

    @Override
    public void remove(T elem) {
        elems.remove(elem);
    }

    @Override
    public T get(int pos) {
        return elems.get(pos);
    }

    @Override
    public void remove(int pos) {
        elems.remove(pos);
    }

    @Override
    public int size() {
        return elems.size();
    }

    @Override
    public ArrayList<T> getAll() {
        return elems;
    }

    @Override
    public String toString(){
        String result = "";
        for( T val : elems )
            result = result + (val.toString() + "\n");
        return result;
    }


    public void setContent(List<T> elems) {
        this.elems= (ArrayList<T>) elems;
    }
}
