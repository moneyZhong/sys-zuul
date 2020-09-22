package com.sys;


import com.sys.util.HMACSHA1Encoder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.security.SignatureException;


class SysZuulApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void genAccessKeyAndSecret(){
        System.out.println(RandomStringUtils.randomAlphabetic(10));
        System.out.println(RandomStringUtils.randomAlphabetic(32));
    }

    public void genParam(){
        String method = "POST";
        long l = System.currentTimeMillis();
        String url = "/v1/sys-bus/oilOrg/save";
        System.out.println("timestamp---》"+l);
        String signStr = method+ "\n" + l + "\n" + url;
        try{
            String genSign = HMACSHA1Encoder.calculateRFC2104HMAC("MBFGHJkfXCZQlImsVgjlPMCUhxOzEzJb",signStr);
            System.out.println(genSign);
        } catch (SignatureException e) {
            throw new RuntimeException("签名失败!"+e.getMessage());
        }


    }

    public static void main(String[] args) {
        SysZuulApplicationTests sysZuulApplicationTests = new SysZuulApplicationTests();
        sysZuulApplicationTests.genAccessKeyAndSecret();
        sysZuulApplicationTests.genParam();
    }


}
