package com.sys.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sys.enums.CommErrEnum;
import com.sys.exception.BizException;

import com.sys.util.HMACSHA1Encoder;

import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.awt.*;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

public class AuthFilter extends ZuulFilter {
    private final static Long FIFTEEN_MIN = 15*60*1000L;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    public static Map<String,String> keySet;
    static {
        keySet = new HashMap<>();
        keySet.put("accessId","key");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = getCurrentContext();
        //x-www-form-urlencoded
        HttpServletRequest request = context.getRequest();

        //支持文件上传参数获取form-data
        HttpServletRequestWrapper httpServletRequestWrapper = (HttpServletRequestWrapper) request;
        String sign = httpServletRequestWrapper.getRequest().getParameter("sign");
        ServletRequest requestWarp = httpServletRequestWrapper.getRequest();

        if(!isContainNeedPamam(requestWarp)){
           throw new BizException(CommErrEnum.BODY_NOT_MATCH);
        }
        String timestamp = requestWarp.getParameter("timestamp");
        final Long currentTime = System.currentTimeMillis();
        if(currentTime - Long.valueOf(timestamp)  > FIFTEEN_MIN){
//            throw new BizException(CommErrEnum.REQUEST_TIME_OVER_FINFTEEN_MIN);
        }
        if(!checkSign(request,requestWarp)){
            throw new BizException(CommErrEnum.SIGNATURE_NOT_RIGHT);
        }
        String token = context.getRequest().getHeader("token");
        return null;
    }

    /**
     * 校验必传参数
     * @param httpRequest
     * @return
     */
    private Boolean isContainNeedPamam(ServletRequest httpRequest){
        if(StringUtils.isEmpty(httpRequest.getParameter("timestamp")) ||
                StringUtils.isEmpty(httpRequest.getParameter("sign")) ||
                StringUtils.isEmpty(httpRequest.getParameter("accessid"))){
            return false;
        }
        return true;
    }

    /**
     * 校验签名
     * @return
     */
    private Boolean checkSign(HttpServletRequest httpRequest,ServletRequest requestWrap){
        String signStr = httpRequest.getMethod() + "\n" + requestWrap.getParameter("timestamp") + "\n" + httpRequest.getRequestURI();
        String secret = keySet.get( requestWrap.getParameter("accessid"));
        if(StringUtils.isEmpty(secret)){
            throw new BizException(CommErrEnum.SIGNATURE_NOT_MATCH);
        }
        try {
            String genSign = HMACSHA1Encoder.calculateRFC2104HMAC(secret,signStr);
            if(requestWrap.getParameter("sign").equals(genSign)){
               return true;
            }
        } catch (SignatureException e) {
            throw new RuntimeException("签名失败!"+e.getMessage());
        }

        return false;
    }
}
