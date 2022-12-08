package Interpreter;

import Controller.Controller;
import Exceptions.MyException;
import Model.Expression.*;
import Model.ProgramState.ProgState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolVal;
import Model.Value.IntVal;
import Model.Value.StringVal;
import Repo.IRepo;
import Repo.MyRepo;
import View.TextMenu;
import View.Command.ExitCommand;
import View.Command.RunExample;

public class Interpreter {
    public Interpreter() {}
    public void do_the_roar() throws MyException {
        IStmt ex1=new CompStmt(new VarDecStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValExp(new IntVal(2))), new PrintStmt(new VarExp("v"))));
        IRepo<ProgState> repo1 = new MyRepo<ProgState>("C:\\Users\\Rares\\IdeaProjects\\L3\\logs\\log1.txt");
        Controller ctr1 = new Controller(repo1);
        ctr1.add(ex1);

        IStmt ex2=new CompStmt(new VarDecStmt("a", new IntType()),
                new CompStmt(new VarDecStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(1, new ValExp(new IntVal(2)), new ArithExp(3, new ValExp(new IntVal(3)), new ValExp(new IntVal(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValExp(new IntVal(1)))), new PrintStmt(new VarExp("b"))))));
        IRepo<ProgState> repo2 = new MyRepo<ProgState>("C:\\Users\\Rares\\IdeaProjects\\L3\\logs\\log2.txt");
        Controller ctr2 = new Controller(repo2);
        ctr2.add(ex2);
        IStmt ex3= new CompStmt(new VarDecStmt("a",new BoolType()),
                new CompStmt(new VarDecStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValExp(new BoolVal(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValExp(new IntVal(2))), new AssignStmt("v", new ValExp(new IntVal(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        IRepo<ProgState> repo3 = new MyRepo<ProgState>("C:\\Users\\Rares\\IdeaProjects\\L3\\logs\\log3.txt");
        Controller ctr3 = new Controller(repo3);
        ctr3.add(ex3);

        /*String varf = "test.in";openRFile(varf);int varc;readFile(varf,varc);
        print(varc);readFile(varf,varc);print(varc);closeRFile(varf);*/
        IStmt ex4= new CompStmt(new VarDecStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValExp(new StringVal("test.in"))),
                        new CompStmt(new OpenRFStmt(new VarExp("varf")),
                                new CompStmt(new VarDecStmt("varc",new IntType()),
                                        new CompStmt( new ReadFStmt(new VarExp("varf"),"varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFStmt(new VarExp("varf"),"varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFStmt(new VarExp("varf"))))))))));
        IRepo<ProgState> repo4= new MyRepo<ProgState>("C:\\Users\\Rares\\IdeaProjects\\L3\\logs\\log4.txt");
        Controller ctr4 = new Controller(repo4);
        ctr4.add(ex4);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        IStmt ex5= new CompStmt(new VarDecStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHStmt("v", new ValExp(new IntVal(20))),
                        new CompStmt(new VarDecStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewHStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));
        IRepo<ProgState> repo5= new MyRepo<ProgState>("C:\\Users\\Rares\\IdeaProjects\\L3\\logs\\log5.txt");
        Controller ctr5 = new Controller(repo5);
        ctr5.add(ex5);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        IStmt ex6= new CompStmt(new VarDecStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHStmt("v", new ValExp(new IntVal(20))),
                        new CompStmt(new VarDecStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewHStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp(1, new ReadHExp(new ReadHExp(new VarExp("a"))), new ValExp(new IntVal(5)))))))));
        IRepo<ProgState> repo6= new MyRepo<ProgState>("C:\\Users\\Rares\\IdeaProjects\\L3\\logs\\log6.txt");
        Controller ctr6 = new Controller(repo6);
        ctr6.add(ex6);

        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStmt ex7= new CompStmt(new VarDecStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHStmt("v", new ValExp(new IntVal(20))),
                        new CompStmt(new PrintStmt(new ReadHExp(new VarExp("v"))),
                                new CompStmt(new WriteHStmt("v", new ValExp(new IntVal(30))),
                                        new PrintStmt(new ArithExp(1, new ReadHExp(new VarExp("v")), new ValExp(new IntVal(5))))))));
        IRepo<ProgState> repo7= new MyRepo<ProgState>("C:\\Users\\Rares\\IdeaProjects\\L3\\logs\\log7.txt");
        Controller ctr7 = new Controller(repo7);
        ctr7.add(ex7);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStmt ex8= new CompStmt(new VarDecStmt("v", new RefType(new IntType())),
                new CompStmt(new NewHStmt("v", new ValExp(new IntVal(20))),
                        new CompStmt(new VarDecStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewHStmt("a", new VarExp("v")),
                                        new CompStmt(new NewHStmt("v", new ValExp(new IntVal(30))),
                                                new PrintStmt(new ReadHExp(new ReadHExp(new VarExp("a")))))))));
        IRepo<ProgState> repo8= new MyRepo<ProgState>("C:\\Users\\Rares\\IdeaProjects\\L3\\logs\\log8.txt");
        Controller ctr8 = new Controller(repo8);
        ctr8.add(ex8);

        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStmt ex9= new CompStmt(new VarDecStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValExp(new IntVal(4))),
                        new CompStmt(new WhileStmt(new RelExp(new VarExp("v"), new ValExp(new IntVal(0)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp(2, new VarExp("v"), new ValExp(new IntVal(1)))))),
                                new PrintStmt(new VarExp("v")))));
        IRepo<ProgState> repo9= new MyRepo<ProgState>("C:\\Users\\Rares\\IdeaProjects\\L3\\logs\\log9.txt");
        Controller ctr9 = new Controller(repo9);
        ctr9.add(ex9);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
        menu.addCommand(new RunExample("6",ex6.toString(),ctr6));
        menu.addCommand(new RunExample("7",ex7.toString(),ctr7));
        menu.addCommand(new RunExample("8",ex8.toString(),ctr8));
        menu.addCommand(new RunExample("9",ex9.toString(),ctr9));
        menu.show();
    }
    }
