package Model.ADT;

import Exceptions.MiscException;

import java.util.HashMap;
import java.util.Map;

public class MyDict<K,V> implements IDict<K,V>{

    HashMap<K,V> elems;
    public MyDict()
    {
        this.elems= new HashMap<K,V>();
    }
    @Override
    public V put(K key, V value) {
        return this.elems.put(key,value);
    }

    @Override
    public V lookup(K key) throws MiscException {
        if(elems.containsKey(key))
            return this.elems.get(key);
        else throw new MiscException("key not found");
    }

    @Override
    public V remove(K key) {
        return this.elems.remove(key);
    }

    @Override
    public boolean isDefined(K id) {
        return this.elems.containsKey(id);
    }

    @Override
    public void update(K id, V val) {
        this.elems.put(id,val);
    }
    @Override
    public String toString(){
        String result = "";
        for (Map.Entry<K, V> entry : elems.entrySet()) {
            K key = entry.getKey();
            V val = entry.getValue();
            result = result + key.toString() + "=" + val.toString() + "\n";
        }

        return result;
    }

    @Override
    public Map<K, V> getContent() {
        return elems;
    }
}
