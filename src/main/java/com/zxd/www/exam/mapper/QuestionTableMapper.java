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
     * 批量插入已选题目号
     * @param selectedIds 已选题目id列表
     * @param tableId 题目表id
     */
    void bandSelectedToTable(@Param("selectedIds") List<Integer> selectedIds,@Param("tableId") Integer tableId);

}
