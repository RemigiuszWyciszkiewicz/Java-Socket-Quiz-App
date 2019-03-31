package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    @FXML
    Label pytanieLabel;
    @FXML
    Label nrPytanieLabel;

    AnswerHandler answerHandler;

    @FXML
    void initialize()
    {
        answerHandler=new AnswerHandler(this);
        answerHandler.start();

        Thread watek=new Thread(() -> {
            Server server=new Server(answerHandler);
            server.start(4444);

        });
        watek.start();


                        changeLabel(answerHandler.showQuestion(), answerHandler.getQuestionIndex() + 1);

    }

    void changeLabel(String s,int b)
    {
        pytanieLabel.setText(s);
        nrPytanieLabel.setText("Pytanie nr:"+b);
    }



}
