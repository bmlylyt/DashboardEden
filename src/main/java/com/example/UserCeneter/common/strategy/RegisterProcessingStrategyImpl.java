package com.example.UserCeneter.common.strategy;

import com.example.UserCeneter.RPCDomain.req.RegisterRequest;
import com.example.UserCeneter.RPCDomain.response.ResponseResult;
import com.example.UserCeneter.common.ResultCode;
import com.example.UserCeneter.common.utils.MD5Utils;
import com.example.UserCeneter.common.utils.UUIDUtils;
import com.example.UserCeneter.dao.RegisterRecordDao;
import com.example.UserCeneter.dao.UserDao;
import com.example.UserCeneter.model.RegisterRecord;
import com.example.UserCeneter.model.User;
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
