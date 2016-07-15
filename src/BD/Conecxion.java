/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ELECTRONICA
 */
public class Conecxion {
    
    private static  String user="pasante1";
    private static String contrasenia="sebastian";
    private static final String JDBC_driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Driver driver;
    private static  String db="databaseName=FUCS";
    private static final String url_JDBC="jdbc:sqlserver://JAVIERCASASUC;"+db;
    
    public Conecxion(){
    
    }
    
    
    public static  synchronized Connection  getConecxion() throws SQLException{
        if(driver==null){
            try{
                Class JdbcDriverClass=Class.forName(JDBC_driver);
                driver= (Driver) JdbcDriverClass.newInstance(); 
            }catch(Exception ex){
                System.err.println("errorororoor");
            ex.printStackTrace();
            }
        }
    return DriverManager.getConnection(url_JDBC,user,contrasenia);
    }
    
    
    
    public static void close(PreparedStatement stat){
    try{
        if(stat!=null){
            stat.close();
        }
    }catch(SQLException ex){
        ex.printStackTrace();
        }
    }
    
    
    public static void close(ResultSet set){
   try{
       if(set!=null){
       set.close();
       }
     }catch(SQLException ex){
       ex.printStackTrace();
        }
    }
    
    public static void close(Connection co){
        try{
        if(co!=null){
        co.close();
        }
        }catch(SQLException ex){
        ex.printStackTrace();
        }
    }
    
    
}
