package com.whuthm.gc;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.whuthm.gc.Constants.REGEX_EMAIL;
import static com.whuthm.gc.Constants.REGEX_PASSWORD;
import static com.whuthm.gc.Constants.REGEX_USERNAME;

public class Utils {

    public static String getUserIdFromToken(String token) {
        return token.split("-")[0];
    }

    public static String getUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean matchEmail(String email){
        return match(email, REGEX_EMAIL);
    }

    public static boolean matchUsername(String username){
        return match(username, REGEX_USERNAME);
    }

    public static boolean matchPassword(String password){
        return match(password, REGEX_PASSWORD);
    }

    public static boolean match(String content, String regex) {
        //正则表达式的模式
        Pattern p = Pattern.compile(REGEX_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(content);
        //进行正则匹配
        return m.matches();
    }

    public static String encryptPassword(String password, String salt) throws NoSuchAlgorithmException {
        if (!StringUtils.isEmpty(salt)) {
            return new String(MessageDigest.getInstance("MD5").digest((salt + password).getBytes()));
        } else {
            return new String(MessageDigest.getInstance("MD5").digest(password.getBytes()));
        }
    }

}
