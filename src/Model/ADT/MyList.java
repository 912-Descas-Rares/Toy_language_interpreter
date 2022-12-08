package Model.ADT;


import java.util.Vector;

public class MyList<T> implements IList<T>{
    Vector<T> elems;
    public MyList()
    {
        this.elems= new Vector<T>();

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
    public Vector<T> getAll() {
        return elems;
    }

    @Override
    public String toString(){
        String result = "";
        for( T val : elems )
            result = result + (val.toString() + "\n");
        return result;
    }
}
