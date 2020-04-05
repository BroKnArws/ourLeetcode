package com.neu.exception.general;

import com.neu.exception.BaseException;

public class ResourceNotExistedException extends BaseException {
    public ResourceNotExistedException(String message) {
        this.setCode(103);
        this.setMessage(message);
    }
}
