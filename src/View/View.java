package View;

import Controller.Controller;
import Model.Expression.ArithExp;
import Model.Expression.ValExp;
import Model.Expression.VarExp;
import Exceptions.*;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolVal;
import Model.Value.IntVal;

import java.io.IOException;
import java.util.Scanner;

public class View {
    Controller c;

    public View(Controller c){
        this.c=c;
    }

    void printMenu(){
        System.out.println("1 ex1, 2 for ex2, 3 for ex3, uneventful, i know");
    }

    public void execute()
    {
        printMenu();
        Scanner in= new Scanner(System.in);
        int command = in.nextInt();
        if(command==1){
            IStmt ex1 = new CompStmt(new VarDecStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValExp(new IntVal(2))), new PrintStmt(new VarExp("v"))));

            c.add(ex1);
            try {
                System.out.println("Example1: int v; v=2;Print(v)");
                c.allStep();

            } catch (MyException e){
                System.out.println(e.what());
            }
        }
        else if(command==2){
            IStmt ex2 = new CompStmt(new VarDecStmt("a", new IntType()),
                    new CompStmt(new VarDecStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a", new ArithExp(1, new ValExp(new IntVal(2)), new ArithExp(3, new ValExp(new IntVal(3)), new ValExp(new IntVal(5))))),
                                    new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValExp(new IntVal(1)))), new PrintStmt(new VarExp("b"))))));
            c.add(ex2);
            try {
                System.out.println("Example2: int a;int b; a=2+3*5;b=a+1;Print(b)");
                c.allStep();
            } catch (MyException e){
                System.out.println(e.what());
            }
        }
        else if(command==3){
            IStmt ex3 = new CompStmt(new VarDecStmt("a",new BoolType()),
                    new CompStmt(new VarDecStmt("v", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValExp(new BoolVal(true))),
                                    new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValExp(new IntVal(2))), new AssignStmt("v", new ValExp(new IntVal(3)))), new PrintStmt(new
                                            VarExp("v"))))));
            c.add(ex3);
            try {
                System.out.println("Example3: bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
                c.allStep();
            } catch (MyException e){
                System.out.println(e.what());
            }
        }
        else {
            System.out.println("WHAT DID YOU THINK IT WAS GOING TO HAPPEN? ಠ_ಠ");
        }
    }
}
