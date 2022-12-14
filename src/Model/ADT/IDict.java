package Model.ADT;

import Exceptions.MiscException;

import java.util.Collection;
import java.util.Map;

public interface IDict<K,V> {

    V put(K key, V value);
    V lookup(K key) throws MiscException;
    V remove(K key);
    boolean isDefined(K id);
    void update(K id, V val);
    String toString();
    Map<K, V> getContent();

    Collection<Integer> getAllAddresses();
}
