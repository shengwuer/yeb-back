package com.xxxx.server.config.security.component;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 *
 *
 *@author
 *@since
 * */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "create";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /*(1).
     *根据用户信息生成token
     * 荷载生成JWT TOKEN
     *       claims
     *
     * */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /*(4).
     * 从token中获取登录用户名
     *           token
     * */

    public String getUserNameFromToken(String token) {

        String username;
        try {
            Claims claims = getClaimsFormToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    /**
     * @return :
     * @author :
     * @description:从token中获取荷载
     */
    private Claims getClaimsFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**(5).
     * @return :
     *
     * @author :
     * @description: 验证token是否有效
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

    /**(6).
     * @return :
     * @author :
     * @description:判断token是否失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFoemToken(token);
        return expiredDate.before(new Date());
    }

    /**(8).
     * @return :
     * @author :
     * @description:判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**(9).
     * @return :
     * @author :
     * @description:刷新token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**(7)
     * @return :
     * @author :
     * @description:从token中获取过期时间
     */
    private Date getExpiredDateFoemToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }


    /*(2).
     * 根据荷载生成JWT TOKEN
     *       claims
     * */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**(3).
     * @return :
     * @author :
     * @description:生成token失效时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

}