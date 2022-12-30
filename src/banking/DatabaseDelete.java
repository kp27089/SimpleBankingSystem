package banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDelete extends DatabaseConnect{

    String number;


    public DatabaseDelete(String number) {

        this.number = number;
        delete();
    }


    public String getNumber() {
        return number;
    }



    private void delete(){
        String sqlUpdate = "DELETE FROM 'card' WHERE number =?";

        try{
            Connection connection = connection();
            PreparedStatement pstm = connection.prepareStatement(sqlUpdate);


            pstm.setString(1,number);
            pstm.executeUpdate();

        }catch(SQLException e){
            System.out.println("error in income class "+getNumber());
        }


    }




}
