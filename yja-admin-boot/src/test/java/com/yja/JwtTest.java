package com.yja;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yja.utils.Md5Util;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGen(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username","张三");

        // 生成jwt的代码
        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 过期时间
                .sign(Algorithm.HMAC256("YJA"));// 指定算法，配置秘钥
        System.out.println(token);
    }

    @Test
    public void parseToken(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MTM4Mjk0NTh9.BJaaZfZRWjp_HdCLpF0Y67ppihz6kUjm4BnM_FyLPGc";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("YJA")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        Claim user = claims.get("user");
        System.out.println(user.asMap());

    }
    @Test
    public void test(){
        System.out.println(Md5Util.getMD5String("123456"));
    }
}
