package com.sys.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;


@Slf4j
public class CrossFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public Object run() {
		
		RequestContext cxt = RequestContext.getCurrentContext();
		HttpServletRequest request = cxt.getRequest();
		String method = request.getMethod();
		log.info("url:{},method:{},origin:{}",request.getRequestURI(),method);
		
		String allowOrigin = "*";
		if(StringUtils.isNotEmpty(allowOrigin)){
			HttpServletResponse response = cxt.getResponse();
			response.setHeader("Access-Control-Allow-Origin", allowOrigin);
			response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization,Accept,X-Requested-With,POWERED-BY-MENGXIANHUI,logintoken");
		}
		
		
		if(StringUtils.equalsIgnoreCase(method, HttpMethod.OPTIONS.name())){
			cxt.setSendZuulResponse(false);
		}
		
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}


}
