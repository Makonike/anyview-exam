package com.zxd.www.exam.service;

import com.zxd.www.exam.entity.Question;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-21 17:18
 **/
public interface QuestionService {

    boolean add(Question question);

    boolean delete(Integer questionId);

    List<Question> getByBandId(Integer bandId);

    boolean update(Question question);
}
