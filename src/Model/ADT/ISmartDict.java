package Model.ADT;

import Exceptions.MiscException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ISmartDict<K,V>{
    K put(V value);
    V lookup(K key) throws MiscException;
    V remove(K key);
    boolean isDefined(K id);
    void update(K id, V val);
    String toString();

    void setContent(Map<K, V> unsafeGarbageCollector);

    Map<K,V> getContent();


    void upload(List<K> freed);

    Collection<Integer> getAllAddresses();
}
