package cn.lzj66.request;

public class CreateTask {
    //班级id
    private int classId;
    //标题
    private String title;
    //描述
    private String description;
    //截止日期
    private String endTime;
    //难度
    private int difficult;
    //数量
    private int count;
    //出题方式
    private int type;

    private String questionJson;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestionJson() {
        return questionJson;
    }

    public void setQuestionJson(String questionJson) {
        this.questionJson = questionJson;
    }

    @Override
    public String toString() {
        return "CreateTask{" +
                "classId=" + classId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", endTime='" + endTime + '\'' +
                ", difficult=" + difficult +
                ", count=" + count +
                ", type=" + type +
                ", questionJson='" + questionJson + '\'' +
                '}';
    }
}
