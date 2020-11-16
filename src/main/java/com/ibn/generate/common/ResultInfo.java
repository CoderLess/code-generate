package com.ibn.generate.common;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ResultInfo {
    private Integer status;
    private Object data;

    public ResultInfo() {
    }

    /**
     * 成功返回数据
     *
     * @param data
     * @return
     */
    public ResultInfo success(Object data) {
        this.status = HttpStatus.OK.value();
        this.data = data;
        return this;
    }

    /**
     * 返回数据失败
     * @param data
     * @return
     */
    public ResultInfo fail(Object data) {
        this.status=HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.data = data;
        return this;
    }

}
