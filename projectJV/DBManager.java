/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectJV;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * シングルトンパターンを適用する
 * @author 17JZ0135
 */
public class DBManager {
    private static DBManager dbManager = null;
    private static Connection con = null;
//    private static final String driverUrl = "jdbc:derby://localhost:1527/dbsys";
//    private static final String dbUserName = "user01";
//    private static final String dbUserPassword = "user01";
    
    
    private static final String driverUrl = "jdbc:derby://localhost:1527/dbsys";
    private static final String dbUserName = "G1716";
    private static final String dbUserPassword = "pass";
    private DBManager() {
        try {
            con = DriverManager.getConnection(driverUrl, dbUserName, dbUserPassword);
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    public static DBManager getDBManager() {
        if (DBManager.dbManager == null) {
            DBManager.dbManager = new DBManager();
        }
        return DBManager.dbManager;
    }
    public Connection getConnection(){
        return con;
    }
    
}
