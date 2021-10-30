package com.zxd.www.exam.service.impl;

import com.zxd.www.exam.entity.SelectedQuestion;
import com.zxd.www.exam.mapper.SelectedQuestionMapper;
import com.zxd.www.exam.service.SelectedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-21 16:32
 **/
@Service
public class SelectedQuestionServiceImpl implements SelectedQuestionService {

    @Autowired
    private SelectedQuestionMapper questionMapper;

    /**
     * 批量插入已选题目
     * @param selectedQuestions 已选题目列表
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public boolean add(List<SelectedQuestion> selectedQuestions) {
        questionMapper.deleteAll(selectedQuestions.get(0).getTableId());
        return questionMapper.insert(selectedQuestions);
    }

    @Override
    public boolean delete(Integer selectedQuestionId) {
        return questionMapper.delete(selectedQuestionId);
    }

    /**
     * 获取某个题目表中的所有已选题目列表
     * @param tableId 题目表id
     */
    @Override
    public List<SelectedQuestion> getByTableId(Integer tableId) {
        return questionMapper.selectByTableId(tableId);
    }
}
