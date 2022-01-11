/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviewusers;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Dell
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<users> table_user;
    @FXML
    private TableColumn<users, Integer> col_id;
    @FXML
    private TableColumn<users, String> col_username;
    @FXML
    private TableColumn<users, String> col_password;
    @FXML
    private TableColumn<users, String> col_email;
    @FXML
    private TableColumn<users, String> col_type;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_type;
    @FXML
    private TextField txt_id;
    
    ObservableList<users> listM;
    ObservableList<users> dataList;
    
    int index = -1;
    
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    @FXML
    private TextField filterfield;
    
    
    @FXML
    public void add_users(){
        con = mysqlconnect.ConnectDb();
        String query = "insert into users (username, password, email, type) values (?,?,?,?)";
        
        try{
            ps = con.prepareStatement(query);
            
            ps.setString(1, txt_username.getText());
            ps.setString(2, txt_password.getText());
            ps.setString(3, txt_email.getText());
            ps.setString(4, txt_type.getText());
            
            ps.execute();
            
            System.out.println("added user succesfully"); 
            updateTable();
        }catch(Exception e){
            System.out.println("not added users");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index = table_user.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            
            return ;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_username.setText(col_username.getCellData(index).toString());
        txt_password.setText(col_password.getCellData(index).toString());
        txt_email.setText(col_email.getCellData(index).toString());
        txt_type.setText(col_type.getCellData(index).toString());
    }

    @FXML
    private void edit_user(ActionEvent event) {
        
        try{
            con = mysqlconnect.ConnectDb();
            
            String v1 = txt_id.getText();
            String v2 = txt_username.getText();
            String v3 = txt_password.getText();
            String v4 = txt_email.getText();
            String v5 = txt_type.getText();
            
            String query = "update users set user_id='"+v1+"',username='"+v2+"',password='"+
                    v3+"',email='"+v4+"',type='"+v5+"'where user_id='"+v1+"'";
      
            ps = con.prepareStatement(query);
            
            ps.execute();
            
            System.out.println("user edited successfully");
            updateTable();
        }catch(Exception e){
            System.out.println("user edited unsuccessfull");
            e.printStackTrace();
        }
    }
    
    
    public void updateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<users,String>("username"));
        col_email.setCellValueFactory(new PropertyValueFactory<users,String>("email"));
        col_password.setCellValueFactory(new PropertyValueFactory<users,String>("password"));
        col_type.setCellValueFactory(new PropertyValueFactory<users,String>("type"));
        
        listM =  mysqlconnect.getDatausere();
        table_user.setItems(listM);
    }
    
    @FXML
    private void delete_user(ActionEvent event) {
        con = mysqlconnect.ConnectDb();
        
        String query = "delete from users where user_id = ?";
        
        try{
            ps = con.prepareStatement(query);
            ps.setString(1, txt_id.getText());
            
            ps.execute();
            System.out.println("user deleted successfully");
            updateTable();
            
        }catch(Exception e){
            System.out.println("deleting user unsuccessfull");
            e.printStackTrace();
        }
    }

    
    

    @FXML
    private void search_user() {
        col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<users,String>("username"));
        col_email.setCellValueFactory(new PropertyValueFactory<users,String>("email"));
        col_password.setCellValueFactory(new PropertyValueFactory<users,String>("password"));
        col_type.setCellValueFactory(new PropertyValueFactory<users,String>("type"));
        
        dataList =  mysqlconnect.getDatausere();
        table_user.setItems(dataList);
        
        FilteredList<users> filteredData = new FilteredList<>(dataList, b-> true);
        filterfield.textProperty().addListener((observable, oldValue, newValue)-> {
            filteredData.setPredicate(person -> {
                                    if(newValue == null || newValue.isEmpty()){
                                        return true;
                                    }
                                    String lowercaseFilter = newValue.toLowerCase();
                                    
                                    if(person.getUsername().toLowerCase().indexOf(lowercaseFilter) != -1){
                                        return true;
                                    }
                                    
                                    else if(person.getPassword().toLowerCase().indexOf(lowercaseFilter) != -1){
                                        return true;
                                    }
                                    
                                    else if(String.valueOf(person.getEmail()).toLowerCase().indexOf(lowercaseFilter) != -1){
                                        return true;
                                    }
                                    else{
                                        return false;
                                    }
                                    
                
            });
        
        });
        SortedList<users> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_user.comparatorProperty());
        table_user.setItems(sortedData);
        
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        updateTable();
        search_user();
    }    
    
}
