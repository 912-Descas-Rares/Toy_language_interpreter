package Exceptions;

public class ArithException extends  MyException{
    String msg;
    public ArithException(String s) {
        super(s);
    }
    @Override
    public String what() {
        return msg;
    }
}
