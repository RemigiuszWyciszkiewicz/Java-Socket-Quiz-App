package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    TextField odpowiedzTextField;
    @FXML
    TextField imieTextField;
    @FXML
    Button wyslijButton;


    Client client;

    @FXML
    void wyslij()
    {
        client.sendMessage(odpowiedzTextField.getText()+"-"+imieTextField.getText());
        odpowiedzTextField.clear();
    }

    @FXML
    void initialize()
    {
        wyslijButton.disableProperty().bind(odpowiedzTextField.textProperty().isEmpty().or(imieTextField.textProperty().isEmpty()));

        client=new Client();
        client.startConnection("127.0.0.1",4444);


    }

}
