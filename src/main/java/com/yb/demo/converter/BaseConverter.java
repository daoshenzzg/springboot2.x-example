package com.yb.demo.converter;

import com.yb.demo.common.exception.ConverterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认基础对象转换器
 *
 * @author daoshenzzg@163.com
 * @date 2019-09-09 10:26
 */
@Slf4j
public abstract class BaseConverter<DO, VO> implements Converter<DO, VO> {

    @Override
    public VO convert(DO from, Class<VO> clazz) throws ConverterException {
        if (from == null) {
            return null;
        }
        try {
            VO to = clazz.newInstance();
            BeanUtils.copyProperties(from, to);
            return to;
        } catch (Exception ex) {
            throw new ConverterException(ex);
        }
    }

    @Override
    public List<VO> convert(List<DO> fromList, Class<VO> clazz) throws ConverterException {
        if (CollectionUtils.isEmpty(fromList)) {
            return null;
        }
        List<VO> toList = new ArrayList<>(fromList.size());
        for (DO from : fromList) {
            toList.add(convert(from, clazz));
        }
        return toList;
    }
}
