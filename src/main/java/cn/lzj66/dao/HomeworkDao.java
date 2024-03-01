package cn.lzj66.dao;

import cn.lzj66.entity.Homework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HomeworkDao {
    /**
     * 添加学生作业信息
     * @param homework Class
     * @return int
     */
    int add(Homework homework);

    /**
     * 根据作业id和学生id获取学生作业的id
     * @param studentId int
     * @param taskId int
     * @return int
     */
    int getHidByTaskId(@Param("studentId") int studentId, @Param("taskId") int taskId);
}
