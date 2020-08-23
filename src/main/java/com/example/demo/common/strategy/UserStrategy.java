package com.example.demo.common.strategy;

import com.example.demo.RPCDomain.req.RegisterRequest;
import com.example.demo.RPCDomain.response.ResponseResult;

public interface UserStrategy {

    ResponseResult doProcessor(RegisterRequest registerRequest, OperatorStrategyEnum operatorStrategyEnum);

}
