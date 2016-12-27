package com.yql.biz.model;

import com.yql.core.model.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>支付实名认证类</p>
 * @author  simple
 * @version 1.0.0
 * data 2016/11/7 0007.
 */
@Entity
@Table(name = "pay_problem")
public class PayProblem extends Domain {
    //密保问题名称
    @Column(name = "problem_name")
    private String problemName;

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }
}
