package com.yql.biz.dto;

import java.util.List;

/**
 * <p>密保问题dto</p>
 * creator simple
 * data 2016/11/8 0008.
 */
public class SecurityProblemDto {

    private String userCode;
    private List<ProblemAnswerDto> answers;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public List<ProblemAnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ProblemAnswerDto> answers) {
        this.answers = answers;
    }
}
