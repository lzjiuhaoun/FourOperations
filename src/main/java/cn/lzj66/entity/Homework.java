package cn.lzj66.entity;

import java.sql.Date;

public class Homework {
    private int id;

    private int studentId;

    private int taskId;

    private int status;

    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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
        return "Homework{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", taskId=" + taskId +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
