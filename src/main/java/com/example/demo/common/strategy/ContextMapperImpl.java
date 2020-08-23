package com.example.demo.common.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContextMapperImpl implements ContextMapper {

    @Autowired
    private RegisterProcessingStrategyImpl userStrategy;

    @Override
    public UserStrategy loadProcessor(OperatorStrategyEnum strategyEnum) {
        if (strategyEnum == OperatorStrategyEnum.SUCCESS
                || strategyEnum == OperatorStrategyEnum.EMAIL_FAIL
        ) {
            return this.userStrategy;
        }
        return null;
    }
}
