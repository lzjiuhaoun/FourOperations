package cn.lzj66.service;

import cn.lzj66.dao.HomeworkDao;
import cn.lzj66.dao.HomeworkItemDao;
import cn.lzj66.dao.TaskDao;
import cn.lzj66.dao.TaskItemDao;
import cn.lzj66.entity.HomeworkItem;
import cn.lzj66.entity.Task;
import cn.lzj66.entity.TaskItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private TaskItemDao taskItemDao;
    @Autowired
    private HomeworkDao homeworkDao;
    @Autowired
    private HomeworkItemDao homeworkItemDao;
    /**
     * 根据班级获取作业信息
     * @param classId int
     * @return List
     */
    public List<Task> getTaskList(int classId,int studentId){
        return taskDao.getListTaskByClassId(classId,studentId);
    }
    public List<TaskItem> getTaskItemList(int taskId){
        return taskItemDao.getTaskById(taskId);
    }
    public List<HomeworkItem> getCompleteTaskItem(int studentId,int taskId){
        int hid = homeworkDao.getHidByTaskId(studentId,taskId);
        return homeworkItemDao.getHomeworkItemByHid(hid);
    }
}
