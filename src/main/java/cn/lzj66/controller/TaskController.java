package cn.lzj66.controller;

import cn.lzj66.entity.HomeworkItem;
import cn.lzj66.entity.Task;
import cn.lzj66.entity.TaskItem;
import cn.lzj66.response.BaseResponse;
import cn.lzj66.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ResponseBody
    @RequestMapping("/getTask")
    public BaseResponse<Task> getTask(int studentId,int classId) {
        List<Task> tasks = taskService.getTaskList(classId,studentId);
        if (tasks.size() > 0) {
            return new BaseResponse<>(true, "获取成功", tasks);
        } else {
            return new BaseResponse<>(false, "获取失败", null);
        }
    }

    @RequestMapping(value = "/getTaskItem")
    public String getTaskItem(int taskId, ModelMap map) {
        List<TaskItem> items = taskService.getTaskItemList(taskId);
        map.addAttribute("items",items);
        return "student/taskItem";
    }
    @RequestMapping(value = "/teacherTaskItem")
    public String getTeacherTaskItem(int taskId, ModelMap map) {
        List<TaskItem> items = taskService.getTaskItemList(taskId);
        map.addAttribute("items",items);
        return "teacher/taskItem";
    }
    @RequestMapping(value = "/getCompleteTaskItem")
    public String getCompleteTaskItem(int taskId,int studentId,ModelMap map){
        List<HomeworkItem> items = taskService.getCompleteTaskItem(studentId,taskId);
        map.addAttribute("items",items);
        return "student/completeTaskItem";
    }
}
