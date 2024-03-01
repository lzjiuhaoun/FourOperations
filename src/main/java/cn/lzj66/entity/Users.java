package cn.lzj66.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Users implements Serializable {
    private static final long serialVersionUID = 1000L;
    private int id;
    private int classId;
    private String username;
    private String password;
    private String headUrl;
    private String gender;
    private String mobile;
    private int type;
    private int score;
    private int status;
    private Timestamp createTime;
    private Classes classes;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
        return "UsersDao{" +
                "id=" + id +
                ", classId=" + classId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", gender='" + gender + '\'' +
                ", mobile='" + mobile + '\'' +
                ", type=" + type +
                ", score=" + score +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
