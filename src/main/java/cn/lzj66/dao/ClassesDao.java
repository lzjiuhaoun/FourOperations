package cn.lzj66.dao;

import cn.lzj66.entity.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassesDao {
    /**
     * 根据班级id去返回班级信息
     * @param id int
     * @return Class
     */
    Classes getClassesById(int id);

    /**
     * 添加班级接口
     * @param classes Class
     * @return int
     */
    int addClass(Classes classes);

    /**
     * 根据班级的uuid(邀请码)返回班级信息
     * @param uuid string
     * @return Class
     */
    Classes getIdByUuid(String uuid);

    List<Classes> getClassByTeacherAndId(@Param("teacherId") int teacherId);

    int updateCountById(int classId);

}
