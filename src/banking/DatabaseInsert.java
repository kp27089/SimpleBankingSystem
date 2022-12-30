package banking;

import java.sql.*;

public class DatabaseInsert extends DatabaseConnect implements Command{
    int id;
    String number;
    String pin;
    final static int balance = 0;
     static String url;
    static String name;

    public DatabaseInsert(int id, String number, String pin, String url) {
        this.id = id;
        this.number = number;
        this.pin = pin;
        this.url = url;
        this.name = sql(url);
    }
    public DatabaseInsert(){

    }


    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public static int getBalance() {
        return balance;
    }


    public String getName() {
        return name;
    }

    @Override
    public void execute(){
        insert(getId(),getNumber(),getPin(),getBalance());
    }



    private void insert(int id, String number, String pin, int balance){
        String sql = "INSERT INTO card (id, number, pin, balance) VALUES (?,?,?,?)";
        PreparedStatement pstmt = null;
        try{
            Connection conn = connection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2, number);
            pstmt.setString(3,pin);
            pstmt.setInt(4,balance);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println();
        } finally {
            if(pstmt != null){
                try{
                    pstmt.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private String sql(String url){
        String copy = url;
        copy = copy.replaceFirst("jdbc:sqlite:","");
        copy = copy.replaceAll(".s3db","");
        return copy;
    }

}
