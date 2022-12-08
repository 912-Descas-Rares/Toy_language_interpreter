package Model.Expression;

import Exceptions.*;
import Model.ADT.MyDict;
import Model.ADT.SmartDict;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolVal;
import Model.Value.IVal;
import Model.Value.IntVal;

import java.util.Objects;

public class RelExp implements IExp{
    IExp e1;
    IExp e2;
    String op;

    public RelExp(VarExp v, ValExp valExp, String s) {
        e1=v;
        e2=valExp;
        op=s;
    }

    private BoolVal evalInt(int a, int b) throws MyException
    {
        if (Objects.equals(op, "==")) {
            return new BoolVal(a==b);
        } else if (Objects.equals(op, "!=")) {
            return new BoolVal(a!=b);
        } else if (Objects.equals(op, "<")) {
            return new BoolVal(a<b);
        } else if (Objects.equals(op, "<=")) {
            return new BoolVal(a<=b);
        } else if (Objects.equals(op, ">")) {
            return new BoolVal(a>b);
        } else if (Objects.equals(op, ">=")) {
            return new BoolVal(a>=b);
        } else {
            throw new SignException("Sign not recognized!");
        }
    }
    private BoolVal evalBool(boolean a, boolean b) throws MyException
    {
        if (Objects.equals(op, "==")) {
            return new BoolVal(a==b);
        } else if (Objects.equals(op, "!=")) {
            return new BoolVal(a!=b);
        } else{
            throw new SignException("Sign not recognized or inappropriate for varType!");
        }
    }

    @Override
    public IVal eval(MyDict<String, IVal> tbl, SmartDict<Integer,IVal> hp) throws MyException {
        // Old habits die hard
        IVal v1 = e1.eval(tbl,hp);
        IVal v2 = e2.eval(tbl,hp);
        if (v1.getType().equals(new IntType()) && v2.getType().equals(new IntType())) {
            IntVal i1= (IntVal)v1;
            IntVal i2= (IntVal)v2;
            return evalInt(i1.getVal(), i2.getVal());
        } else if (v1.getType().equals(new BoolType()) && v2.getType().equals(new BoolType())) {
            BoolVal b1= (BoolVal)v1;
            BoolVal b2= (BoolVal)v2;
            return evalBool(b1.getVal(), b2.getVal());

        } else throw new TypeException("Types not compatible! Non-orthodox!");

    }

    @Override
    public String toString() {
        return e1.toString()+op+e2.toString();
    }
}
