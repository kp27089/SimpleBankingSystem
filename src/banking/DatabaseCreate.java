package banking;

import java.sql.*;

public class DatabaseCreate implements Command{
    String url;
    String sql;

    public DatabaseCreate(String url) {
        this.url = url;
        sql = statement(url);
    }

    @Override
    public void execute() {
        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
           // stmt.execute();
            stmt.execute(sql);
            if (conn!=null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println();
            }

        }catch(SQLException e){



            System.out.println(sql);



        }
    }


    private String statement(String name){
        String copy = name;
        copy = copy.replaceFirst("jdbc:sqlite:","");
        copy = copy.replaceAll(".s3db","");
        String sql =


                "CREATE TABLE IF NOT EXISTS card (\n"
                + "     id INTEGER PRIMARY KEY,\n" // autimaticly create new id
                + "     number TEXT,\n"                       // don't write VARCHAR its mistake!
                + "     pin TEXT,\n"
                + "     balance INTEGER DEFAULT 0"
                + ");";

        return sql;
    }






    }


