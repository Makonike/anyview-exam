/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 30/10/2021 17:59:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for conn_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `conn_admin_role`;
CREATE TABLE `conn_admin_role`  (
  `admin_role_id` int(0) NOT NULL AUTO_INCREMENT,
  `admin_id` int(0) NOT NULL,
  `role_id` int(0) NOT NULL,
  PRIMARY KEY (`admin_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for conn_course_class
-- ----------------------------
DROP TABLE IF EXISTS `conn_course_class`;
CREATE TABLE `conn_course_class`  (
  `course_class_id` int(0) NOT NULL AUTO_INCREMENT,
  `course_id` int(0) NOT NULL,
  `class_id` int(0) NOT NULL,
  PRIMARY KEY (`course_class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for conn_course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `conn_course_teacher`;
CREATE TABLE `conn_course_teacher`  (
  `course_teacher_id` int(0) NOT NULL AUTO_INCREMENT,
  `course_id` int(0) NOT NULL,
  `teacher_id` int(0) NOT NULL,
  PRIMARY KEY (`course_teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for conn_exam_class
-- ----------------------------
DROP TABLE IF EXISTS `conn_exam_class`;
CREATE TABLE `conn_exam_class`  (
  `exam_class_id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_id` int(0) NOT NULL,
  `class_id` int(0) NOT NULL,
  PRIMARY KEY (`exam_class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for conn_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `conn_role_permission`;
CREATE TABLE `conn_role_permission`  (
  `role_per_id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NOT NULL,
  `permission_id` int(0) NOT NULL,
  PRIMARY KEY (`role_per_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `admin_id` int(0) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `admin_password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `salt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE,
  INDEX `idx_adminid`(`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class`  (
  `class_id` int(0) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '专业+班级名',
  `school_id` int(0) NOT NULL COMMENT '学校id',
  `institute_id` int(0) NOT NULL COMMENT '学院id',
  `grade_id` int(0) NOT NULL COMMENT '年级id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `class_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '班级描述/备用字段',
  `removed` tinyint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`class_id`) USING BTREE,
  INDEX `idx_classid_removed`(`class_id`, `removed`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `course_id` int(0) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `removed` tinyint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_exam
-- ----------------------------
DROP TABLE IF EXISTS `t_exam`;
CREATE TABLE `t_exam`  (
  `exam_id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `table_id` int(0) NOT NULL DEFAULT 1,
  `teacher_id` int(0) NOT NULL COMMENT '发起测试的老师id',
  `setup_time` datetime(0) NULL DEFAULT NULL COMMENT '开始准备的时间',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始测试时间',
  `exp_time` datetime(0) NULL DEFAULT NULL COMMENT '测验结束时间',
  `exam_time` int(0) NOT NULL DEFAULT 1 COMMENT '测试时长，单位为分钟',
  `status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '0-未开始 1-准备 2-进行中 3-已结束',
  PRIMARY KEY (`exam_id`) USING BTREE,
  INDEX `idx_teacherid`(`teacher_id`) USING BTREE,
  INDEX `idx_examid_status`(`exam_id`, `status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade`  (
  `grade_id` int(0) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `removed` tinyint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`grade_id`) USING BTREE,
  INDEX `idx_gradeid_removed`(`grade_id`, `removed`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_institute
-- ----------------------------
DROP TABLE IF EXISTS `t_institute`;
CREATE TABLE `t_institute`  (
  `institute_id` int(0) NOT NULL AUTO_INCREMENT,
  `institute_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `removed` tinyint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`institute_id`) USING BTREE,
  INDEX `idx_instituteid_removed`(`institute_id`, `removed`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `permission_id` int(0) NOT NULL AUTO_INCREMENT,
  `permission_operation` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question`  (
  `question_id` int(0) NOT NULL AUTO_INCREMENT,
  `band_id` int(0) NOT NULL DEFAULT 1 COMMENT '所属题库id',
  `question_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '问题名',
  `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '题目',
  `sections` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `difficulty` tinyint(0) NOT NULL DEFAULT 0 COMMENT '题目难度 0-打卡题 1-简单 2-中等 3-难',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`question_id`) USING BTREE,
  INDEX `idx_bandid`(`band_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_question_band
-- ----------------------------
DROP TABLE IF EXISTS `t_question_band`;
CREATE TABLE `t_question_band`  (
  `question_band_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '题库id',
  `question_band_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `removed` tinyint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`question_band_id`) USING BTREE,
  INDEX `idx_bandid_removed`(`question_band_id`, `removed`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_question_table
-- ----------------------------
DROP TABLE IF EXISTS `t_question_table`;
CREATE TABLE `t_question_table`  (
  `question_table_id` int(0) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '测验题目表名',
  `teacher_id` int(0) NOT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `removed` tinyint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`question_table_id`) USING BTREE,
  INDEX `idx_tableid_removed`(`question_table_id`, `removed`) USING BTREE,
  INDEX `idx_teacherid_removed`(`teacher_id`, `removed`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_school
-- ----------------------------
DROP TABLE IF EXISTS `t_school`;
CREATE TABLE `t_school`  (
  `school_id` int(0) NOT NULL AUTO_INCREMENT,
  `school_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `removed` tinyint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`school_id`) USING BTREE,
  INDEX `idx_schoolid_removed`(`school_id`, `removed`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_selected_question
-- ----------------------------
DROP TABLE IF EXISTS `t_selected_question`;
CREATE TABLE `t_selected_question`  (
  `selected_question_id` int(0) NOT NULL AUTO_INCREMENT,
  `order` int(0) NOT NULL,
  `question_id` int(0) NOT NULL,
  `table_id` int(0) NOT NULL DEFAULT 1,
  `score` int(0) NOT NULL,
  PRIMARY KEY (`selected_question_id`) USING BTREE,
  INDEX `idx_tableid`(`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `student_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `student_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `student_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` tinyint(0) NOT NULL DEFAULT 0,
  `class_id` int(0) NOT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `removed` tinyint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`student_id`) USING BTREE,
  INDEX `idx_studentid_removed`(`student_id`, `removed`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher`  (
  `teacher_id` int(0) NOT NULL AUTO_INCREMENT,
  `admin_id` int(0) NOT NULL,
  `teacher_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` tinyint(0) NOT NULL DEFAULT 0 COMMENT '0-男 1-女',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `removed` tinyint(0) NOT NULL DEFAULT 0 COMMENT '0-未删除 1-已删除',
  PRIMARY KEY (`teacher_id`) USING BTREE,
  INDEX `idx_teacherid_removed`(`teacher_id`, `removed`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `salt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
