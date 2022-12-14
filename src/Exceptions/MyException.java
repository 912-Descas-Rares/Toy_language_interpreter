package Exceptions;

public class MyException extends Throwable implements IException{
    String msg;
    public MyException(String s) {
    this.msg=s;
    }

    @Override
    public String what() {
        return this.msg;
    }
    public String getMessage() {
        return this.msg;
    }
}
