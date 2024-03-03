package cn.lzj66.controller;

import cn.lzj66.response.BaseResponse;
import cn.lzj66.entity.Classes;
import cn.lzj66.entity.Student;
import cn.lzj66.request.CompleteTask;
import cn.lzj66.request.Login;
import cn.lzj66.request.Register;
import cn.lzj66.service.StudentService;
import cn.lzj66.util.Encrypt;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/index")
    public String studentIndex() {
        return "student/index";
    }

    @ResponseBody //返回json数据
    @RequestMapping(value = "/login")
    public BaseResponse<Student> studentLogin(HttpServletRequest request, Login login) {
        if ("".equals(login.getUsername()) || login.getUsername() == null) {
            return new BaseResponse<>(false, "账号不能为空", null);
        }
        if ("".equals(login.getPassword()) || login.getPassword() == null) {
            return new BaseResponse<>(false, "密码不能为空", null);
        }
        Student student = studentService.getStudentByName(login.getUsername());
        if (student == null || student.getPassword() == null || student.getUsername() == null) {
            return new BaseResponse<>(false, login.getUsername() + "用户不存在", null);
        }
        if (Encrypt.comparePassword(student.getPassword(), login.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("student", student);
            return new BaseResponse<Student>(true, "登录成功", Arrays.asList(student));
        } else {
            return new BaseResponse<>(false, "密码不正确", null);
        }
    }

    @ResponseBody
    @RequestMapping("/register")
    public BaseResponse<Object> doStudent(Register register) {
        if (register == null || "".equals(register.getUsername()) || "".equals(register.getPassword())) {
            return new BaseResponse<>(false, "参数不能为空", null);
        }
        String encryPd = Encrypt.encodeMD5(register.getPassword());
        Student student = new Student();
        student.setUsername(register.getUsername());
        student.setPassword(encryPd);
        student.setMobile(register.getMobile());
        student.setEmail(register.getEmail());
        student.setHeadUrl("../img/man_head.jpg");
        int res = studentService.addStudent(student);
        if (res > 0) {
            return new BaseResponse<>(true, "注册成功", null);
        } else {
            return new BaseResponse<>(true, "注册失败", null);
        }
    }

    @ResponseBody
    @RequestMapping("/joinClass")
    public BaseResponse<Classes> joinClass(String code, HttpServletRequest request) {
        if ("".equals(code) || code == null) {
            return new BaseResponse<>(false, "邀请码不能为空", null);
        }
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        int studentId = student.getId();
        Classes classes = studentService.joinClass(studentId, code);
        if (classes != null) {
            return new BaseResponse<Classes>(true, "加入班级成功", Arrays.asList(classes));
        } else {
            return new BaseResponse<>(false, "不合法的邀请码", null);
        }
    }

    @RequestMapping(value = "/complete")
    @ResponseBody
    public BaseResponse<Object> complete(@RequestBody String str, HttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession();
        try {
            CompleteTask completeTask = mapper.readValue(str, CompleteTask.class);
            System.out.println(completeTask);
            Student student = (Student) session.getAttribute("student");
            int studentId = student.getId();
            boolean flag = studentService.complete(studentId, completeTask);
            if (flag) {
                return new BaseResponse<>(true, "答题成功", null);
            } else {
                return new BaseResponse<>(false, "答题失败", null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new BaseResponse<>(false, "解析json出错", null);
        }
    }


}
