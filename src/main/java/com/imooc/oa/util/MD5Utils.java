package com.imooc.oa.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    /**
     * 对数据源加盐混淆后生成MD5摘要
     * @param source 源数据
     * @return MD5摘要
     */
    public static String md5Digest(String source){
        return DigestUtils.md5Hex(source);
    }
    public static String md5Digest(String source, Integer salt){
        char[] ca = source.toCharArray(); //字符数组
        for (int i = 0; i < ca.length; i++) {
            ca[i] = (char)(ca[i] + salt);
        }
        String target = new String(ca);
//        System.out.println(target);
        String md5 = DigestUtils.md5Hex(target);
        return md5;
    }

    public static void main(String[] args) {
        System.out.println(MD5Utils.md5Digest("test", 188));
    }
}
