package Model.Expression;

import Model.ADT.MyDict;
import Exceptions.*;
import Model.ADT.SmartDict;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolVal;
import Model.Value.IVal;
import Model.Value.IntVal;

public class LogicExp implements IExp {
    IExp e1;
    IExp e2;
    int op;//1 and, 2 or
    @Override
    public IVal eval(MyDict<String, IVal> tbl, SmartDict<Integer,IVal> hp) throws MyException {
        IVal v1,v2;
        v1= e1.eval(tbl,hp);
        if (v1.getType().equals(new BoolType()))
        {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new BoolType())) {
                BoolVal i1 = (BoolVal)v1;
                BoolVal i2 = (BoolVal)v2;
                boolean b1,b2;
                b1= i1.getVal();
                b2 = i2.getVal();
                if (op==1)  return new BoolVal( b1 && b2 );
                if (op==2)  return new BoolVal( b1 || b2 );

            }else
                throw new ArithException("second operand is not an boolean");
        }
        throw new ArithException("first operand is not an boolean");
    }

    @Override
    public String toString() {
        if(op==1)
            return e1.toString()+"and"+e2.toString();
        return e1.toString()+"or"+e2.toString();
    }
}
