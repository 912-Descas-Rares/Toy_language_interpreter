package Exceptions;

public class MiscException extends  MyException{

    String msg;
    public MiscException(String s) {
        super(s);
    }
    @Override
    public String what() {
        return msg;
    }
}
