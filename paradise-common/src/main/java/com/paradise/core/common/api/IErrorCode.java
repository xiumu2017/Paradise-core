package com.paradise.core.common.api;

/**
 * 封装API的错误码
 *
 * @author Paradise
 * @date 2019/4/19
 */
public interface IErrorCode {
    /**
     * 获取错误码
     *
     * @return 错误码
     */
    long getCode();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
