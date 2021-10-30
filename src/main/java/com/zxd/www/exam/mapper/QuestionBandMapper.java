package com.zxd.www.exam.mapper;

import com.zxd.www.exam.entity.QuestionBand;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-20 19:42
 **/
@Repository
public interface QuestionBandMapper {

    boolean insert(QuestionBand questionBand);

    boolean delete(Integer questionBandId);

    List<QuestionBand> selectList();

    /**
     * 作用是借用数据库设置的更加时间戳更新来更新时间戳
     * 当加入题目时，调用该方法更新时间戳
     * @param questionBandId 题库id
     */
    void update(Integer questionBandId);

    boolean updateName(QuestionBand questionBand);

}
