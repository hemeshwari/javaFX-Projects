/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviewexample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Dell
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, Integer> id;
    @FXML
    private TableColumn<Person, String> name;
    @FXML
    private TableColumn<Person, String> lname;
    @FXML
    private TableColumn<Person, String> gmail;
    @FXML
    private TableColumn<Person, String> yahoo;
    @FXML
    private TableColumn<Person, String> phone;
    @FXML
    private TableColumn<Person, String> country;
    
    ObservableList<Person> list = FXCollections.observableArrayList(
            new Person(1,"nisha","vishwakarma","nisha@gmail.com","nisha@yahoo.com","9988079876","india"),
            new Person(2,"nishi","yadav","nishi@gmail.com","nishi@yahoo.com","8678077654","japan"),
            new Person(3,"shaji","mishra","r@gmail.com","a@yahoo.com","7689779876","china"),
            new Person(4,"niksha","pandey","t@gmail.com","s@yahoo.com","9988079876","us"),
            new Person(5,"nitiksha","kosti","y@gmail.com","d@yahoo.com","9988079876","uae"),
            new Person(6,"nimisha","patil","u@gmail.com","f@yahoo.com","9988079876","usa"),
            new Person(7,"nitasha","kunj","ju@gmail.com","g@yahoo.com","9988079876","shrilanka"),
            new Person(8,"natasha","shah","h@gmail.com","h@yahoo.com","9988079876","israil")
    );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id.setCellValueFactory(new PropertyValueFactory<Person,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Person,String>("name"));
        lname.setCellValueFactory(new PropertyValueFactory<Person,String>("lname"));
        gmail.setCellValueFactory(new PropertyValueFactory<Person,String>("gmail"));
        yahoo.setCellValueFactory(new PropertyValueFactory<Person,String>("yahoo"));
        phone.setCellValueFactory(new PropertyValueFactory<Person,String>("phone"));
        country.setCellValueFactory(new PropertyValueFactory<Person,String>("country"));
    
        table.setItems(list);   
        
    }    
    
}
