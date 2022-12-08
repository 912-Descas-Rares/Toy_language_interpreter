package Exceptions;

public class VarException extends  MyException{
    String msg;
    public VarException(String s) {
        super(s);
    }
    @Override
    public String what() {
        return msg;
    }
}
