package cn.lzj66.util;

import java.util.Base64;
//import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    /**
     * 加密数据
     *
     * @param string
     * @return string 加密后的字符串
     */
    public static String encodeMD5(String string) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("生成md5错误1");
            e.printStackTrace();
        }

        String newStr = Base64.getEncoder().encodeToString(string.getBytes());
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        try {
//            newStr = base64Encoder.encode(messageDigest.digest(string.getBytes("utf-8")));
//        } catch (UnsupportedEncodingException e) {
//            System.out.println("生成md5错误1");
//            e.printStackTrace();
//        }
        return newStr;
    }

    /**
     * 验证数据
     *
     * @param oldPd
     * @param newPd
     * @return
     */
    public static boolean comparePassword(String oldPd, String newPd) {
        if (encodeMD5(newPd).equals(oldPd)) {
            return true;
        } else {
            return false;
        }
    }
}
