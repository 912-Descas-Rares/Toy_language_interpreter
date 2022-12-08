package View.Command;

import Controller.Controller;
import Exceptions.*;

public class RunExample extends Command{
    private Controller ctr;
    public RunExample(String key, String desc, Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }

    @Override
    public void execute() throws MyException {
        try{
            ctr.allStep();  }
        catch ( MyException e){
            System.out.println(e.what());
        }
    }

}
