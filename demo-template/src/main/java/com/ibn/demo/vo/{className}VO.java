package com.ibn.demo.vo;

import lombok.Data;

/**
 * @version 1.0
 * @description:
 * @projectName：demo
 * @see: com.ibn.demo.vo
 * @author： ${custom.author}
 * @createTime：${custom.curTime}
 */
@Data
public class ${className}VO {

    private static final long serialVersionUID = 1L;

    #foreach($fileld in ${columns})
        /**
         * @description: $fileld.columnComment
         * @author：${custom.author}
         * @createTime：${custom.curTime}
         */
        private $fileld.type $fileld.paramName;
    #end
}
