package Model.ADT;

import java.util.Stack;

public class MyStack<T> implements IStack<T>{
    //int size,capacity;
    Stack<T> elems;

    public MyStack ()
    {
    this.elems= new Stack<T>();
    }
    @Override
    public void push(T elem) {
    this.elems.push(elem);
    }

    public T pop(){
        return this.elems.pop();
    }

    @Override
    public boolean isEmpty() {
        return elems.isEmpty();
    }

    @Override
    public String toString(){
        String result = "";
        for( T val : elems )
            result =  (val.toString() + "\n") + result;
        return result;
    }
}
