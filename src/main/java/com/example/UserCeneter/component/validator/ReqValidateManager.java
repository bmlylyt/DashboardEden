package com.example.UserCeneter.component.validator;

import com.example.UserCeneter.common.exception.CustomException;

public interface ReqValidateManager<T> {

    void doExecute(T arg) throws CustomException;

}
