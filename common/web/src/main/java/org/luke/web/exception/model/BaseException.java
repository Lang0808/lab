package org.luke.web.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.luke.common.model.exception.ErrorCode;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private ErrorCode errorCode;

    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
