package com.ibn.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.ibn.demo.dao.${className}Dao;
import com.ibn.demo.domain.${className}DTO;
import com.ibn.demo.entity.${className}DO;
import com.ibn.demo.service.${className}Service;
import com.ibn.demo.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：demo
 * @see: com.ibn.demo.service.impl
 * @author： ${custom.author}
 * @createTime：${custom.curTime}
 */
@Service("${paramName}Serive")
public class ${className}ServiceImpl implements ${className}Service {

    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    @Autowired
    private ${className}Dao ${paramName}Dao;

    @Override
    public Long save(${className}DTO ${paramName}DTO) {
        if (null == ${paramName}DTO) {
            return null;
        }
        ${className}DO ${paramName}DO = new ${className}DO();
        BeanUtils.copyProperties(${paramName}DTO, ${paramName}DO);
        ${paramName}Dao.save(${paramName}DO);
        return ${paramName}DO.getId();
    }

    @Override
    public Long saveBatch(List<${className}DTO> ${paramName}DTOList) {
        if (CollectionUtils.isEmpty(${paramName}DTOList)) {
            return null;
        }
        List<${className}DO> ${paramName}DOList;
        try {
            ${paramName}DOList = BeanUtils.convertList(${paramName}DTOList, ${className}DO.class);
        } catch (Exception e) {
            String msg = String.format("${className}ServiceImp.saveBatch方法llist转换失败：%s",
                    JSONObject.toJSONString(${paramName}DTOList));
            logger.error(msg, e);
            return null;
        }
        return ${paramName}Dao.saveBatch(${paramName}DOList);
    }

    @Override
    public Integer updateById(${className}DTO ${paramName}DTO) {
        if (null == ${paramName}DTO) {
            return null;
        }
        ${className}DO ${paramName}DO = new ${className}DO();
        BeanUtils.copyProperties(${paramName}DTO, ${paramName}DO);
        return ${paramName}Dao.updateById(${paramName}DO);
    }

    @Override
    public Integer deleteById(Long id) {
        if (null == id) {
            return null;
        }
        return ${paramName}Dao.deleteById(id);
    }

    @Override
    public ${className}DTO queryById(Long id) {
        if (null == id) {
            return null;
        }
        ${className}DO ${paramName}DO = ${paramName}Dao.queryById(id);
        if (null == ${paramName}DO) {
            return null;
        }
        ${className}DTO ${paramName}DTO = new ${className}DTO();
        BeanUtils.copyProperties(${paramName}DO, ${paramName}DTO);
        return ${paramName}DTO;
    }

    @Override
    public List<${className}DTO> queryList(${className}DTO ${paramName}DTO) {
        if (null == ${paramName}DTO) {
            return null;
        }
        ${className}DO ${paramName}DO = new ${className}DO();
        BeanUtils.copyProperties(${paramName}DTO, ${paramName}DO);
        List<${className}DO> ${paramName}DOList = ${paramName}Dao.queryList(${paramName}DO);
        if (CollectionUtils.isEmpty(${paramName}DOList)) {
            return Lists.newArrayList();
        }
        List<${className}DTO> ${paramName}DTOList;
        try {
            ${paramName}DTOList=BeanUtils.convertList(${paramName}DOList, ${className}DTO.class);
        } catch (Exception e) {
            String msg = String.format("${className}ServiceImpl.queryList方法list转换失败：%s",
                    JSONObject.toJSONString(${paramName}DOList));
            logger.error(msg, e);
            return Lists.newArrayList();
        }
        return ${paramName}DTOList;
    }
}
