import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainFXMLController implements Initializable {

    @FXML
    private Button myButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void buttonClicked(ActionEvent event) {
        System.out.println("MainController.buttonClicked()");
    }

    

}
