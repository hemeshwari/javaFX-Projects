/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sidenavigationnew;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Dell
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button home;
    @FXML
    private Button blog;
    @FXML
    private Button videos;
    @FXML
    private Button gallery;
    @FXML
    private Button about_us;
    @FXML
    private BorderPane border_pane;
    
    private void loadPage(String page){
        Parent root=null;
        try{
            root=FXMLLoader.load(getClass().getResource(page+".fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        border_pane.setCenter(root);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        home.onActionProperty().set(e->{
            loadPage("home");
        });
        
        blog.setOnAction(e -> {
            loadPage("blog");
        });
        
        videos.setOnAction(e -> {
            loadPage("videos");
        });
        
        gallery.setOnAction(e -> {
            loadPage("gallery");
        });
        
        about_us.setOnAction(e -> {
            loadPage("about_us");
        });
    }
}
