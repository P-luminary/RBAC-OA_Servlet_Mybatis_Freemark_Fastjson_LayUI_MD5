package com.imooc.oa.util;

import junit.framework.TestCase;
import org.junit.Test;

public class MD5UtilsTest extends TestCase {
    @Test
    public void md5Digest(){
        System.out.println(MD5Utils.md5Digest("test"));
        System.out.println(MD5Utils.md5Digest("test"));
        System.out.println(MD5Utils.md5Digest("test"));
    }
}