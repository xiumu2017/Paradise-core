package com.paradise.core.common.exception;

import com.paradise.core.common.api.IErrorCode;

/**
 * 断言处理类，用于抛出各种API异常
 *
 * @author Paradise
 * @date 2020/2/27
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }

    /**
     * 判断校验结果，并抛出指定异常
     *
     * @param flag 参数校验结果
     * @param msg  返回前台的message
     */
    public static void pvIsTrue(boolean flag, String msg) {
        if (!flag) {
            throw new ParamValidateFailException(msg);
        }
    }
}
