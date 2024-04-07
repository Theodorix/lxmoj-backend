package com.yupi.lxmoj.judge;


import com.yupi.lxmoj.judge.codesandbox.model.JudgeInfo;
import com.yupi.lxmoj.judge.strategy.DefaultJudgeStrategy;
import com.yupi.lxmoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.yupi.lxmoj.judge.strategy.JudgeContext;
import com.yupi.lxmoj.judge.strategy.JudgeStrategy;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        String language = judgeContext.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
