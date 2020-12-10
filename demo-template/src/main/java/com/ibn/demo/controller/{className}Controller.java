package com.ibn.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.ibn.demo.common.ResultInfo;
import com.ibn.demo.domain.${className}DTO;
import com.ibn.demo.service.${className}Service;
import com.ibn.demo.util.BeanUtils;
import com.ibn.demo.vo.${className}VO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：demo
 * @see: com.ibn.demo.controller
 * @author： ${custom.author}
 * @createTime：${custom.curTime}
 */
@RestController
@RequestMapping("${paramName}")
public class ${className}Controller {

    private static final Logger logger = LoggerFactory.getLogger(${className}Controller.class);

    @Autowired
    private ${className}Service ${paramName}Service;

    @PostMapping("save")
    public ResultInfo save(@RequestBody ${className}VO ${paramName}VO) {
        if (null == ${paramName}VO) {
            return new ResultInfo().error("参数不能为空");
        }
        ${className}DTO ${paramName}DTO = new ${className}DTO();
        BeanUtils.copyProperties(${paramName}VO, ${paramName}DTO);
        Long id = ${paramName}Service.save(${paramName}DTO);
        return new ResultInfo().success(id);
    }

    @PostMapping("saveBatch")
    public ResultInfo save(@RequestBody List<${className}VO> ${paramName}VOList) {
        if (CollectionUtils.isEmpty(${paramName}VOList)) {
            return new ResultInfo().error("参数不能为空");
        }
        List<${className}DTO> ${paramName}DTOList = null;
        try {
            ${paramName}DTOList = BeanUtils.convertList(${paramName}VOList, ${className}DTO.class);
        } catch (Exception e) {
            String msg = String.format("${className}Controller.save中list转换失败：%s", JSONObject.toJSONString(${paramName}DTOList));
            logger.error(msg, e);
        }
        ${paramName}Service.saveBatch(${paramName}DTOList);
        return new ResultInfo().success();
    }

    @PostMapping("updateById")
    public ResultInfo updateById(@RequestBody ${className}VO ${paramName}VO) {
        if (null == ${paramName}VO) {
            return new ResultInfo().error("参数不能为空");
        }
        ${className}DTO ${paramName}DTO = new ${className}DTO();
        BeanUtils.copyProperties(${paramName}VO, ${paramName}DTO);
        ${paramName}Service.updateById(${paramName}DTO);
        return new ResultInfo().success();
    }

    @PostMapping("deleteById")
    public ResultInfo deleteById(Long id) {
        if (null == id) {
            return new ResultInfo().error("参数不能为空");
        }
        ${paramName}Service.deleteById(id);
        return new ResultInfo().success();
    }

    @GetMapping("queryById")
    public ResultInfo queryById(Long id) {
        if (null == id) {
            return new ResultInfo().error("参数不能为空");
        }
        ${className}DTO ${paramName}DTO = ${paramName}Service.queryById(id);
        return new ResultInfo().success(${paramName}DTO);
    }

    @GetMapping("queryList")
    public ResultInfo queryList(@RequestBody ${className}VO ${paramName}VO) {
        if (null == ${paramName}VO) {
            return new ResultInfo().error("参数不能为空");
        }
        ${className}DTO ${paramName}DTO = new ${className}DTO();
        BeanUtils.copyProperties(${paramName}VO, ${paramName}DTO);
        List<${className}DTO> ${paramName}DTOList = ${paramName}Service.queryList(${paramName}DTO);
        return new ResultInfo().success(${paramName}DTOList);
    }

}
