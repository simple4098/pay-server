package com.yql.biz.service.impl;

import com.yql.biz.dao.IPayProblemDao;
import com.yql.biz.dto.PayProblemDto;
import com.yql.biz.model.PayProblem;
import com.yql.biz.service.IPayProblemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * creator simple
 * Created by Administrator
 * data 2016/11/8 0008.
 */
@Service
public class PayProblemService implements IPayProblemService {
    @Resource
    private IPayProblemDao payProblemDao;

    @Override
    public List<PayProblemDto> findPayProblemList() {
        List<PayProblemDto> list = new ArrayList<>();
        List<PayProblem> all = payProblemDao.findAll();
        for (PayProblem payProblem:all){
            PayProblemDto payProblemDto = new PayProblemDto();
            BeanUtils.copyProperties(payProblem,payProblemDto);
            list.add(payProblemDto);
        }

        return list;
    }
}
