package com.neu.exception.general;

import com.neu.exception.BaseException;

public class NoResultException extends BaseException {

    public NoResultException(){
        this.setCode(20003);
        this.setMessage("无结果");
    }
}
