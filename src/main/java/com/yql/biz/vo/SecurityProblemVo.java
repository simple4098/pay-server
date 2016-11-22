package com.yql.biz.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yql.biz.model.SecurityProblem;

/**
 * <p>密保问题vo对象</p>
 * creator simple
 * data 2016/11/9 0009.
 */
public class SecurityProblemVo {
    private Integer payAccountId;
    //密保问题
    private Integer problemId;
    //密保问题答案
    @JsonIgnore
    private String answer;
    //密保问题
    private String problemName;

    public Integer getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Integer payAccountId) {
        this.payAccountId = payAccountId;
    }

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

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    /*public static SecurityProblemVo domainToVo(SecurityProblem securityProblem){
        String jsonString = JSONObject.toJSONString(securityProblem);
        return JSON.parseObject(jsonString,SecurityProblemVo.class);
    }*/
}
