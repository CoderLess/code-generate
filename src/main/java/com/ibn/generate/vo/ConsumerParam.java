package com.ibn.generate.vo;

import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.vo
 * @author： RenBin
 * @createTime：2020/11/30 23:02
 */
@Data
public class ConsumerParam {
    private Long id;
    private List<KeyValueVO> data;
}
