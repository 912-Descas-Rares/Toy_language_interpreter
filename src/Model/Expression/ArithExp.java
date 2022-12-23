package Model.Expression;

import Model.ADT.MyDict;
import Exceptions.*;
import Model.ADT.SmartDict;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.IVal;
import Model.Value.IntVal;

public class ArithExp implements IExp{
    IExp e1;
    IExp e2;
    int op;

    public ArithExp(int i, IExp iExp1, IExp iExp2) {
        e1=iExp1;
        e2=iExp2;
        op=i;
    }
    //1-plus, 2-minus, 3-star, 4-divide, 5 mod

    @Override
    public IVal eval(MyDict<String, IVal> tbl, SmartDict<Integer,IVal> hp) throws MyException {
        IVal v1,v2;
        v1= e1.eval(tbl,hp);
        if (v1.getType().equals(new IntType()))
        {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new IntType())) {
                IntVal i1 = (IntVal)v1;
                IntVal i2 = (IntVal)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                if (op==1)  return new IntVal(n1+n2);
                if (op ==2)  return new IntVal(n1-n2);
                if(op==3)  return new IntVal(n1*n2);
                if(op==4)
                    if(n2==0) throw new ArithException("division by zero");
                    else  return new IntVal(n1/n2);
                if(op==5)
                    if(n2==0) throw new ArithException("mod by zero");
                    else  return new IntVal(n1%n2);
            }else
                throw new ArithException("second operand is not an integer");
        }
            throw new ArithException("first operand is not an integer");
    }

    @Override
    public IType typecheck(MyDict<String, IType> typeEnv) throws MyException {
        IType t1=e1.typecheck(typeEnv),t2=e2.typecheck(typeEnv);
        if(t1.equals(new IntType()))
        {
            if(t2.equals(new IntType()))
            {
                return new IntType();
            }
            else
                throw new TypeException("second operand is not an integer, dummy");
        }
        else
            throw new TypeException("first operand is not an integer, Archimedes");

    }

    @Override
    public String toString() {
        MyDict<Integer, Character> map= new MyDict<Integer, Character>();
        map.put(1,'+');
        map.put(2,'-');
        map.put(3,'*');
        map.put(4,'/');
        map.put(5,'%');

        try {
            return e1.toString()+map.lookup(op)+e2.toString();
        } catch (MiscException e) {
            throw new RuntimeException(e);
        }
    }
}
