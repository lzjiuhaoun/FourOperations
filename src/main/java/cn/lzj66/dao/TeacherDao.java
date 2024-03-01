package cn.lzj66.dao;

import cn.lzj66.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherDao {
    /**
     * 根据老师登录名来获取老师信息
     * @param username string
     * @return Class
     */
    Teacher getTeacherByName(String username);

    /**
     * 添加老师信息
     * @param teacher Class
     * @return int
     */
    int addTeacher(Teacher teacher);
    ;
}
