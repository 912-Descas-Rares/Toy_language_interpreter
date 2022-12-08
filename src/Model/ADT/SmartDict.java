package Model.ADT;

import Exceptions.MiscException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Integer;

public class SmartDict<K extends Integer, V> implements ISmartDict<K , V> {
    private final HashMap<K, V> elems;
    private MyStack<Integer> free;
    int nextFree;

    public SmartDict() {
        this.elems = new HashMap<K, V>();
        this.free=new MyStack<Integer>();
        this.nextFree = 1;
    }
    int getFree(){
        if(free.isEmpty()) return nextFree++;
        else return free.pop();
    }
    @Override
    public K put(V value) {
        int key = getFree();
        this.elems.put((K)(Integer)key, value);
        return (K)(Integer)key;
    }
    @Override
    public void update(K id, V val) {
        this.elems.put(id, val);
    }

    @Override
    public V lookup(K key) throws MiscException {
        return this.elems.get(key);
    }

    @Override
    public V remove(K key) {
        return this.elems.remove(key);
    }

    @Override
    public boolean isDefined(K key) {
        return this.elems.containsKey(key);
    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<K, V> entry : elems.entrySet()) {
            Integer key = entry.getKey();
            V val = entry.getValue();
            result = result + key.toString() + "=" + val.toString() + "\n";
        }

        return result;
    }

    @Override
    public void setContent(Map<K, V> unsafeGarbageCollector) {
        this.elems.clear();
        this.elems.putAll(unsafeGarbageCollector);
    }

    @Override
    public Map<K, V> getContent() {
        return elems;
    }

    public void upload(List<K> freed) {
        for(K key: freed){
            free.push(key);
        }
    }
}
