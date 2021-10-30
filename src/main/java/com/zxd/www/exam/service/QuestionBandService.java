package com.zxd.www.exam.service;

import com.zxd.www.exam.entity.QuestionBand;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-20 20:00
 **/
public interface QuestionBandService {

    boolean add(QuestionBand questionBand);

    boolean delete(Integer questionBandId);

    List<QuestionBand> getList();

    void update(Integer questionBandId);

    boolean updateName(QuestionBand questionBand);

}
