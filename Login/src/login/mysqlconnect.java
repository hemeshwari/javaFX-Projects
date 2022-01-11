/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Dell
 */
public class mysqlconnect {
    Connection con = null;
    public static Connection ConnectDb(){
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost/market", "root","");
            return con;
        }catch(Exception e){
            return null;
        }
    
        
        
    }
}
