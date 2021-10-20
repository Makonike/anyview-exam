package com.zxd.www.exam.service;

import com.zxd.www.exam.entity.QuestionTableEntity;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-20 18:42
 **/
public interface QuestionTableService {

    boolean add(QuestionTableEntity questionTableEntity);

    boolean delete(Integer questionTableId);

    List<QuestionTableEntity> getByTeacherId();

    void bandSelectedToTable(List<Integer> selectedIds, Integer tableId);
}
