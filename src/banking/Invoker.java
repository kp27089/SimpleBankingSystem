package banking;

import java.util.Scanner;

public class Invoker {
    Scanner scanner = new Scanner(System.in);

    Commands tempCommand;

    public Invoker(Commands tempCommand) {
        this.tempCommand = tempCommand;
        execute();
    }

    public void execute(){
        if(tempCommand==Commands.CREATE){

        }



    }





}
