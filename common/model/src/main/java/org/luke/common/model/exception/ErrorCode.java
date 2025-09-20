package org.luke.common.model.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SUCCESS("SUCCESS"),
    UN_SUPPORTED("UN_SUPPORTED"),
    INVALID_PARAMS("INVALID_PARAMS"),
    RATE_LIMITED("RATE_LIMITED"),
    DATA_NOT_EXIST("DATA_NOT_EXIST");

    private final String errorCode;
}
