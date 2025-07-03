package icu.mikanku.gobangbackend.user.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.spring.SpringUtil;

import java.util.Map;

public class JWTUtil {

    /** ban constructor */
    private JWTUtil() {}

    private static final byte[] SECRET;

    static {
        String secretStr = SpringUtil.getProperty("jwt.secret");
        SECRET = SecureUtil.md5(secretStr).getBytes();
    }

    /**
     * generate jwt
     */
    public static String generateToken(String username) {
        return cn.hutool.jwt.JWTUtil.createToken(Map.of("username", username), SECRET);
    }

    /**
     * get username from jwt
     * @param token jwt
     * @return username
     */
    public static String getUsername(String token) {
        return cn.hutool.jwt.JWTUtil.parseToken(token).getPayloads().get("username").toString();
    }

    /**
     * verify jwt
     * @param token jwt
     * @return isValid
     */
    public static boolean verify(String token) {
        return cn.hutool.jwt.JWTUtil.verify(token, SECRET);
    }

}
