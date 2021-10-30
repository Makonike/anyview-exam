package com.zxd.www.exam.mapper;

import com.zxd.www.exam.entity.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-21 17:11
 **/
@Repository
public interface QuestionMapper {

    boolean insert(Question question);

    boolean delete(Integer questionId);

    List<Question> selectQuestionByBandId(Integer bandId);

    boolean update(Question question);
}

