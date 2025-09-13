package org.luke.common.model.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    UN_SUPPORTED("UN_SUPPORTED"),
    INVALID_PARAMS("INVALID_PARAMS");

    private final String errorCode;
}
