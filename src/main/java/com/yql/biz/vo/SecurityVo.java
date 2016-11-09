package com.yql.biz.vo;

import java.util.List;

/**
 * <p>密保问题dto</p>
 * creator simple
 * data 2016/11/8 0008.
 */
public class SecurityVo {

    private String userCode;
    private List<ProblemAnswerVo> answers;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public List<ProblemAnswerVo> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ProblemAnswerVo> answers) {
        this.answers = answers;
    }
}
