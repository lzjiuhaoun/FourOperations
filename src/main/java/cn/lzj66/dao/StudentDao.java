package cn.lzj66.dao;

import cn.lzj66.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentDao {
    /**
     * 根据id获取学生信息
     * @param id int
     * @return Class
     */
    Student getStudentById(int id);
    Student getStudentById2(int id);

    /**
     * 根据用户名获取学生信息
     * @param name string
     * @return Class
     */
    Student getStudentByName(String name);

    /**
     * 添加学生信息
     * @param student Class
     * @return int
     */
    int addStudent(Student student);

    /**
     * 根据学生id来更新班级信息
     * @param id int
     * @param classId int
     * @return int
     */
    int updateClassIdById(@Param("id") int id,@Param("classId") int classId);

    /**
     * 根据学生id来更新学豆信息
     * @param id int
     * @param score int
     * @return int
     */
    int updateScoreById(@Param("id") int id,@Param("score") int score);
    /**
     * 根据班级
     * @param classId int
     * @return List
     */
    List<Student> getSameStudent(int classId);
}
