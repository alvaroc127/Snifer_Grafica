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
    
    private final  String user="user=;";
    private final String contrasenia="password=;";
    private final String JDBC_driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Driver driver;
    private final String db="databaseName= ;";
    private final String url_JDBC="jdbc:sqlserver://localhost:1443;"+db+user+contrasenia;
    
    
    public Conecxion(){
    
    }
    
    
    public synchronized Connection  conecxion() throws SQLException{
        if(driver==null){
            try{
                Class JdbcDriverClass=Class.forName(JDBC_driver);
                driver= (Driver) JdbcDriverClass.newInstance(); 
            }catch(Exception ex){
            ex.printStackTrace();
            }
        }
    return DriverManager.getConnection(url_JDBC);
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
