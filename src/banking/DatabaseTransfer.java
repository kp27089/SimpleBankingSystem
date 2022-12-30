package banking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTransfer extends DatabaseConnect {





    Boolean isContain(String number, String pin){
        Boolean result = false;
        int n = -2000;
        String sqlCheck = "SELECT EXISTS (SELECT * FROM 'card' WHERE number ="+number+" AND pin = "+pin+")";
        ResultSet rs = null;

        try{
            Connection connection = connection();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sqlCheck);



            if (rs.getInt(1)>0)
                result = true;

        }catch(SQLException e){
            System.out.println("error in connect class " +number +" "+pin+" "+e);
        } finally {
            if(rs != null){
                try{
                    rs.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }

        }



        return result;
    }


}
