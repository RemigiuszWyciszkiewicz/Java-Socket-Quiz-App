package sample;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AnswerHandler extends Thread {

    static BlockingQueue<Answer> answersQueue=new LinkedBlockingQueue<>();

    Controller controller;

    public AnswerHandler(Controller controller) {
        this.controller = controller;
        initQuestions();

        System.out.println("");
        System.out.println(showQuestion());


    }

    String[] questionList = new String[5];
    HashMap<String,Integer> playersList = new HashMap<>();
    private int questionIndex=0;



    void initQuestions()
    {
        questionList[0] = "Stolica polski?-warszawa";
        questionList[1] = "Stolica niemiec?-berlin";
        questionList[2] = "Stolica norwegii?-oslo";
        questionList[3] = "Stolica ukrainy?-kijów";
        questionList[4] = "Stolica czech?-praga";

    }

    void addNewPlayer(String imie)
    {
        if(!playersList.containsKey(imie)) {
            playersList.put(imie, 0);

        }
    }

    void checkAnswer(Answer answer)
    {
        String correctAnswer = questionList[questionIndex].substring(questionList[questionIndex].indexOf("-")+1);

        if(correctAnswer.equals(answer.getAnswer()))
        {
            System.out.println("Poprawna odpowiedz!");
            questionIndex++;
            addPoint(answer.getImie());
            showQuestion();
        } else System.out.println("Błędna odpowiedz :(");

    }
    void addPoint(String name)
    {
       int i = playersList.get(name);
       i++;
       playersList.replace(name,playersList.get(name),i);
    }

    String showQuestion()
    {
        return ">>>>>  " +questionList[questionIndex].substring(0,questionList[questionIndex].indexOf("-"));

    }

    @Override
    public void run() {
        while(true)
        {
            Answer answer = null;
            try {
               answer=answersQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            addNewPlayer(answer.getImie());
            checkAnswer(answer);
            wyświetlListeGraczy();
            System.out.println(showQuestion());
        }
    }

    void wyświetlListeGraczy()
    {
        for (Map.Entry me : playersList.entrySet()) {
            System.out.println("Imie: "+me.getKey() + "  Ilość Punktów: " + me.getValue());
        }

    }

    public int getQuestionIndex() {
        return questionIndex;
    }
}
