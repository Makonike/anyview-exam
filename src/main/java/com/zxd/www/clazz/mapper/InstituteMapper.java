package com.zxd.www.clazz.mapper;

import com.zxd.www.clazz.entity.InstituteEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-29 18:15
 **/
@Repository
public interface InstituteMapper {

    boolean insert(InstituteEntity instituteEntity);

    boolean delete(Integer instituteId);

    boolean update(InstituteEntity instituteEntity);

    InstituteEntity selectById(Integer instituteId);

    List<InstituteEntity> selectList();

}
