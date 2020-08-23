package com.example.demo.common.strategy;

import com.example.demo.RPCDomain.req.RegisterRequest;
import com.example.demo.RPCDomain.response.ResponseResult;
import com.example.demo.common.ResultCode;
import com.example.demo.common.utils.MD5Utils;
import com.example.demo.common.utils.UUIDUtils;
import com.example.demo.dao.RegisterRecordDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.RegisterRecord;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterProcessingStrategyImpl implements UserStrategy {

    @Autowired
    private RegisterRecordDao registerRecordDao;

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseResult doProcessor(RegisterRequest registerRequest, OperatorStrategyEnum operatorStrategyEnum) {
        if (operatorStrategyEnum == OperatorStrategyEnum.SUCCESS) {
            RegisterRecord registerRecord = new RegisterRecord();
            registerRecord.setId(UUIDUtils.get());
            registerRecord.setUsername(registerRequest.getUsername());
            registerRecord.setEmail(registerRequest.getEmail());
            registerRecord.setCaptcha(registerRequest.getCaptcha());
            registerRecord.setSentTime(new Date(System.currentTimeMillis()));
            registerRecordDao.save(registerRecord);

            // save user info
            User user = new User();
            user.setUsername(registerRecord.getUsername());
            user.setPasssword(MD5Utils.getMD5(registerRequest.getPassword()));
            user.setId(UUIDUtils.get());
            userDao.save(user);
            return new ResponseResult(ResultCode.REGISTER_CAPTCHA_SEND);
        } else {
            return new ResponseResult(ResultCode.MAIL_SEND_FAIL);
        }
    }
}
