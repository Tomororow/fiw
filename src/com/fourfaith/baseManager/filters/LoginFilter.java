package com.fourfaith.baseManager.filters;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.StringUtils;

/**
 * 登录过滤器(判断session中是否有登录信息)
 */
public class LoginFilter implements Filter{
	
	protected FilterConfig filterConfig;  
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//判断是否启用
		String enabled = filterConfig.getInitParameter("enabled");  
		if ("false".equals(enabled)) { 
			//不启用则直接执行
            chain.doFilter(request, response);  
        } else {  
        	// 判断是否是http请求
    	    if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
    	    	throw new ServletException( "OncePerRequestFilter just supports HTTP requests");
    	    }
    	    
    	    // 获得在下面代码中要用的request,response,session对象
    	    HttpServletRequest httpRequest = (HttpServletRequest) request;
    	    HttpServletResponse httpResponse = (HttpServletResponse) response;
    	    HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute(CommonUtil.mSessionLoginUser) == null) {
                String keywords = filterConfig.getInitParameter("keywords");  
                String uri = httpRequest.getRequestURI(); 
                if (!StringUtils.contains(uri, keywords.split(";"))) {  
                	//uri不包含拦截关键字则直接继续执行
                    chain.doFilter(request, response);  
                    return;  
                }  
                String ignored = filterConfig.getInitParameter("ignored");  
                if (StringUtils.contains(uri, ignored.split(";"))) {  
                	//uri包含忽略关键字则直接继续执行
                    chain.doFilter(request, response);  
                    return;  
                }  
                PrintWriter out=httpResponse.getWriter();
                response.setContentType("text/html");
                
                String serverName = httpRequest.getContextPath();
                if(serverName!=null && !serverName.equals("")){
                	out.print("top.location.href='" + httpRequest.getContextPath() + "'");
                }else{
                	//无项目名称
                	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                	out.print("top.location.href='" + rootPath + "'");
                }
            } else {
            	//已登录则直接执行
                chain.doFilter(request, response);  
            }  
        }  
	}
	
	@Override
	public void destroy() {
		filterConfig = null;
	}
	
}