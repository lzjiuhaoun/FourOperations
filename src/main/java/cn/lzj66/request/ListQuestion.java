package cn.lzj66.request;

import java.util.List;

public class ListQuestion {
    private List<String> question;

    private List<String> answer;

    public List<String> getQuestion() {
        return question;
    }

    public void setQuestion(List<String> question) {
        this.question = question;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "ListQuestion{" +
                "question=" + question +
                ", answer=" + answer +
                '}';
    }
}
