package sample;

public class Answer {

    private String imie;
    private String answer;

    public Answer(String imie, String answer) {
        this.imie = imie;
        this.answer = answer;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "imie='" + imie + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
