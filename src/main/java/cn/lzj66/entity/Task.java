package cn.lzj66.entity;

import java.sql.Date;
import java.util.List;

public class Task {
    private Integer id;

    private String title;

    private String description;

    private Integer classId;

    private Integer teacherId;

    private Integer count;

    private String endTime;

    private Integer isFinish;

    private Boolean status;

    private Date createTime;

    private List<TaskItem> items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<TaskItem> getItems() {
        return items;
    }

    public void setItems(List<TaskItem> items) {
        this.items = items;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", classId=" + classId +
                ", teacherId=" + teacherId +
                ", count=" + count +
                ", endTime='" + endTime + '\'' +
                ", isFinish=" + isFinish +
                ", status=" + status +
                ", createTime=" + createTime +
                ", items=" + items +
                '}';
    }
}