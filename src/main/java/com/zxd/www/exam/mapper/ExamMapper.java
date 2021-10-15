package com.zxd.www.exam.mapper;

import org.springframework.stereotype.Repository;

/**
 * @author Makonike
 * @date 2021-10-15 23:06
 **/
@Repository
public interface ExamMapper {

    String getExamNoByUserId(Integer userId);

    String getExamNoByAdminId(Integer adminId);

}
