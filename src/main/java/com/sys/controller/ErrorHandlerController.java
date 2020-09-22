package com.sys.controller;

import com.netflix.zuul.context.RequestContext;
import com.sys.vo.ResultBody;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理服务异常
 */
@RestController
public class ErrorHandlerController implements ErrorController {
 
    /**
     * 出异常后进入该方法，交由下面的方法处理
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }
 
    @RequestMapping("/error")
    public Object error(HttpServletRequest request, HttpServletResponse response){
        RequestContext ctx = RequestContext.getCurrentContext();
        Integer status = (Integer)request.getAttribute("javax.servlet.error.status_code");
        return ResultBody.error (status.toString(), status == 404 ? "未找到对应服务" : "内部服务器错误,正在处理");


    }
 
}
