package banking;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    static String url;
    DatabaseCreate create;
    static int id = 0;
    DatabaseInsert insert;
    public String tempName;
    Boolean result;

    Connection conn;

    public Database(String name){
        this.url="jdbc:sqlite:"+name;
        this.create = new DatabaseCreate(url);
        create.execute();

    }

    public Database(String number, String pin){
        id++;
        this.insert = new DatabaseInsert(id, number, pin, url);
        insert.execute();

    }

    public Database(){

    }

    public static String getUrl() {
        return url;
    }

    public Boolean getResult() {
        return result;
    }

    public Database(Commands command, String pin, String number, int income){
        switch (command){
            case INCOME:
                DatabaseIncome inc = new DatabaseIncome(pin, number, income);
                tempName = inc.getName() +" " + inc.getNumber();
                break;
            case BALANCE:
                DatabaseBalance bal = new DatabaseBalance(number);

                break;
            case CHECK:
                DatabaseConnect chk = new DatabaseConnect();
                result = chk.isContain(number, pin);
                break;
            case CHECKTRANSFER:
                DatabaseConnect chkt = new DatabaseConnect();
                result = chkt.isContain(number, Commands.CHECKTRANSFER);
                break;
            case CHECKBALANCETRANSFER:
                DatabaseConnect chkbt = new DatabaseConnect();
                result = chkbt.isContain(number, Commands.CHECKBALANCETRANSFER, income);
                break;
            case TRANSFER:
                DatabaseTransfer tran = new DatabaseTransfer();
                break;
            case CLOSE:
                Database close = new DatabaseDelete(number);

                break;


        }
    }


    public Database(Commands command, String pin, String number){
        this(command,pin,number,0);
    }

    public Database(Commands command){
        this(command,null,null,0);
    }

    public Database(Commands command, String number){
        this(command,"",number,0);
    }


}
