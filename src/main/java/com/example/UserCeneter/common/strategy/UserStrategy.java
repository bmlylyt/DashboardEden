package com.example.UserCeneter.common.strategy;

import com.example.UserCeneter.RPCDomain.req.RegisterRequest;
import com.example.UserCeneter.RPCDomain.response.ResponseResult;

public interface UserStrategy {

    ResponseResult doProcessor(RegisterRequest registerRequest, OperatorStrategyEnum operatorStrategyEnum);

}
