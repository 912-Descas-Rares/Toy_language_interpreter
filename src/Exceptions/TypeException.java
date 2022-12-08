package Exceptions;

public class TypeException extends  MyException{
    String msg;
    public TypeException(String s) {
        super(s);
    }
    @Override
    public String what() {
        return msg;
    }
}
