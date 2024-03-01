package cn.lzj66.dao;

import cn.lzj66.entity.HomeworkItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeworkItemDao {
    /**
     * 添加学生作业详情
     * @param homeworkItems List
     * @return int
     */
    int add(@Param("items") List<HomeworkItem> homeworkItems);

    /**
     * 返回学生作业完成详情内容
     * @param hid int
     * @return List
     */
    List<HomeworkItem> getHomeworkItemByHid(int hid);
}
