package Controller;

import GarbageCollector.GarbageCollector;
import Model.ADT.MyDict;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Exceptions.*;
import Model.ADT.SmartDict;
import Model.ProgramState.ProgState;
import Model.Value.IntVal;
import Model.Value.RefVal;
import Repo.IRepo;
import Model.Statement.IStmt;
import Model.Value.IVal;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepo<ProgState> repo;

    ExecutorService executor;

    public Controller(IRepo<ProgState> repo){
        this.repo=repo;
    }

    public void add(IStmt stmt){
        ProgState prog = new ProgState(new MyStack<IStmt>(),new MyDict<String, IVal>(), new MyList<IVal>(),stmt,new MyDict<String, BufferedReader >(), new SmartDict<Integer, IVal>());
        repo.addThreads(prog);
    }

    /*Map<Integer,IVal> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,IVal> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey())||e.getValue() instanceof RefVal)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    int getAddrDeep(RefVal v, Map<Integer,IVal> heap){
        IVal val = heap.get(v.getAddr());
        if(val instanceof RefVal){
            return getAddrDeep((RefVal)val,heap);
        }
        else return v.getAddr();
    }
    List<Integer> getAddrFromSymTable(Collection<IVal> symTableVals, Map<Integer,IVal> hp){
        return symTableVals.stream()
                .filter(v->v instanceof RefVal)
                .map(v->{RefVal v1=(RefVal)v; return getAddrDeep(v1,hp);})
                .collect(Collectors.toList());
    }*/
    List<ProgState> removeCompletedProg(List<ProgState> inPrgList){
        return inPrgList.stream()
                .filter(p->p.isNotCompleted())
                .collect(Collectors.toList());
    }
    void oneStepForAllPrg(List<ProgState> threads) throws MyException {

        //before the execution, print the PrgState List into the log file
        try{
            threads.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
            });

            //run concurrently one step for each of the existing PrgStates
            List<Callable<ProgState>> callList = threads.stream()
                    .map((ProgState programState) -> (Callable<ProgState>)(() -> {
                        try {
                            return programState.oneStep();
                        } catch (MyException e) {
                            throw new RuntimeException(e);
                        }
                    }))
                    .toList();

            /*    List<Callable<ProgState>> callList = threads.stream()
                    .map((ProgState p) -> (Callable<ProgState>)(() -> {
                        try {
                            ProgState prog =p.oneStep();
                            List<Integer> addresses = getAddrFromSymTable(p.getSymTable().getContent().values(),p.getHeap().getContent());
                            Map<Integer,IVal> t=(unsafeGarbageCollector(addresses,p.getHeap().getContent()));
                            List<Integer> freed= getFreed(t,p.getHeap().getContent());
                            p.getHeap().setContent(t);
                            p.getHeap().upload(freed);
                            return prog;
                        } catch (MyException e) {
                            throw new RuntimeException(e);
                        }
                    }))
                    .collect(Collectors.toList());*/

            //start the execution of the callList of callables
            List<ProgState> newThreads = executor.invokeAll(callList). stream()
                    . map(future ->  {
                                try {
                                    return future.get();
                                } catch (InterruptedException | ExecutionException e) {
                                    throw new RuntimeException(e);
                                }
                    })
                    .filter(p -> p!=null)
                    .collect(Collectors.toList());

            //add the new created threads to the list of existing threads
                Set<ProgState> set = new HashSet<>(); // remove duplicates
                set.addAll(threads);
                set.addAll(newThreads);
                threads.clear();
                threads.addAll(set);
            // OLD threads.addAll(newThreads);

            //after the execution, print the PrgState List into the log file
            threads.forEach(prg -> {
                try {
                    repo.logPrgStateExec(prg);
                } catch (MyException e) {
                    throw new RuntimeException(e);
                }
            });

            //Save the current programs in the repository
            MyList<ProgState> threadsList = new MyList<ProgState>();
            threadsList.setContent(threads);
            repo.setThreads(threadsList);
        }
        catch (InterruptedException| RuntimeException e){
            throw new MyException(e.getMessage());
        }
    }
    public void allStep() throws MyException  {

        executor = Executors.newFixedThreadPool(2);
        List<ProgState> threads = removeCompletedProg(repo.getThreads().getAll());
        GarbageCollector garbageCollector = new GarbageCollector();

        while(threads.size() > 0){
            garbageCollector.collectGarbage(threads);
            oneStepForAllPrg(threads);
            threads = removeCompletedProg(repo.getThreads().getAll());
        }

        executor.shutdownNow();
        MyList<ProgState> threadsList = new MyList<ProgState>();
        threadsList.setContent(threads);
        repo.setThreads(threadsList);


        /*executor= Executors.newFixedThreadPool(2);
        //here we remove completed programs
        //finished homework? Go home!
        List<ProgState> threads = removeCompletedPrg(repo.getThreads().getAll());
        while(threads.size()>0){
            oneStepForAllPrg(threads);
            threads = removeCompletedPrg(repo.getThreads().getAll());
            executor.shutdownNow();
            MyList<ProgState> threadsList = new MyList<ProgState>();
            threadsList.setContent(threads);
            repo.setThreads(threadsList);

        }*/
    }
    /*public void allStep() throws MyException {
        ProgState prog = repo.getCrtPrg();
        repo.logPrgStateExec();
        while (!prog.getStk().isEmpty()){
            this.oneStep(prog);
            //repo.logPrgStateExec();
            List<Integer> addresses = getAddrFromSymTable(prog.getSymTable().getContent().values(),prog.getHeap().getContent());
            Map<Integer,IVal> t=(unsafeGarbageCollector(addresses,prog.getHeap().getContent()));
            List<Integer> freed= getFreed(t,prog.getHeap().getContent());
            prog.getHeap().setContent(t);
            prog.getHeap().upload(freed);
            repo.logPrgStateExec();

        }
        System.out.println(prog);
    }*/

    private List<Integer> getFreed(Map<Integer, IVal> t, Map<Integer, IVal> content) {
        return content.entrySet().stream()
                .filter(e->!t.containsKey(e.getKey()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
