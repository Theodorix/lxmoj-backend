# DROP DATABASE lxmoj;

-- 创建库
create database if not exists lxmoj;

use lxmoj;

# DROP TABLE user;

-- 用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
  `userAccount` VARCHAR(256) NOT NULL COMMENT '账号',
  `userPassword` VARCHAR(512) NOT NULL COMMENT '密码',
  `unionId` VARCHAR(256) NULL COMMENT '微信开放平台id',
  `mpOpenId` VARCHAR(256) NULL COMMENT '公众号openId',
  `userName` VARCHAR(256) NULL COMMENT '用户昵称',
  `userAvatar` VARCHAR(1024) NULL COMMENT '用户头像',
  `userProfile` VARCHAR(512) NULL COMMENT '用户简介',
  `gender` TINYINT NULL COMMENT '性别 0为女性 1为男性',
  `address` VARCHAR(256) NULL COMMENT '地址',
  `tags` VARCHAR(1024) NULL COMMENT '标签 JSON',
  `birthday` DATE NULL COMMENT '生日',
  `school` VARCHAR(256) NULL COMMENT '就读学校',
  `company` VARCHAR(256) NULL COMMENT '公司',
  `position` VARCHAR(256) NULL COMMENT '职位',
  `userRole` VARCHAR(256) DEFAULT 'user' NOT NULL COMMENT '用户角色：user/admin/ban',
  `gitHubName` VARCHAR(256) NULL COMMENT 'GitHub用户名', -- 新增的列
  `websites` VARCHAR(1024) NULL COMMENT '用户网站', -- 新增的列
  `createTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  `updateTime` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除'
) COMMENT '用户' COLLATE = utf8mb4_unicode_ci;

# DROP TABLE question;

-- 题目表
CREATE TABLE IF NOT EXISTS question (
    id           BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    title        VARCHAR(512)                       NULL COMMENT '标题',
    content      TEXT                               NULL COMMENT '内容',
    tags         VARCHAR(1024)                      NULL COMMENT '标签列表（json 数组）',
    frontendCode TEXT                               NULL COMMENT '前端代码',
    logicCode    TEXT                               NULL COMMENT '逻辑代码', -- 新增的列
    backendCode  TEXT                               NULL COMMENT '后端代码',
    answer       TEXT                               NULL COMMENT '题目答案',
    difficulty   TINYINT                            NULL COMMENT '难度 1-简单 2-中等 3-困难',
    submitNum    INT      DEFAULT 0                 NOT NULL COMMENT '题目提交数',
    acceptedNum  INT      DEFAULT 0                 NOT NULL COMMENT '题目提交数',
    passRate     DECIMAL(5, 2) AS (IF((`submitNum` = 0), 0, ((`acceptedNum` / `submitNum`) * 100))) STORED COMMENT '通过率',
    judgeCase    TEXT                               NULL COMMENT '判题用例 (json 数组)',
    judgeConfig  TEXT                               NULL COMMENT '判题配置 (json 对象)',
    thumbNum     INT      DEFAULT 0                 NOT NULL COMMENT '点赞数',
    favourNum    INT      DEFAULT 0                 NOT NULL COMMENT '收藏数',
    userId       BIGINT                             NOT NULL COMMENT '创建用户 id',
    createTime   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updateTime   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    isDelete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除',
    INDEX idx_userId (userId)
) COMMENT '题目' COLLATE = utf8mb4_unicode_ci;

# -- 添加passRate2列（前端）
# ALTER TABLE question
#     ADD COLUMN passRate2 DECIMAL(5, 2) AS (IF((`submitNum` = 0), 0, ((`acceptedNum` / `submitNum`) * 100))) STORED COMMENT '通过率';
#
# -- 添加passRate列
# ALTER TABLE question
#     ADD COLUMN passRate DECIMAL(5, 2) GENERATED ALWAYS AS (passRate2) STORED;
#
# -- 创建触发器，在更新passRate2时更新passRate（前端）
# DELIMITER //
# CREATE TRIGGER update_passRate2_trigger BEFORE UPDATE ON question
#     FOR EACH ROW
# BEGIN
#     SET NEW.passRate = NEW.passRate2;
# END;
# //
# DELIMITER ;
#
# -- 删除触发器 update_passRate2_trigger
# DROP TRIGGER IF EXISTS update_passRate2_trigger;
#
#
# ALTER TABLE question
#     DROP COLUMN passRate,
#     DROP COLUMN passRate2;


ALTER TABLE question
    ADD COLUMN passRatePercent DECIMAL(5, 2) GENERATED ALWAYS AS (
        IF(submitNum = 0, 0, (acceptedNum / submitNum) * 100)
        ) STORED;

# DROP TABLE question_submit;

-- 题目提交表
CREATE TABLE IF NOT EXISTS question_submit (
   id           BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
   language     VARCHAR(128)                       NULL COMMENT '编程语言',
   code         TEXT                               NULL COMMENT '用户代码',
   judgeInfo    TEXT                               NULL COMMENT '判题信息（json 数组）',
   status       INT      DEFAULT 0                 NULL COMMENT '判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）',
   questionId   BIGINT                             NOT NULL COMMENT '题目 id',
   userId       BIGINT                             NOT NULL COMMENT '创建用户 id',
   createTime   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
   updateTime   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   isDelete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除',
   error_message VARCHAR(1024)                     NULL COMMENT '错误信息',
   remark       VARCHAR(1024)                     NULL COMMENT '备注',
   outPut       VARCHAR(1024)                     NULL COMMENT '输出结果',
   INDEX idx_questionId (questionId),
   INDEX idx_userId (userId)
) COMMENT '题目提交' COLLATE = utf8mb4_unicode_ci;

-- 题目点赞表（硬删除）
create table if not exists question_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    questionId bigint                             not null comment '题目 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_questionId (questionId),
    index idx_userId (userId)
) comment '题目点赞';

-- 题目收藏表（硬删除）
create table if not exists question_favour
(
    id         bigint auto_increment comment 'id' primary key,
    questionId bigint                             not null comment '题目 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_questionId (questionId),
    index idx_userId (userId)
) comment '题目收藏';

DROP TABLE post;

-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    cover      varchar(1024)                      null comment '封面',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子收藏';
