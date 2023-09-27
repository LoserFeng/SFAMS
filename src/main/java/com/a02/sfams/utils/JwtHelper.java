package com.a02.sfams.utils;


import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;



public class JwtHelper {


    private static long tokenExpiration = 5*24*60*60*1000;   //5天时间
    private static String tokenSignKey = "123456";


    /**
     * 根据userNumber和id生成Token
     * @param userId
     * @param userNumber
     * @return
     */
    public static String createToken(String userId,String userNumber) {
        String token = Jwts.builder()
                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("userNumber", userNumber)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }
    public static String getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String userId = (String) claims.get("userId");
        return userId;
    }
    public static String getUserNumber(String token) {
        if(StringUtils.isEmpty(token)) return "";
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userNumber");
    }
    public static void main(String[] args) {
        String token = JwtHelper.createToken("1673952067598757890", "321232132");
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUserNumber(token));
    }
}
