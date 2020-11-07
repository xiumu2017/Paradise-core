package com.paradise.core.common.exception;

import com.paradise.core.common.api.IErrorCode;
import com.paradise.core.common.api.ResultCode;

/**
 * App 业务异常
 *
 * @author Paradise
 */
public class AppServiceException extends RuntimeException {
    private IErrorCode errorCode;

    public AppServiceException(String message) {
        super(message);
        this.errorCode = null;
    }

    public AppServiceException() {
        this.errorCode = ResultCode.FAILED;
    }

    public AppServiceException(IErrorCode iErrorCode) {
        super(iErrorCode.getMessage());
        this.errorCode = iErrorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
