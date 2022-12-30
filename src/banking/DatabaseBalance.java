package banking;

import java.sql.*;

public class DatabaseBalance extends DatabaseConnect {

    int balance;
    String number;

    public DatabaseBalance(String number) {

        this.number = number;


        System.out.println(balance(number));
    }


    public String getBalance() {
        return number;
    }


    private int balance(String number) {
        int result = 0;
        int n = -2000;

        String sqlCheck = "SELECT balance FROM 'card' WHERE number =" + number + ")";
        ResultSet rs = null;

        try {
            Connection connection = connection();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sqlCheck);


            result = rs.getInt("balance");

        } catch (SQLException e) {
            System.out.println("error in connect class " + number + e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


        return result;
    }


}
