package com.ibn.demo.service;

import com.ibn.demo.domain.${className}DTO;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：demo
 * @see: com.ibn.demo.service
 * @author： ${custom.author}
 * @createTime：${custom.curTime}
 */
public interface ${className}Service {

    /**
     * @description: 保存用户信息
     * @author：${custom.author}
     * @createTime：${custom.curTime}
     */
    Long save(${className}DTO ${paramName}DTO);

    /**
     * @description: 批量保存用户信息
     * @author：${custom.author}
     * @createTime：${custom.curTime}
     */
    Long saveBatch(List<${className}DTO> ${paramName}DTOList);

    /**
     * @description: 更新用户信息
     * @author：${custom.author}
     * @createTime：${custom.curTime}
     */
    Integer updateById(${className}DTO ${paramName}DTO);

    /**
     * @description: 根据Id删除用户信息
     * @author：${custom.author}
     * @createTime：${custom.curTime}
     */
    Integer deleteById(Long id);

    /**
     * @description: 通过id查询用户信息
     * @author：${custom.author}
     * @createTime：${custom.curTime}
     */
    ${className}DTO queryById(Long id);

    /**
     * @description: 通过条件查询用户信息
     * @author：${custom.author}
     * @createTime：${custom.curTime}
     */
    List<${className}DTO> queryList(${className}DTO ${paramName}DTO);
}
