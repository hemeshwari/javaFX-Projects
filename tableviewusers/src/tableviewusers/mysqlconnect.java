/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviewusers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dell
 */
public class mysqlconnect {
    Connection con = null;
    
    
    
    public static Connection ConnectDb(){
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/market","root","");
            
            System.out.println("connection established!");
            
            return con;
        }catch(Exception e){
            e.printStackTrace();
            
            return null;
        }
        
    }
    
    public static ObservableList<users> getDatausere(){
       
        Connection con = ConnectDb();
        ObservableList<users> list = FXCollections.observableArrayList();
        
        try{
            PreparedStatement ps = con.prepareStatement("select * from users");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                System.out.println("try to add");
                list.add(new users(Integer.parseInt(rs.getString("user_id")),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("type")));      
                System.out.println(" added");
            }
        }catch(Exception e){
            e.printStackTrace();
            
        }
        
        return list;
    }
    
    public static ObservableList<Students> getDataStudents(){
       
        Connection con = ConnectDb();
        ObservableList<Students> list = FXCollections.observableArrayList();
        
        try{
            PreparedStatement ps = con.prepareStatement("select * from students");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                System.out.println("try to add");
                list.add(new Students(Integer.parseInt(rs.getString("student_id")),rs.getString("student_name"),rs.getString("student_email"),rs.getString("student_password"),rs.getString("student_course_name")));      
                System.out.println(" added");
            }
        }catch(Exception e){
            e.printStackTrace();
            
        }
        
        return list;
    }
}
