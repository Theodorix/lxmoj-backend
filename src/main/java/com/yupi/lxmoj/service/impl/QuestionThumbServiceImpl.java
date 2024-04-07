package com.yupi.lxmoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.lxmoj.common.ErrorCode;
import com.yupi.lxmoj.exception.BusinessException;
import com.yupi.lxmoj.mapper.QuestionThumbMapper;
import com.yupi.lxmoj.model.entity.Question;
import com.yupi.lxmoj.model.entity.QuestionThumb;
import com.yupi.lxmoj.model.entity.User;
import com.yupi.lxmoj.service.QuestionService;
import com.yupi.lxmoj.service.QuestionThumbService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class QuestionThumbServiceImpl extends ServiceImpl<QuestionThumbMapper, QuestionThumb>
        implements QuestionThumbService {


    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionThumbMapper questionThumbMapper;


    /**
     * 点赞
     *
     * @param questionId 0.1 0.1 0 1 2
     * @param loginUser
     * @return int
     * @author: LXY
     * @description: TODO
     * @date: 2023/12/20 14:49
     **/
    @Override
    public int doQuestionThumb(long questionId, User loginUser) {
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已点赞
        long userId = loginUser.getId();
        // 每个用户串行点赞
        // 锁必须要包裹住事务方法
        QuestionThumbService questionThumbService = (QuestionThumbService) AopContext.currentProxy();
        synchronized (String.valueOf(userId).intern()) {
            return questionThumbService.doQuestionThumbInner(userId, questionId);
        }
    }

    /**
     * 封装了事务的方法
     *
     * @param userId
     * @param questionId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doQuestionThumbInner(long userId, long questionId) {
        QuestionThumb questionThumb = new QuestionThumb();
        questionThumb.setUserId(userId);
        questionThumb.setQuestionId(questionId);
        QueryWrapper<QuestionThumb> thumbQueryWrapper = new QueryWrapper<>(questionThumb);
        QuestionThumb oldQuestionThumb = this.getOne(thumbQueryWrapper);
        boolean result;
        // 已点赞
        if (oldQuestionThumb != null) {
            result = this.remove(thumbQueryWrapper);
            if (result) {
                // 点赞数 - 1
                result = questionService.update()
                        .eq("id", questionId)
                        .gt("thumbNum", 0)
                        .setSql("thumbNum = thumbNum - 1")
                        .update();
                return result ? -1 : 0;
            } else {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        } else {
            // 未点赞
            result = this.save(questionThumb);
            if (result) {
                // 点赞数 + 1
                result = questionService.update()
                        .eq("id", questionId)
                        .setSql("thumbNum = thumbNum + 1")
                        .update();
                return result ? 1 : 0;
            } else {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        }
    }

    @Override
    public long getQuestion_thumbStatus(long questionId, long userId) {
        QueryWrapper<QuestionThumb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId)
                .eq("questionId", questionId);
        // 如果记录数为 0，则返回 -1，否则返回 1
        return questionThumbMapper.selectCount(queryWrapper) > 0 ? 1 : -1;
    }
}




