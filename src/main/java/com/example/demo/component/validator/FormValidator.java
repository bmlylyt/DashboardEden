package com.example.demo.component.validator;

import com.example.demo.common.exception.CustomException;

public interface FormValidator<T> {

    boolean canAccept(T arg);

    void validate(T args) throws CustomException;

}
