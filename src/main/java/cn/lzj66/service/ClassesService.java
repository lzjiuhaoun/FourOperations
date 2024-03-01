package cn.lzj66.service;

import cn.lzj66.dao.ClassesDao;
import cn.lzj66.dao.StudentDao;
import cn.lzj66.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesService {

    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private StudentDao studentDao;
    public boolean addClass(int teacherId,String name,String desc){
        return true;
    }

    public List<Student> getSameStudent(int classId){
        return studentDao.getSameStudent(classId);
    }
}
