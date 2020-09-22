package com.sys.filter;

import com.google.gson.GsonBuilder;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.sys.exception.BizException;
import com.sys.vo.ResultBody;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
@Slf4j
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ResultBody resultBody = null;
        try {
            Throwable re = getOriginException(ctx.getThrowable());
            if (re instanceof BizException) {
                BizException bizException = (BizException)re;
                resultBody = ResultBody.builder().code(bizException.getErrorCode()).msg(bizException.getErrorMsg()).build();
            }
            if (Objects.isNull(resultBody)) {
                resultBody  = ResultBody.builder().code("500").msg(re.getLocalizedMessage()).build();
            }
        } catch (Exception e) {
            String error = "Error during filtering[ErrorFilter]";
            log.error(error, e);
            resultBody  = ResultBody.builder().code("500").msg(e.getMessage()).build();
        }
        ctx.getResponse().setStatus(Integer.valueOf(resultBody.getCode()));
        responseOutWithJson(ctx.getResponse(), resultBody);
        return null;

    }

    private Throwable getOriginException(Throwable e) {
        e = e.getCause();
        while (e.getCause() != null) {
            e = e.getCause();
        }
        return e;
    }

    protected void responseOutWithJson(HttpServletResponse response,
                                       Object responseObject) {
        //将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(new GsonBuilder().serializeNulls().create().toJson(responseObject));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}








































































