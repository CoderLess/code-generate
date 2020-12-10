package com.ibn.generate.controller;

import com.google.common.collect.Maps;
import com.ibn.generate.common.ResultInfo;
import com.ibn.generate.service.CommonService;
import com.ibn.generate.service.GenerateService;
import com.ibn.generate.vo.ConsumerParam;
import com.ibn.generate.vo.KeyValueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.controller
 * @author： RenBin
 * @createTime：2020/11/30 21:26
 */
@RestController
@RequestMapping("custom")
public class CustomController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private GenerateService generateService;


    @GetMapping("list")
    public ResultInfo queryParam(Long id) {
        Set<String> paramList = commonService.queryParamName(id);
        return new ResultInfo().success(paramList);
    }

    @PostMapping("config")
    public ResultInfo config(@RequestBody ConsumerParam consumerParam) {
        Map<String, String> map = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(consumerParam.getData())) {
            for (KeyValueVO keyValueVO : consumerParam.getData()) {
                map.put(keyValueVO.getKey(), keyValueVO.getValue());
            }
        }
        commonService.setConfig(consumerParam.getId(), map);
        generateService.generate(consumerParam.getId());
        return new ResultInfo().success("ok");
    }
}
