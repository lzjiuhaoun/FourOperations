package cn.lzj66.dao;

import cn.lzj66.request.Subject;
import cn.lzj66.response.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubjectDao {
    /**
     * 添加题库信息
     * @param subject Class
     * @return int
     */
    int addSubject(Subject subject);

    int addSubjectList(@Param("subjects") List<Subject> subjects);

    List<Question> getSubjectByDifficult(@Param("difficult") int difficult, @Param("count") int count);
}
