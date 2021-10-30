package com.zxd.www.exam.service.impl;

import com.zxd.www.exam.entity.QuestionBand;
import com.zxd.www.exam.mapper.QuestionBandMapper;
import com.zxd.www.exam.service.QuestionBandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-20 20:02
 **/
@Service
public class QuestionBandServiceImpl implements QuestionBandService {

    @Autowired
    private QuestionBandMapper bandMapper;


    @Override
    public boolean add(QuestionBand questionBand) {
        return bandMapper.insert(questionBand);
    }

    /**
     * 关于题库内题目的删除暂时不做
     * @param questionBandId 题库id
     */
    @Override
    public boolean delete(Integer questionBandId) {
        return bandMapper.delete(questionBandId);
    }

    /**
     * 获取所有题库列表
     */
    @Override
    public List<QuestionBand> getList() {
        return bandMapper.selectList();
    }

    /**
     * 作用是借用数据库设置的更加时间戳更新来更新时间戳
     * 当加入题目时，调用该方法更新时间戳
     * @param questionBandId 题库id
     */
    @Override
    public void update(Integer questionBandId) {
        bandMapper.update(questionBandId);
    }

    /**
     * 修改题库名
     * @param questionBand 题库
     */
    @Override
    public boolean updateName(QuestionBand questionBand) {
        return bandMapper.updateName(questionBand);
    }
}
