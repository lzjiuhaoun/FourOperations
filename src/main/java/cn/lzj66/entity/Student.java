package cn.lzj66.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int classId;
    private String username;
    private String password;
    private String headUrl;
    private String email;
    private String mobile;
    private int score;
    private int status;
    private Timestamp createTime;
    private Classes classes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", classId=" + classId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", gender='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", score=" + score +
                ", status=" + status +
                ", createTime=" + createTime +
                ", classes=" + classes +
                '}';
    }
}
