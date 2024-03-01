package cn.lzj66.service;

import cn.lzj66.dao.ClassesDao;
import cn.lzj66.dao.HomeworkDao;
import cn.lzj66.dao.HomeworkItemDao;
import cn.lzj66.dao.StudentDao;
import cn.lzj66.dao.TaskDao;
import cn.lzj66.entity.Classes;
import cn.lzj66.entity.Homework;
import cn.lzj66.entity.HomeworkItem;
import cn.lzj66.entity.Student;
import cn.lzj66.request.CompleteTask;
import cn.lzj66.dao.*;
import cn.lzj66.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private HomeworkDao homeworkDao;
    @Autowired
    private HomeworkItemDao homeworkItemDao;
    /**
     * 根据id获取学生信息
     * @param id int
     * @return Class
     */
    public Student getStudentById(int id){
        return studentDao.getStudentById(id);
    }

    /**
     * 根据用户名获取学生信息
     * @param username String
     * @return Class
     */
    public Student getStudentByName(String username){
        return studentDao.getStudentByName(username);
    }

    /**
     * 添加学生信息
     * @param student Class 用户名
     * @return int
     */
    public int addStudent(Student student){
        return studentDao.addStudent(student);
    }

    /**
     * 学生加入班级
     * @param id int
     * @param uuid String
     * @return bool
     */
    @Transactional
    public Classes joinClass(int id, String uuid){
        Classes clazz = classesDao.getIdByUuid(uuid);
        if(clazz == null){
            return null;
        }
        int res = studentDao.updateClassIdById(id,clazz.getId());
        int ret = classesDao.updateCountById(clazz.getId());
        if(res > 0){
            return clazz;
        }else{
            return null;
        }
    }
    @Transactional
    public boolean complete(int studentId, CompleteTask completeTask){
        int score = 0;
        //1.插入数据到完成作业表中
        int taskId = Integer.parseInt(completeTask.getTaskId());
        Homework homework = new Homework();
        homework.setStudentId(studentId);
        homework.setTaskId(taskId);
        homeworkDao.add(homework);
        //2.插入数据到完成作业详情表中
        int hid = homework.getId();
        List<HomeworkItem> items = new ArrayList<>();
        for (int i = 0; i < completeTask.getQuestion().size(); i++) {
            String question = completeTask.getQuestion().get(i);
            String answer = completeTask.getAnswer().get(i);
            String result =completeTask.getResult().get(i);
            HomeworkItem item = new HomeworkItem();
            item.setHid(hid);
            item.setQuestion(question);
            item.setAnswer(answer);
            item.setResult(result);
            if(result.equals(answer)){
                score++;//记录学豆
                item.setCorrect(1);
            }else{
                item.setCorrect(0);
            }
            items.add(item);
        }
        int res = homeworkItemDao.add(items);

        if(score >=6){//3.如果做对的题目大于6题，才能发放学豆
            studentDao.updateScoreById(studentId,score);
        }
        if (res > 0){
            return true;
        }else{
            return false;
        }
    }



}
