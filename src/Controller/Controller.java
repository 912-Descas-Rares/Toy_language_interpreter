package Controller;

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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    IRepo<ProgState> repo;

    public Controller(IRepo<ProgState> repo){
        this.repo=repo;
    }

    public void add(IStmt stmt){
        ProgState prog = new ProgState(new MyStack<IStmt>(),new MyDict<String, IVal>(), new MyList<IVal>(),stmt,new MyDict<String, BufferedReader >(), new SmartDict<Integer, IVal>());
        repo.add(prog);
    }
    public ProgState oneStep(ProgState state) throws MyException{
        MyStack<IStmt> stk=(MyStack<IStmt>) state.getStk();
        try{
            if(stk.isEmpty()) throw  new MiscException("Program State stack is empty");
            IStmt  crtStmt = stk.pop();
            return crtStmt.execute(state);
        }
        catch(IOException e){
            throw new MiscException(e.getMessage());
        }

    }
    Map<Integer,IVal> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,IVal> heap){
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
    }
    public void allStep() throws MyException {
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
    }

    private List<Integer> getFreed(Map<Integer, IVal> t, Map<Integer, IVal> content) {
        return content.entrySet().stream()
                .filter(e->!t.containsKey(e.getKey()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
