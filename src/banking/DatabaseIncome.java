package banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseIncome extends DatabaseInsert{
    String pin;
    String number;
    int income;

    public DatabaseIncome(String pin, String number, int income) {
        this.pin = pin;
        this.number = number;
        this.income = income;
        income();
    }

    @Override
    public String getNumber() {
        return number;
    }

    public int getIncome() {
        return income;
    }

    private void income(){
        String sqlUpdate = "UPDATE 'card' SET balance = balance + ? WHERE number =?";

        try{
            Connection connection = connection();
            PreparedStatement pstm = connection.prepareStatement(sqlUpdate);
            pstm.setInt(1, income);

            pstm.setString(2,number);
            pstm.executeUpdate();

        }catch(SQLException e){
            System.out.println("error in income class "+getName());
        }


    }


}
