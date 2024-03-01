package cn.lzj66.entity;


import java.sql.Date;

public class TeacherClass {
    private Integer id;

    private Integer teacherId;

    private Integer classId;

    private Byte status;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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
        return "TeacherClass{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", classId=" + classId +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}