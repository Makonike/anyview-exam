package com.zxd.www.clazz.mapper;

import com.zxd.www.clazz.entity.SchoolEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-29 17:45
 **/
@Repository
public interface SchoolMapper {

    boolean insert(SchoolEntity schoolEntity);

    boolean delete(Integer schoolId);

    SchoolEntity selectById(Integer schoolId);

    List<SchoolEntity> selectList();

    boolean update(SchoolEntity schoolEntity);

}
