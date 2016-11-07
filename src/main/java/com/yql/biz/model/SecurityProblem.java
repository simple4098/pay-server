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
public class SecurityProblem extends Domain implements Serializable {
    //支付账号id
    @Column(name = "pay_account_id")
    private Long payAccountId;
    //密保问题
    @Column(name = "problem")
    private String problem;
    //密保问题答案
    @Column(name = "answer")
    private String answer;

    public Long getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Long payAccountId) {
        this.payAccountId = payAccountId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}