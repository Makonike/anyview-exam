package com.zxd.www.clazz.mapper;

import com.zxd.www.clazz.entity.ClassEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-16 13:37
 **/
@Repository
public interface ClassMapper {

    boolean insert(ClassEntity classEntity);

    boolean update(ClassEntity classEntity);

    boolean delete(Integer classId);

    Integer selectClassIdByUserId(Integer userId);

    ClassEntity selectByClassId(Integer classId);

    boolean bindClassToExam(@Param("classId") Integer classId,@Param("examId") Integer examId);

    List<ClassEntity> selectList();

    List<Integer> getExamClass(Integer adminId);

    List<Integer> getExamClassByTeacherId(Integer teacherId);

}
