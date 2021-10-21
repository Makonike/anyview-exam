package com.zxd.www.exam.mapper;

import com.zxd.www.exam.entity.SelectedQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-21 13:10
 **/
@Repository
public interface SelectedQuestionMapper {

    boolean insert(List<SelectedQuestion> selectedQuestions);

    boolean delete(Integer selectedQuestionId);

    List<SelectedQuestion> selectByTableId(Integer tableId);

}
