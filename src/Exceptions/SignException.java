package Exceptions;

public class SignException extends  MyException{
    String msg;
    public SignException(String s) {
        super(s);
    }
    @Override
    public String what() {
        return msg;
    }
}
