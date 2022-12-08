package Model.ProgramState;

import Model.ADT.*;
import Model.Statement.IStmt;
import Model.Value.IVal;

import java.io.BufferedReader;

public class ProgState {
    IStack<IStmt> exeStack;
    IDict<String, IVal> symTable;
    IDict<String, BufferedReader> fileTable;
    ISmartDict<java.lang.Integer, IVal> heap;
    IList<IVal> out;
    IStmt   originalProgram;
    public IStack<IStmt> getStk() {
        return this.exeStack;
    }
    public IDict<String, IVal> getSymTable() {return this.symTable;}
    public IDict<String, BufferedReader> getFileTable() {return this.fileTable;}
    public ISmartDict <java.lang.Integer, IVal> getHeap() {return this.heap;}
    public IList<IVal> getOut() {
        return out;
    }

    public ProgState(IStack<IStmt> stk, IDict<String, IVal> symtbl, IList<IVal> ot, IStmt prog,IDict<String, BufferedReader> ft , ISmartDict <Integer, IVal> hp) {
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        originalProgram=deepCopy(prog); //recreate the entire original prog
        stk.push(prog);
        fileTable=ft;
        heap=hp;
        }

    private IStmt deepCopy(IStmt prog) { //Probably not the best way to do this
        IStmt c = prog;
        return c;
    }
    public String toString() {
        return "Symbol table: \n" + symTable.toString() + "\n|" + "Exe stack:\n" + exeStack.toString() + "\n|" + "Output\n" + out.toString()+ "File table\n" + fileTable.toString()+ "Heap\n" + heap.toString();
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }
}
