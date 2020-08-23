package com.example.demo.component.validator;

import com.example.demo.common.exception.CustomException;

public interface ReqValidateManager<T> {

    void doExecute(T arg) throws CustomException;

}
