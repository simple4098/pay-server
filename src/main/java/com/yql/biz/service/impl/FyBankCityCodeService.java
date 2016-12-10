package com.yql.biz.service.impl;

import com.yql.biz.dao.IFyBankCityCodeDao;
import com.yql.biz.model.FyBankCityCode;
import com.yql.biz.service.IFyBankCityCodeService;
import com.yql.biz.vo.FyBankCityCodeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * <p> 描述 </p>
 *
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
        Map<String, List<FyBankCityCode>> provinceList = all.stream().collect(groupingBy(FyBankCityCode::getProvinceId));
        Optional.ofNullable(provinceList).ifPresent(stringListMap -> stringListMap.forEach((provinceId, fyBankCityCodes) -> {
            FyBankCityCodeVo fyBankCityCodeVo = new FyBankCityCodeVo();
            fyBankCityCodeVo.setProvinceId(provinceId);
            fyBankCityCodeVo.setFyBankCityCodeList(fyBankCityCodes);
            Optional.ofNullable(fyBankCityCodes).ifPresent(fyBankCityCodes1 -> fyBankCityCodes1.forEach(fyBankCityCode -> fyBankCityCodeVo.setProvinceName(fyBankCityCode.getProvinceName())));
            list.add(fyBankCityCodeVo);
        }));
       /* for (FyBankCityCode fyBankCityCode:all){
            FyBankCityCodeVo fyBankCityCodeVo =  FyBankCityCodeVo.toVo(fyBankCityCode);
            list.add(fyBankCityCodeVo);
        }*/
        return list;
    }
}
