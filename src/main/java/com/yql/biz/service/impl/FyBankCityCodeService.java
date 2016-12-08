package com.yql.biz.service.impl;

import com.yql.biz.dao.IFyBankCityCodeDao;
import com.yql.biz.model.FyBankCityCode;
import com.yql.biz.service.IFyBankCityCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> 描述 </p>
 * @auther simple
 * data 2016/12/8 0008.
 */
@Service
public class FyBankCityCodeService implements IFyBankCityCodeService {
    @Resource
    private IFyBankCityCodeDao cityCodeDao;

    @Override
    @Transactional(readOnly = true)
    public List<FyBankCityCode> findAllCityCode() {
        return cityCodeDao.findAll();
    }
}
