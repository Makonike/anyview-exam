package com.zxd.www.exam.service;

/**
 * @author Makonike
 * @date 2021-10-15 23:06
 **/
public interface ExamService {

    String getExamNoByUserId(Integer userId);

    String getExamNoByAdminId(Integer adminId);
}
