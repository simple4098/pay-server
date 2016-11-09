package com.yql.biz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * creator simple
 * Created by Administrator
 * data 2016/11/7 0007.
 */
@Entity
@Table(name = "security_problem")
public class SecurityProblem extends Domain {
    //支付账号id
    @Column(name = "pay_account_id")
    private Integer payAccountId;
    //密保问题
    @Column(name = "problem_id")
    private Integer problemId;
    //密保问题答案
    @Column(name = "answer")
    private String answer;

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
}
