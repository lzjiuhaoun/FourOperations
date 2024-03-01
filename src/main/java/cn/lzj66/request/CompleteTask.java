package cn.lzj66.request;

import java.util.Arrays;
import java.util.List;

public class CompleteTask {
    private String taskId;

    private List<String> question;

    private List<String> answer;

    private List<String> result;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

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

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CompleteTask{" +
                "taskId='" + taskId + '\'' +
                ", question=" + question +
                ", answer=" + answer +
                ", result=" + result +
                '}';
    }
}
