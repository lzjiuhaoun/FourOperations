package cn.lzj66.entity;

import java.sql.Date;

public class HomeworkItem {

    private int id;

    private int hid;

    private String question;

    private String answer;

    private String result;

    private int correct;

    private int status;

    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "HomeworkItem{" +
                "id=" + id +
                ", hid=" + hid +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", result='" + result + '\'' +
                ", correct=" + correct +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
