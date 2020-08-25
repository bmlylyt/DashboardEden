package com.example.UserCeneter;

import com.example.UserCeneter.common.utils.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenTests {

    @Test
    public void testToken() throws Exception {
        String username = "test_username";
        String userId = "test_id";
        String token = JwtTokenUtil.createJWT(username, userId);
        Assertions.assertFalse(JwtTokenUtil.isExpiration(token));
        Assertions.assertEquals(username, JwtTokenUtil.getUsername(token));
        Assertions.assertEquals(userId, JwtTokenUtil.getUserId(token));
    }
}
