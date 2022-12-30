package banking;

import java.util.Scanner;

public class Client implements Command{
    Scanner scanner = new Scanner(System.in);
    int selection;
    Receiver invoke;

    Database database;

    public Client(){

    }
    public Client(String name){
        this.database = new Database(name);

    }



    public void execute(){


        do{

            displayMenu();
            selection = getSelection();

            switch(selection){
                case 1:
                    this.invoke = new Receiver(Commands.CREATE);
                    invoke.execute();
                    if(invoke.getExit()){
                        selection = 0;
                    }
                    break;
                case 2:
                    this.invoke = new Receiver(Commands.LOG);
                    invoke.execute();
                    if(invoke.getExit()){
                        selection = 0;
                    }
                    break;
            }


        }while(selection!=0);
        System.out.println("Bye!");


    }


    private void displayMenu(){
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    private int getSelection(){
        int selection = scanner.nextInt();
        return selection;

    }


}
