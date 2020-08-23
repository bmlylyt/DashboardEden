package com.example.demo.component.validator;

import com.example.demo.RPCDomain.req.RegisterRequest;
import com.example.demo.common.ResultCode;
import com.example.demo.common.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class RegisterFormValidator implements FormValidator<RegisterRequest> {
    @Override
    public boolean canAccept(RegisterRequest arg) {
        if (arg instanceof RegisterRequest) {
            return true;
        }
        return false;
    }

    @Override
    public void validate(RegisterRequest args) throws CustomException {
        if (StringUtils.isBlank(args.getUsername())
            || StringUtils.isBlank(args.getPassword())
            || StringUtils.isBlank(args.getEmail())
            || StringUtils.isNoneBlank(args.getCaptcha())) {
            throw new CustomException(ResultCode.INVALID_REGISTER_PARAMETER);
        }
    }
}
