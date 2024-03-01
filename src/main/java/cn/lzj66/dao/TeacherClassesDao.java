package cn.lzj66.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeacherClassesDao {
    /**
     * 添加老师和班级的关系
     * @param teacherId int
     * @param classId int
     * @return int
     */
    int add(@Param("teacherId") int teacherId,@Param("classId") int classId);


}
