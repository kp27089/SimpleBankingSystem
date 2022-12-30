package banking;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Receiver {
    Account accBase;
    String key;
    Commands command;
    static Map<String, Account> accounts = new HashMap<String, Account>();
    Boolean exit = false;
    Database database;

    public Receiver(Commands command) {
        this.command = command;
    }

    public Receiver() {

    }


    public void execute() {
        if (command == Commands.CREATE) {
            CreateCommand inputNewAccount = new CreateCommand();

            inputNewAccount.execute();
            accounts.put(inputNewAccount.getIdentifier(), inputNewAccount.getAccount());
            System.out.println();
            System.out.println("Your card has been created");
            System.out.println("Your card number:");
            System.out.println(accounts.get(inputNewAccount.identifier).getCardNumber());
            System.out.println("Your card PIN:");
            System.out.println(accounts.get(inputNewAccount.identifier).getPin());
            database = new Database(accounts.get(inputNewAccount.identifier).getCardNumber(), accounts.get(inputNewAccount.identifier).getPin());
            command = Commands.NULL;
        } else if (command == Commands.LOG) {
            LoginCommand log = new LoginCommand();
            String pin = log.getPin();
            int len = log.getCardNum().length();

            String cardNum = log.getCardNum().substring(6, len - 1);

//            if((accounts.containsKey(cardNum) && accounts.get(cardNum).getPin().equals(pin))) {
            Database database2 = new Database(Commands.CHECK, pin, log.getCardNum());
//            }
            if ((accounts.containsKey(cardNum) && accounts.get(cardNum).getPin().equals(pin)) || database2.getResult()) {
                int selection;
                Scanner scanner = new Scanner(System.in);
                System.out.println("You have successfully logged in!");


                do {
                    displayMenu();
                    selection = scanner.nextInt();
                    scanner.nextLine();
                    if (selection == 5) {
                        System.out.println("You have successfully logged out!");
                    } else if (selection == 2) {
                        System.out.println("Enter income:");
                        int income = scanner.nextInt();
                        database = new Database(Commands.INCOME, "", log.getCardNum(), income);
                        System.out.println("Income was added!");

                    } else if (selection == 3) {
                        System.out.println();
                        System.out.println("Transfer");
                        System.out.println("Enter card number:");
                        String transfer = scanner.nextLine();

                        if (transfer.length() > 1 || !transfer.equals(null)) {
                            LuhnAlg luhnCheck = new LuhnAlg(transfer);
                            Boolean luhnResult = luhnCheck.getResult();
                            if (luhnResult) {

                                Database database3 = new Database(Commands.CHECKTRANSFER, null, transfer);
                                if (database3.getResult()) {
                                    System.out.println("Enter how much money you want to transfer:");
                                    String moneyTransfer = scanner.nextLine();
                                    Database databaseTransfer = new Database(Commands.CHECKBALANCETRANSFER, null, log.getCardNum(),Integer.parseInt(moneyTransfer));
                                    if (databaseTransfer.getResult()){
                                        database = new Database(Commands.INCOME, "", log.getCardNum(), Integer.parseInt("-"+moneyTransfer));
                                        database = new Database(Commands.INCOME, "", transfer, Integer.parseInt(moneyTransfer));
                                        System.out.println("Success!");

                                    }else{
                                        System.out.println("Not enough money!");
                                    }

                                }else{
                                    System.out.println("Such a card does not exist."+pin);
                                }
                            } else {
                                System.out.println("Probably you made a mistake in the card number. Please try again!"+luhnResult);


                            }
                        }




                    } else if (selection == 1) {
                        System.out.print("balance: ");
                        Database balance = new Database(Commands.BALANCE, log.getCardNum());

                    }else if (selection == 4) {
                        Database delete = new Database(Commands.CLOSE, log.getCardNum());
                        System.out.println("The account has been closed!");
                        break;
                    } else if (selection == 0) {
                        exit = true;


                        break;
                    }
                } while (selection != 5);


            } else {
                System.out.println("Wrong card number or PIN!");
            }
            command = Commands.NULL;
        }
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    private void displayMenu() {

        System.out.println();
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
    }

    public Boolean getExit() {
        return exit;
    }
}
