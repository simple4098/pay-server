package com.yql.biz.service.impl;

import com.yql.biz.dao.IFyBankCityCodeDao;
import com.yql.biz.model.FyBankCityCode;
import com.yql.biz.service.IFyBankCityCodeService;
import com.yql.biz.vo.FyBankCityCodeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;

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
    public List<FyBankCityCodeVo> findAllCityCode() {
        List<FyBankCityCodeVo> list = new ArrayList<>();
        List<FyBankCityCode> all = cityCodeDao.findAll();
        //province


        for (FyBankCityCode fyBankCityCode:all){
            FyBankCityCodeVo fyBankCityCodeVo =  FyBankCityCodeVo.toVo(fyBankCityCode);
            list.add(fyBankCityCodeVo);
        }
        return list;
    }
}
