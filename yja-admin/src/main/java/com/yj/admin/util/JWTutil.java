package com.yj.admin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.yj.core.security.model.JwtClaimConstants;
import com.yj.core.security.model.SysUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JWTutil {
    private  static  final String secret = "111111";
    // 毫秒单位
    private static final Long expiration = 36000000L;


    public static String token(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return JWT.create()
                .withExpiresAt(expiryDate)
                .withAudience(JSON.toJSONString(authentication)) //设置接受方信息 一般指登录用户
                .sign(algorithm);
    }

    public static void tokenVerify(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        jwtVerifier.verify(token);//没报错说明验证成功
        JWT.decode(token).getExpiresAt();
        String json = JWT.decode(token).getAudience().get(0);
        //JwtAuthentication jwtAuthentication = JSON.parseObject(json, JwtAuthentication.class);
        Authentication  jwtAuthentication = getAuthentication(json);
        SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
    }
    public Integer getUsernameFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return Integer.valueOf(jwt.getSubject());
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public static Date getExpires(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        jwtVerifier.verify(token);//没报错说明验证成功
        Date expiresAt = JWT.decode(token).getExpiresAt();
        return expiresAt;
    }

    /*
     * 刷新token
     * */

    public String refreshToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            String username = jwt.getSubject();
            Algorithm algorithm = Algorithm.HMAC256(secret);

            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expiration);

            return JWT.create()
                    .withSubject(username)
                    .withIssuedAt(now)
                    .withExpiresAt(expiryDate)
                    .sign(algorithm);
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 从 Token 中解析数据
     *
     * @param token JWT Token
     * @return 解析数据
     */

    /**
     * 从 Token 中解析数据
     *
     * @param token JWT Token
     * @return 解析数据
     */
    public static String parseToken(String token) {
        try {
            if (StrUtil.isBlank(token)) {
                return null;
            }

            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            return token;
        } catch (Exception ignored) {
        }
        return null;
    }


    /**
     *  从 Token 中获取 Authentication
     *
     * @param json
     * @return
     */
    public static UsernamePasswordAuthenticationToken getAuthentication(String json) {
        JSONObject jwsJson = JSONObject.parseObject(json);
        JSONObject principal = jwsJson.getJSONObject("principal");
        SysUserDetails userDetails = new SysUserDetails();
        userDetails.setUserId(principal.getString(JwtClaimConstants.USER_ID)); // 用户ID
        userDetails.setDeptId(principal.getString(JwtClaimConstants.DEPT_ID)); // 部门ID
        userDetails.setDataScope(Integer.valueOf(principal.getString(JwtClaimConstants.DATA_SCOPE).toString())); // 数据权限范围

        userDetails.setUsername(principal.getString("username")); // 用户名
        // 角色集合
        //Set<SimpleGrantedAuthority> authorities = ((JSONArray)principal.get(JwtClaimConstants.AUTHORITIES))
        //        .stream()
        //        .map(authority -> new SimpleGrantedAuthority(authority != null ? authority.toString() : null))
        //        .collect(Collectors.toSet());

        JSONArray jsonArray = JSONArray.parseArray(principal.getString(JwtClaimConstants.AUTHORITIES));
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Object o : jsonArray) {
            JSONObject roleObj = (JSONObject) o;
            String role = ((JSONObject) o).getString("authority");
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }
}
