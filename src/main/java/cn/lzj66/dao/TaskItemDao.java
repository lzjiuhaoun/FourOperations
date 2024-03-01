package cn.lzj66.dao;

import cn.lzj66.entity.TaskItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskItemDao {
    /**
     * 根据作业id来获取作业的详情信息
     * @param taskId int
     * @return List
     */
    List<TaskItem> getTaskById(int taskId);

    /**
     * 增加作业详情信息
     * @param list List
     * @return int
     */
    int addBatchItem(@Param("list") List<TaskItem> list);
}
