package cn.lzj66.controller;

import cn.lzj66.request.CreateTask;
import cn.lzj66.request.ListQuestion;
import cn.lzj66.request.Login;
import cn.lzj66.request.Register;
import cn.lzj66.request.Subject;
import cn.lzj66.response.BaseResponse;
import cn.lzj66.response.JsonClass;
import cn.lzj66.response.Question;
import cn.lzj66.entity.Classes;
import cn.lzj66.entity.Student;
import cn.lzj66.entity.Task;
import cn.lzj66.entity.Teacher;
import cn.lzj66.service.TeacherService;
import cn.lzj66.util.CalculateExp;
import cn.lzj66.util.Encrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Value("${goeasy.key.super}")
    private String superKey;
    @Value("${goeasy.regionHost}")
    private String regionHost;
    /**
     * 教师首页
     * @return Sting
     */
    @RequestMapping(value = "/index")
    public String teacherIndex(ModelMap map, HttpServletRequest request){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        int teacherId = teacher.getId();
        List<Task> tasks = teacherService.getTaskList(teacherId);
        map.addAttribute("tasks",tasks);
        return "teacher/index";
    }
    @RequestMapping(value = "/addTask")
    public String teacherAddTask(ModelMap map,HttpServletRequest request){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        int teacherId = teacher.getId();
        List<Classes> classes = teacherService.getClassList(teacherId);
        List<JsonClass> jsonClasses = new ArrayList<>();
        JsonClass jsonClass;
        for (Classes cl:classes) {
            jsonClass = new JsonClass();
            jsonClass.setValue(cl.getId());
            jsonClass.setText(cl.getName());
            jsonClasses.add(jsonClass);
        }
        ObjectMapper mapper = new ObjectMapper();
        String str = "{}";
        try {
            str = mapper.writeValueAsString(jsonClasses);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        map.addAttribute("str",str);
        return "teacher/addTask";
    }

    @RequestMapping(value = "/class")
    public String teacherClass(ModelMap map,HttpServletRequest request){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        int teacherId = teacher.getId();
        List<Classes> classes = teacherService.getClassList(teacherId);
        map.addAttribute("classes",classes);
        return "teacher/class";
    }
    @RequestMapping(value = "/classDetail")
    public String classDetail(@RequestParam("classId") int classId, @RequestParam("name") String name,@RequestParam("uuid") String uuid, ModelMap map){
        List<Student> students = teacherService.getAllStudentByClassId(classId);
        map.addAttribute("students",students);
        map.addAttribute("name",name);
        map.addAttribute("uuid",uuid);
        return "teacher/classDetail";
    }
    @RequestMapping(value = "/addClass")
    public String addClass(){
        return "teacher/addClass";
    }

    @RequestMapping(value = "/subject")
    public String subject(){
        return "teacher/subject";
    }
    @RequestMapping(value = "/message")
    public String teacherMessage(){
        return "teacher/message";
    }
    @RequestMapping(value = "/myself")
    public String teacherMyself(ModelMap map,HttpServletRequest request){
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        map.addAttribute("teacher",teacher);
        return "teacher/myself";
    }
    /**
     * 教师登录接口
     * @param login Class
     * @return Class
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public BaseResponse<Teacher> teacherLogin(Login login, HttpServletRequest request){
        if("".equals(login.getUsername()) || login.getUsername() == null){
            return new BaseResponse<>(false,"账号不能为空",null);
        }
        if("".equals(login.getPassword()) || login.getPassword() == null){
            return new BaseResponse<>(false,"密码不能为空",null);
        }
        Teacher teacher = teacherService.getTeacherByUserName(login.getUsername());
        if(teacher == null || teacher.getUsername() == null || teacher.getPassword() == null){
            return new BaseResponse<>(false,"该用户不存在",null);
        }
        if(Encrypt.comparePassword(teacher.getPassword(),login.getPassword())){
            HttpSession session = request.getSession();
            session.setAttribute("teacher",teacher);
            return new BaseResponse<>(true,"登录成功", Arrays.asList(teacher));
        }else{
            return new BaseResponse<>(false,"密码不正确",null);
        }
    }

    /**
     * 教师注册接口
     * @param register Register.class
     * @return Class
     */
    @ResponseBody
    @RequestMapping("/register")
    public BaseResponse<Object> doStudent(Register register){
        if(register == null || "".equals(register.getUsername()) || "".equals(register.getPassword())){
            return new BaseResponse<>(false,"参数不能为空",null);
        }
        String encryPd = Encrypt.encodeMD5(register.getPassword());//密码加密
        Teacher teacher = new Teacher();
        teacher.setPassword(encryPd);
        teacher.setUsername(register.getUsername());
        teacher.setMobile(register.getMobile());
        teacher.setEmail(register.getEmail());
        int res = teacherService.addTeacher(teacher);
        if (res > 0){
            return new BaseResponse<>(true,"注册成功", null);
        }else{
            return new BaseResponse<>(true,"注册失败", null);
        }
    }

    @RequestMapping(value = "/createQuestion")
    public String createQuestion(CreateTask task, ModelMap map){
        System.out.println(task);
        List<Question> list = teacherService.createQuestion(task.getDifficult(),task.getType(),task.getCount());
        map.addAttribute("questions",list);
        map.addAttribute("task",task);
        return "teacher/checkTask";
    }
    /**
     * 创建作业接口
     * @param task Class
     * @return Class
     */
    @ResponseBody
    @RequestMapping(value = "/createTask")
    public BaseResponse<Object> createTask(CreateTask task,HttpServletRequest request){
        if(task == null || "".equals(task.getTitle()) || task.getCount() == 0){
            return new BaseResponse<>(false,"数据不能为空",null);
        }
        System.out.println(task);
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        int teacherId = teacher.getId();
        ObjectMapper mapper = new ObjectMapper();
        try {
            ListQuestion listQuestion = mapper.readValue(task.getQuestionJson(),ListQuestion.class);
            boolean flag = teacherService.addTask(teacherId,task,listQuestion);
            if(flag){
                GoEasy goEasy = new GoEasy(regionHost,superKey);
                goEasy.publish("class"+task.getClassId(),"["+task.getTitle()+"]已经布置，请及时完成");
                return new BaseResponse<>(true,"发布成功",null);
            }else{
                return new BaseResponse<>(false,"发布失败",null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new BaseResponse<>(false,"json解析错误",null);
        }

    }

    /**
     * 创建班级接口
     * @param name string 班级名字
     * @param desc string 班级描述
     * @return Class
     */
    @ResponseBody
    @RequestMapping(value = "/createClass")
    public  BaseResponse<Object> createClass(@RequestParam("name") String name,@RequestParam("description") String desc,HttpServletRequest request){
        if("".equals(name) || "".equals(desc)){
            return new BaseResponse<>(false,"数据不能为空",null);
        }
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        int teacherId = teacher.getId();
        boolean flag = teacherService.addClass(teacherId,name,desc.trim());
        if(flag){
            return new BaseResponse<>(true,"创建成功",null);
        }else{
            return new BaseResponse<>(false,"创建失败",null);
        }
    }
    /**
     * 增加题库题目
     * @param str JSON字符串
     * @return Class
     */
    @ResponseBody
    @RequestMapping(value = "/addSubject")
    public BaseResponse<Object> addSubject(@RequestBody String str,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        ObjectMapper mapper = new ObjectMapper();
        try {
            Subject subject = mapper.readValue(str,Subject.class);
            System.out.println(subject);
            subject.setPublicerId(teacher.getId());
            /*验证题目是否正确*/
            CalculateExp exp = new CalculateExp();
            List expList = exp.transStr(subject.getQuestion());
            String postExp[] = exp.midToEnd(expList);
            String result = (String) exp.calculate(postExp);
            if (!result.equals(subject.getAnswer())) {
                return new BaseResponse<>(false,"请输入正确答案",null);
            }

            int flag = teacherService.addSubject(subject);
            if(flag > 0){
                return new BaseResponse<>(true,"提交成功",null);
            }else{
                return new BaseResponse<>(false,"失败",null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new BaseResponse<>(false,"解析json出错",null);
        }
    }
}
