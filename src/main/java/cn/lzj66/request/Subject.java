package cn.lzj66.request;

public class Subject {

    private int difficult;
    private int publicerId;
    private String question;
    private String answer;
    private int status;

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public int getPublicerId() {
        return publicerId;
    }

    public void setPublicerId(int publicerId) {
        this.publicerId = publicerId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "difficult=" + difficult +
                ", publicerId='" + publicerId + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                '}';
    }
}
