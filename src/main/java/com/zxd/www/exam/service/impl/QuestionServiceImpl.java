package com.zxd.www.exam.service.impl;

import com.zxd.www.exam.entity.Question;
import com.zxd.www.exam.entity.QuestionBand;
import com.zxd.www.exam.mapper.QuestionMapper;
import com.zxd.www.exam.service.QuestionBandService;
import com.zxd.www.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-21 17:26
 **/
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionBandService bandService;

    @Override
    public boolean add(Question question) {
        if (questionMapper.insert(question)) {
            bandService.update(question.getBandId());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer questionId) {
        return questionMapper.delete(questionId);
    }

    @Override
    public List<Question> getByBandId(Integer bandId) {
        return questionMapper.selectQuestionByBandId(bandId);
    }

    @Override
    public boolean update(Question question) {
        return questionMapper.update(question);
    }
}
