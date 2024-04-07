package com.yupi.lxmoj.judge.codesandbox.impl;

import com.yupi.lxmoj.judge.codesandbox.CodeSandbox;
import com.yupi.lxmoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.lxmoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.lxmoj.judge.codesandbox.model.JudgeInfo;
import com.yupi.lxmoj.model.enums.JudgeInfoMessageEnum;
import com.yupi.lxmoj.model.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *示例代码沙箱（仅为了跑通业务流程）
 */
@Slf4j
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {

//        JavaNativeCodeSandboxOld javaNativeCodeSandboxOld = new JavaNativeCodeSandboxOld();
//
//        ExecuteCodeResponse executeCodeResponse = javaNativeCodeSandboxOld.executeCode(executeCodeRequest);

        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.WAITING.getText());
        judgeInfo.setMemory(0L);
        judgeInfo.setTime(0L);
        executeCodeResponse.setJudgeInfo(judgeInfo);

        return executeCodeResponse;
    }
}
