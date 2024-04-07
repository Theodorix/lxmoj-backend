package com.yupi.lxmoj.judge;

import com.yupi.lxmoj.model.entity.QuestionSubmit;
import com.yupi.lxmoj.model.vo.QuestionSubmitRunResultResponse;

import java.util.List;

/**
 * 判题服务
 */
public interface JudgeService {

    /**
     * 判题
     *
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);


    /**
     * 运行
     *
     * @return
     */
    QuestionSubmitRunResultResponse doRun(long questionId, String code, String language, List<String> inputList);
}
