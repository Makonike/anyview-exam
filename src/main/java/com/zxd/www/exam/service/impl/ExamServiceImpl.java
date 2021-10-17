package com.zxd.www.exam.service.impl;

import com.zxd.www.clazz.service.ClassService;
import com.zxd.www.exam.entity.Exam;
import com.zxd.www.exam.mapper.ExamMapper;
import com.zxd.www.exam.service.ExamService;
import com.zxd.www.sys.entity.SysAdminEntity;
import com.zxd.www.sys.mapper.TeacherMapper;
import com.zxd.www.websocket.service.WebSocketService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Makonike
 * @date 2021-10-15 23:26
 **/
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ClassService classService;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 为未开始的测验设置为自动测验，判断冲突
     * 教师保存测验设置好准备时间和开始时间，测验时长后计算测验结束时间
     * 等到准备开始，通知所有测验的学生，然后等待开始测验
     * @param exam 测验
     */
    @Override
    public boolean autoExamSave(Exam exam) {

        if(exam.getSetupTime().isAfter(exam.getStartTime())){
            return false;
        }
        // 获取当前管理员
        SysAdminEntity admin = (SysAdminEntity) SecurityUtils.getSubject().getPrincipal();
        // 计算结束时长
        exam.setExpTime(exam.getStartTime().plusMinutes(exam.getExamTime()));

        // 排除冲突
        List<Integer> examClass = classService.getExamClass(admin.getAdminId());
        for (Integer aClass : examClass) {
            // 遍历列表，获取班级的所有测验安排
            if(examMapper.countExamPeriod(aClass, exam.getSetupTime(), exam.getExpTime()) > 0){
                return false;
            }
        }
        // TODO: 计时器，等到测验准备时间通知所有参与测验的学生,还有调用自动准备，自动开始，自动结束的service方法
        return examMapper.autoExamSave(exam);
    }

    /**
     * 手动保存测验
     * 教师保存测验，设置状态为未开始，此时可以修改测验编排
     * 等到准备时间开始，通知所有测验的学生
     * 等待老师手动开启测验
     * @param exam 测验
     */
    @Override
    public boolean examSave(Exam exam) {
        // 获取当前管理员
        SysAdminEntity admin = (SysAdminEntity) SecurityUtils.getSubject().getPrincipal();
        // 设置发布教师
        exam.setTeacherId(teacherMapper.selectByAdminId(admin.getAdminId()).getTeacherId());
        examMapper.examSave(exam);
        for (Integer classId : exam.getClassIds()) {
            classService.bindClassToExam(classId, exam.getExamId());
        }
        return true;
    }

    /**
     * 准备测验，判断冲突
     * 老师设置开始时间，测验进入准备状态，只能查看测验编排而不能修改
     * 测验进入准备状态，通知所有参与测验的学生强制跳转至测验, 等待测验开始
     * @param exam 测验
     */
    @Override
    public boolean examSetUp(Exam exam) {
        return false;
    }

    /**
     * 手动开始测验,设置测验开始时间为now,更新测验状态并通知所有测验的学生, 计算测验结束时间
     * 老师点击测验手动开始测验
     * 通知学生端的测验表界面中的“操作”列所有按钮变为可用
     * 更新学生端的测验表界面中的“开始时间”和“当前状态”
     * 倒计时开始
     * @param examId 测验id
     */
    @Override
    public boolean examStart(Integer examId) {
        return false;
    }

    /**
     * 自动开始测验
     * 到达时间自动开始测验
     * 通知学生端的测验表界面中的“操作”列所有按钮变为可用
     * 更新学生端的测验表界面中的“开始时间”和“当前状态”
     * @param examId 测验id
     */
    @Override
    public boolean autoExamStart(Integer examId){
        return false;
    }

    /**
     * 手动结束测验
     * 计算并修改测验时长,修改测验状态, 计算测验结束时间
     * 通知教师端，教师端弹出一个确认框。教师点击确认后
     * 通知学生端保存所有学生的代码，并跳转出测验表界面。(跳转到主页)
     * @param examId 测验id
     */
    @Override
    public boolean examStop(Integer examId) {
        return false;
    }

    /**
     * 自动结束测验
     * 修改测验状态
     * 通知教师端，教师端弹出一个确认框。教师点击确认后
     * 通知学生端保存所有学生的代码，并跳转出测验表界面。(跳转到主页)
     * @param examId 测验id
     */
    @Override
    public boolean autoExamStop(Integer examId){
        return false;
    }

    /**
     * 测验延时
     * 修改数据库中的测验时长，判断冲突
     * 通知所有学生端
     * @param examId 测验id
     * @param delayTime 延迟时间
     */
    @Override
    public boolean examDelay(Integer examId,Integer delayTime) {
        // 获取当前管理员
        SysAdminEntity admin = (SysAdminEntity) SecurityUtils.getSubject().getPrincipal();
        Exam old = getByExamId(examId);
        // 设置新的测验时长
        old.setExamTime(old.getExamTime() + delayTime);
        // 设置新的测验结束时间
        old.setExpTime(old.getStartTime().plusMinutes(old.getExamTime()));
        // 查找参与测验的班级号列表
        List<Integer> examClass = classService.getExamClass(admin.getAdminId());
        for (Integer aClass : examClass) {
            // 遍历列表，获取班级的所有测验安排
            if(examMapper.countExamPeriod(aClass, old.getSetupTime(), old.getExpTime()) > 0){
                return false;
            }
        }
        // 延迟测验
        boolean b = examMapper.examDelay(old);
        for (Integer aClass : examClass) {
            // TODO: 更新学生端的测验表界面和做题界面中的倒计时,重置计时器(？)
            webSocketService.sendMessageAll(aClass.toString(), "测验延迟"+delayTime + "分钟");
        }
        return b;
    }

    /**
     * 根据id获取测验信息
     * @param examId examId
     */
    @Override
    public Exam getByExamId(Integer examId) {
        return examMapper.getByExamId(examId);
    }
}
