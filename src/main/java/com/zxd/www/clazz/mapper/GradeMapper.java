package com.zxd.www.clazz.mapper;

import com.zxd.www.clazz.entity.GradeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-30 13:57
 **/
@Repository
public interface GradeMapper {

    List<GradeEntity> selectList();

}
