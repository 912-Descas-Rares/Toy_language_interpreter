package GarbageCollector;

import Model.ADT.MyDict;
import Model.ADT.SmartDict;
import Model.ProgramState.ProgState;
import Model.Value.IVal;
import Model.Value.RefVal;

import java.util.*;
import java.util.stream.Collectors;

public class GarbageCollector {
    public void collectGarbage(List<ProgState> threads) {
        //sets do not allow duplicates, so we won't have to deal with them ourselves
        Set<Integer> allAdresses = new HashSet<>();

        for(ProgState thread : threads) {
            SmartDict<Integer, IVal> heap = (SmartDict<Integer, IVal>) thread.getHeap();
            MyDict<String,IVal> symTable = (MyDict<String, IVal>) thread.getSymTable();
            allAdresses.addAll(heap.getAllAddresses());
            allAdresses.addAll(symTable.getAllAddresses());
        }
        List<Integer> goodAddresses= new ArrayList<>(allAdresses);

        for(ProgState thread : threads) {
            SmartDict<Integer, IVal> heap = (SmartDict<Integer, IVal>) thread.getHeap();
            Map<Integer,IVal> oldHeap = heap.getContent();
            Map<Integer,IVal> newHeap = unsafeGarbageCollector(oldHeap,goodAddresses);
            heap.setContent(newHeap);
        }

    }

    private Map<Integer, IVal> unsafeGarbageCollector(Map<Integer, IVal> oldHeap, List<Integer> goodAddresses) {
        return oldHeap.entrySet().stream()
                .filter(e->goodAddresses.contains(e.getKey())||e.getValue() instanceof RefVal)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

}




