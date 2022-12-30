package banking;

import java.util.Scanner;

public class LoginCommand implements Command{
    String pin;
    String cardNum;
    Scanner scanner = new Scanner(System.in);


    public void execute(){

        cardNum = scanner.nextLine();
        pin = scanner.nextLine();
    }

    public LoginCommand(){
        execute();
    }

    public String getPin() {
        return pin;
    }

    public String getCardNum() {
        return cardNum;
    }
}
