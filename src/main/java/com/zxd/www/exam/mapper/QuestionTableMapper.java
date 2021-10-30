package com.zxd.www.exam.mapper;

import com.zxd.www.exam.entity.QuestionTableEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-20 9:19
 **/
@Repository
public interface QuestionTableMapper {

    boolean insert(QuestionTableEntity questionTableEntity);

    boolean delete(Integer questionTableId);

    /**
     * 查看某个教师保存的题目表列表
     * @param teacherId 教师id
     */
    List<QuestionTableEntity> selectByTeacherId(Integer teacherId);

    /**
     * 修改测验题目表名
     * @param questionTableEntity 测验题目表
     */
    boolean update(QuestionTableEntity questionTableEntity);

    /**
     * 根据题目表名搜索
     * @param tableName 题目表名
     */
    QuestionTableEntity selectByName(String tableName);
}
