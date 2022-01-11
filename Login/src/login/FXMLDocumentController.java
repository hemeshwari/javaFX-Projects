/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane pane_login;

    @FXML
    private TextField txt_username;
    @FXML
    private Button btn_login;
    @FXML
    private PasswordField txt_password;
    @FXML
    private ComboBox type;
    @FXML
    private AnchorPane pane_signup;
    @FXML
    private TextField email_up;
    @FXML
    private ComboBox type_up;
    @FXML
    private TextField txt_username_up;
    @FXML
    private PasswordField txt_password_up;
    @FXML
    private Button login;
    @FXML
    private Button signup;

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    @FXML
    private void Login(ActionEvent event) throws Exception {

        con = mysqlconnect.ConnectDb();
        String sql = "Select * from users where username = ? and password = ? and type = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, txt_username.getText());
            ps.setString(2, txt_password.getText());
            ps.setString(3, type.getValue().toString());

            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("U&p is correct");

                btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("CPanel.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

            } else {
                System.out.println("u&p is incorrect");
            }
        } catch (Exception e) {

        }
    }

    public void add_users(ActionEvent event) {
        con = mysqlconnect.ConnectDb();
        String sql = "insert into users (username,password,type,email) values (?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, txt_username_up.getText());
            ps.setString(2, txt_password_up.getText());
            ps.setString(3, type_up.getValue().toString());
            System.out.println(type_up.getValue().toString());
            ps.setString(4, email_up.getText());
            ps.execute();
            System.out.println("add users successfull!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type_up.getItems().addAll("Admin", "client", "cashier", "storekeeper");
        type.getItems().addAll("Admin", "client", "cashier", "storekeeper");

        login.setOnAction(e -> {
            System.out.println("pppp");
            pane_login.setVisible(true);
            pane_signup.setVisible(false);
        });
        signup.setOnAction(e -> {
            System.out.println("Hello");
            pane_login.setVisible(false);
            pane_signup.setVisible(true);
        });
    }

}
