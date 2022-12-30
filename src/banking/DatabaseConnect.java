package banking;

import java.sql.*;

public class DatabaseConnect extends Database {


    Connection connection() {
        String sql2 = url + "s3db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("error in insert class");
        }
        return conn;
    }


    Boolean isContain(String number, String pin) {
        Boolean result = false;
        int n = -2000;

        String sqlCheck = "SELECT EXISTS (SELECT * FROM 'card' WHERE number =" + number + " AND pin = " + pin + ")";
        ResultSet rs = null;

        try {
            Connection connection = connection();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sqlCheck);


//                PreparedStatement pstm = connection.prepareStatement(sqlCheck);
//                pstm.setString(1, number);
//                pstm.setString(2,pin);


            if (rs.getInt(1) > 0)
                result = true;

        } catch (SQLException e) {
            System.out.println("error in connect class " + number + " " + pin + " " + e);
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

    Boolean isContain(String number, Commands commands) {
        Boolean result = false;
        int n = -2000;

        String sqlCheck = "SELECT EXISTS (SELECT * FROM 'card' WHERE number =" + number + ")";
        ResultSet rs = null;

        try {
            Connection connection = connection();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sqlCheck);


//                PreparedStatement pstm = connection.prepareStatement(sqlCheck);
//                pstm.setString(1, number);
//                pstm.setString(2,pin);


            if (rs.getInt(1) > 0)
                result = true;

        } catch (SQLException e) {
            System.out.println("error in connect class " + number +  e);
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

    Boolean isContain(String number, Commands commands, int balance) {
        Boolean result = false;
        int n = -2000;

        String sqlCheck = "SELECT EXISTS (SELECT * FROM 'card' WHERE number =" + number + " AND balance >= " + balance + ")";
        ResultSet rs = null;

        try {
            Connection connection = connection();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sqlCheck);


//                PreparedStatement pstm = connection.prepareStatement(sqlCheck);
//                pstm.setString(1, number);
//                pstm.setString(2,pin);


            if (rs.getInt(1) > 0)
                result = true;

        } catch (SQLException e) {
            System.out.println("error in connect class " + number +  e);
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









    private String sql(String url) {
        String copy = url;
        copy = copy.replaceFirst("jdbc:sqlite:", "");
        copy = copy.replaceAll(".s3db", "");
        return copy;
    }

}
