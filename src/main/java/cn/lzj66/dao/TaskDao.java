package cn.lzj66.dao;

import cn.lzj66.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskDao {
    /**
     * 根据班级号和学生号（查看是否做过作业）的作业信息
     * @param classId int
     * @param studentId int
     * @return List
     */
    List<Task> getListTaskByClassId(@Param("classId") int classId, @Param("studentId") int studentId);

    /**
     * 添加作业
     * @param task Class
     * @return int
     */
    int addTask(Task task);

    List<Task> getListTaskByTeacherId(@Param("teacherId") int teacherId);
}
