package com.example.UserCeneter.component.validator;

import com.example.UserCeneter.common.exception.CustomException;

public interface FormValidator<T> {

    boolean canAccept(T arg);

    void validate(T args) throws CustomException;

}
