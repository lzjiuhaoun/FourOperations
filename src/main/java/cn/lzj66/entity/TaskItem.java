package cn.lzj66.entity;

import java.sql.Date;

public class TaskItem {
    private int id;

    private int taskId;

    private String question;

    private String answer;

    private int status;

    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
