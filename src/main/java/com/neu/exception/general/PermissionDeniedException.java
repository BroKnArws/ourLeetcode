package com.neu.exception.general;

import com.neu.exception.BaseException;

public class PermissionDeniedException extends BaseException {
    public PermissionDeniedException() {
        this.setCode(102);
        this.setMessage("没有权限");
    }
}
