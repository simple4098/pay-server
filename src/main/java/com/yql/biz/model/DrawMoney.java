package com.yql.biz.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p> 用户提现申请对象 </p>
 * @auther simple
 * data 2016/11/25 0025.
 */
@Entity
@Table(name = "draw_money")
public class DrawMoney extends Domain{
    //用户code
    private String userCode;
    //体现金额
    private BigDecimal totalPrice;
    //
}
