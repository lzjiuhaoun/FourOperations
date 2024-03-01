package cn.lzj66.controller;

import cn.lzj66.response.BaseResponse;
import cn.lzj66.entity.Student;
import cn.lzj66.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/class")
@Controller
public class ClassController {

    @Autowired
    private ClassesService service;
    @ResponseBody
    @RequestMapping(value = "/getSameStudent")
    public BaseResponse<Student> getSameStudent(int classId){
        List<Student> students = service.getSameStudent(classId);
        if(students.size() > 0){
            return new BaseResponse<>(true,"获取成功",students);
        }else{
            return new BaseResponse<>(false,"获取失败",null);
        }
    }
}
