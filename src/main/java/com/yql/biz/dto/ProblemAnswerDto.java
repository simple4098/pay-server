package com.yql.biz.dto;

/**
 * <p>密保问题答案Dto</p>
 * creator simple
 * data 2016/11/8 0008.
 */
public class ProblemAnswerDto {

    private Integer problemId;
    private String answer;

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
