package com.ibn.generate.exception;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.exception
 * @author： RenBin
 * @createTime：2020/11/17 08:35
 */
public class ParamErrorException extends Exception {
    private static final long serialVersionUID = 2364694796725158554L;

    public ParamErrorException(String msg, Exception e) {
        super(msg, e);
    }

    public ParamErrorException(String msg) {
        super(msg);
    }
}
