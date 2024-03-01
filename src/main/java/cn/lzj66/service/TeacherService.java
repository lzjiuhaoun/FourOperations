package cn.lzj66.service;

import cn.lzj66.dao.ClassesDao;
import cn.lzj66.dao.StudentDao;
import cn.lzj66.dao.SubjectDao;
import cn.lzj66.dao.TaskDao;
import cn.lzj66.dao.TaskItemDao;
import cn.lzj66.dao.TeacherClassesDao;
import cn.lzj66.dao.TeacherDao;
import cn.lzj66.entity.Classes;
import cn.lzj66.entity.Student;
import cn.lzj66.entity.Task;
import cn.lzj66.entity.TaskItem;
import cn.lzj66.entity.Teacher;
import cn.lzj66.request.ListQuestion;
import cn.lzj66.request.Subject;
import cn.lzj66.response.Question;
import cn.lzj66.dao.*;
import cn.lzj66.entity.*;
import cn.lzj66.request.CreateTask;
import cn.lzj66.util.CalculateExp;
import cn.lzj66.util.Create;
import cn.lzj66.util.InviteCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {

    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private TeacherClassesDao teacherClassesDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private TaskItemDao taskItemDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private StudentDao studentDao;
    /**
     * 根据用户名获取账号密码
     * @param username String
     * @return Class
     */
    public Teacher getTeacherByUserName(String username){
        return teacherDao.getTeacherByName(username);
    }

    /**
     * 根据教师id获取班级列表
     * @param teacherId int
     * @return List
     */
    public List<Classes> getClassList(int teacherId){
        return classesDao.getClassByTeacherAndId(teacherId);
    }

    /**
     * 根据班级id获取全部同学
     * @param classId int
     * @return List
     */
    public List<Student> getAllStudentByClassId(int classId){
        return studentDao.getSameStudent(classId);
    }
    /**
     * 增加一个老师账号
     * @param teacher Class
     * @return
     */
    public int addTeacher(Teacher teacher){
        return teacherDao.addTeacher(teacher);
    }

    /**
     * 增加一个Subject
     * @param subject
     * @return
     */
    public int addSubject(Subject subject) {
        return subjectDao.addSubject(subject);
    }

    /**
     * 添加一个班级
     * @param teacherId int 教师id
     * @param name string 班级名字
     * @param desc string 班级描述
     * @return
     */
    @Transactional
    public boolean addClass(int teacherId,String name,String desc){
        String uuid = InviteCode.build();//生成一个邀请码
        Classes classes = new Classes();
        classes.setName(name);
        classes.setRemark(desc);
        classes.setUuid(uuid);
        classesDao.addClass(classes);
        int classId = classes.getId();
        System.out.println(classes.getId());
        int res = teacherClassesDao.add(teacherId,classId);
        if(res >= 1){
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public boolean addTask(int teacherId , CreateTask question, ListQuestion listQuestion){
        Task task = new Task();
        task.setClassId(question.getClassId());
        task.setTeacherId(teacherId);
        task.setTitle(question.getTitle());
        task.setCount(question.getCount());
        task.setDescription(question.getDescription().trim());
        task.setEndTime(question.getEndTime());

        int row = taskDao.addTask(task);
        int taskId = task.getId();
        List<TaskItem> items = new ArrayList<>();
        TaskItem taskItem;
        for (int i = 0; i < listQuestion.getQuestion().size(); i++) {
            taskItem = new TaskItem();
            taskItem.setQuestion(listQuestion.getQuestion().get(i));
            taskItem.setAnswer(listQuestion.getAnswer().get(i));
            taskItem.setTaskId(taskId);
            items.add(taskItem);
        }
        int res = taskItemDao.addBatchItem(items);
        if(res >= 1){
            return true;
        }else{
            return false;
        }
    }
    public List<Task> getTaskList(int teacherId){
        return taskDao.getListTaskByTeacherId(teacherId);
    }

    /**
     * 创建题目
     * @param difficult 难度
     * @param type 题目类型
     * @param count 数量
     * @return List
     */
    public List<Question> createQuestion(int difficult, int type, int count){
        List<Question> list = new ArrayList<>();
        switch (type){
            case 1:
                CalculateExp calculateExp = new CalculateExp();
                Map<String,List<String>> map = calculateExp.build(count, Create.getMaxNumberByDifficult(difficult),Create.getDifficultBySelf(difficult));
                List<String> ques = map.get("question");
                List<String> answer = map.get("answer");
                Question question ;
                for (int i = 0; i < ques.size(); i++) {
                    question = new Question();
                    question.setId(i + 1);
                    question.setQuestion(ques.get(i));
                    question.setAnswer(answer.get(i));
                    list.add(question);
                }
                break;
            case 2:
                list = subjectDao.getSubjectByDifficult(difficult,count);
                break;
            default:
                break;
        }
        return list;
    }
}
