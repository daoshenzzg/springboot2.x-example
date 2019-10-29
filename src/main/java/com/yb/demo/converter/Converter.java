package com.yb.demo.converter;

import com.yb.demo.common.exception.ConverterException;

import java.util.List;

/**
 * 对象转换器
 *
 * @author daoshenzzg@163.com
 * @date 2019-09-09 10:20
 */
public interface Converter<DO, VO> {

    /**
     * 对象转换
     *
     * @param from
     * @param clazz
     * @return
     */
    VO convert(DO from, Class<VO> clazz) throws ConverterException;

    /**
     * 对象批量转换
     *
     * @param fromList
     * @param clazz
     * @return
     */
    List<VO> convert(List<DO> fromList, Class<VO> clazz) throws ConverterException;
}
