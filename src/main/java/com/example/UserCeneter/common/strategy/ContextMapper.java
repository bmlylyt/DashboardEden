package com.example.UserCeneter.common.strategy;

import org.springframework.stereotype.Service;

@Service
public interface ContextMapper {

    UserStrategy loadProcessor(OperatorStrategyEnum strategyEnum);

}
