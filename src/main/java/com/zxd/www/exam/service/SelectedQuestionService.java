package com.zxd.www.exam.service;

import com.zxd.www.exam.entity.SelectedQuestion;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-21 16:30
 **/
public interface SelectedQuestionService {

    boolean add(List<SelectedQuestion> selectedQuestions);

    boolean delete(Integer selectedQuestionId);

    List<SelectedQuestion> getByTableId(Integer tableId);

}
