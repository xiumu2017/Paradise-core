package com.paradise.core.common.api;

/**
 * 枚举了一些常用API操作码
 *
 * @author Paradise
 * @date 2019/4/19
 */
public enum ResultCode implements IErrorCode {
    /**
     * success
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(400, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    ACCEPT_FAIL_OVER_LIMIT(5000, "接单失败，超出每日限额"),
    RUSH_FAIL_OVER_LIMIT(5002, "抢单失败，超出每日限额"),
    RUSH_FAIL_BEAN_RUSHED(5001, "下手慢了，抢单失败");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
