package com.example.demo.common.utils;

import com.example.demo.common.ResultCode;
import com.example.demo.common.exception.CustomException;
import com.example.demo.common.token.Audience;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public final class JwtTokenUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    private static Audience audience;

    /**
     * generate token
     */
    public static String createJWT(String username, String userId) {

        checkAudience();

        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    .claim("userId", userId)
                    // 代表这个JWT的主体，即它的所有人
                    .setSubject(username)
                    // 代表这个JWT的签发主体；
                    .setIssuer(audience.getClientId())
                    // 是一个时间戳，代表这个JWT的签发时间；
                    .setIssuedAt(new Date())
                    // 代表这个JWT的接收对象；
                    .setAudience(audience.getName())
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            int TTLMillis = audience.getExpiresSecond();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                // 时间戳：此token的过期时间；
                builder.setExpiration(exp)
                        // 是一个时间戳：此token生效的开始时间
                        .setNotBefore(now);
            }

            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            logger.error("签名失败", e);
            throw new CustomException(ResultCode.PERMISSION_SIGNATURE_ERROR);
        }

    }

    /**
     * parse token
     */
    public static Claims parseJWT(String jsonWebToken) {
        checkAudience();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(audience.getBase64Secret()))
                    .parseClaimsJws(jsonWebToken)
                    .getBody();
            return claims;
        } catch (ExpiredJwtException eje) {
            logger.error("===== Token过期 =====", eje.getMessage());
            throw new CustomException(ResultCode.PERMISSION_TOKEN_EXPIRED);
        } catch (Exception e) {
            logger.error("===== Token解析异常 =====", e.getMessage());
            throw new CustomException(ResultCode.PERMISSION_TOKEN_INVALID);
        }
    }

    public static void checkAudience() {
        audience = audience == null
                ? SpringContextHolder.getBean(Audience.class) : audience;
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        return parseJWT(token).getSubject();
    }

    /**
     * 从token中获取用户ID
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        return parseJWT(token).get("userId", String.class);
    }

    /**
     * 从token中获取用户ID
     *
     * @param authorHeader
     * @return
     */
    public static String getUserIdByAuthorHead(String authorHeader) {
        return parseJWT(authorHeader.substring(7)).get("userId", String.class);
    }

    /**
     * 是否已过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        return parseJWT(token).getExpiration().before(new Date());
    }

    /**
     * 返回token
     */
    public static String getToken(String reqHeaderValue) {
        return StringUtils.isNotBlank(reqHeaderValue) ? reqHeaderValue.substring(7) : reqHeaderValue;
    }

}
