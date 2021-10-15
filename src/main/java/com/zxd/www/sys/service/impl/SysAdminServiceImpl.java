package com.zxd.www.sys.service.impl;

import com.zxd.www.global.util.StringUtil;
import com.zxd.www.sys.entity.SysAdminEntity;
import com.zxd.www.sys.entity.Teacher;
import com.zxd.www.sys.mapper.SysAdminMapper;
import com.zxd.www.sys.mapper.TeacherMapper;
import com.zxd.www.sys.service.SysAdminService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * admin业务实现类
 * @author Makonike
 * @date 2021-10-14 17:35
 **/
@Service
public class SysAdminServiceImpl implements SysAdminService {

    @Autowired
    private SysAdminMapper adminMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 保存 admin
     * @param admin admin
     */
    @Override
    public boolean save(SysAdminEntity admin) {
        //生成20位随机字符串作为盐
        String salt = StringUtil.generateSalt(20);
        // sha256加密
        admin.setAdminPassword(new Sha256Hash(admin.getAdminPassword(), salt).toHex());
        admin.setSalt(salt);

        return adminMapper.insert(admin);
    }

    /**
     * 根据id删除admin
     * @param adminId adminId
     */
    @Override
    public boolean delete(Integer adminId) {
        return adminMapper.delete(adminId);
    }

    /**
     * 修改密码
     * @param admin admin
     */
    @Override
    public boolean update(SysAdminEntity admin) {
        //生成20位随机字符串作为盐
        String salt = StringUtil.generateSalt(20);
        // sha256加密
        admin.setAdminPassword(new Sha256Hash(admin.getAdminPassword(), salt).toHex());
        admin.setSalt(salt);

        return adminMapper.update(admin);
    }

    @Override
    public List<SysAdminEntity> list() {
        return adminMapper.selectList();
    }

    /**
     * 通过id获取
     * @param adminId id
     */
    @Override
    public SysAdminEntity getById(Integer adminId) {
        return adminMapper.selectByAdminId(adminId);
    }

    @Override
    public Teacher teacherInfo(Integer adminId) {
        return teacherMapper.selectByAdminId(adminId);
    }
}
