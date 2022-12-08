import Exceptions.MyException;
import Interpreter.Interpreter;


public class Main {
    public static void main(String[] args) throws MyException {
        try{
            Interpreter i = new Interpreter();
            i.do_the_roar();
        }
        catch (MyException e){
            System.out.println(e.what());
        }
    }
}