package com.zxd.www.exam.service.impl;

import com.zxd.www.exam.entity.QuestionTableEntity;
import com.zxd.www.exam.mapper.QuestionTableMapper;
import com.zxd.www.exam.service.QuestionTableService;
import com.zxd.www.exam.service.SelectedQuestionService;
import com.zxd.www.sys.entity.SysAdminEntity;
import com.zxd.www.sys.entity.Teacher;
import com.zxd.www.sys.service.SysAdminService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-20 18:48
 **/
@Service
public class QuestionTableServiceImpl implements QuestionTableService {

    @Autowired
    private QuestionTableMapper tableMapper;

    @Autowired
    private SysAdminService adminService;

    @Autowired
    private SelectedQuestionService selectedQuestionService;

    @Override
    public boolean add(QuestionTableEntity questionTableEntity) {

        QuestionTableEntity byName = getByName(questionTableEntity.getTableName());
        if(byName != null){
            return false;
        }
        SysAdminEntity admin = (SysAdminEntity) SecurityUtils.getSubject().getPrincipal();
        // 获取teacherId
        Teacher teacher = adminService.teacherInfo(admin.getAdminId());
        questionTableEntity.setTeacherId(teacher.getTeacherId());

        return tableMapper.insert(questionTableEntity);
    }

    @Override
    public boolean delete(Integer questionTableId) {
        return tableMapper.delete(questionTableId);
    }

    @Override
    public List<QuestionTableEntity> getByTeacherId() {
        SysAdminEntity admin = (SysAdminEntity) SecurityUtils.getSubject().getPrincipal();
        // 获取teacherId
        Teacher teacher = adminService.teacherInfo(admin.getAdminId());

        return tableMapper.selectByTeacherId(teacher.getTeacherId());
    }

    @Override
    public boolean update(QuestionTableEntity questionTableEntity) {
        QuestionTableEntity byName = getByName(questionTableEntity.getTableName());
        if(byName != null){
            return false;
        }
        return tableMapper.update(questionTableEntity);
    }

    @Override
    public QuestionTableEntity getByName(String tableName) {
        return tableMapper.selectByName(tableName);
    }

}
