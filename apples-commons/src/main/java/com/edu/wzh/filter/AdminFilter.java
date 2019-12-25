package com.edu.wzh.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter implements Filter {
    String staticStr="/static";
    String  dyStr="/index.jsp/regist.jsp/favicon.ico/login/pages/Adminmain.jsp/NoAccess.jsp";
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        String path=request.getServletPath();
        System.out.println(path);
        String paths= (String) request.getSession().getAttribute("paths");
        if(path.contains(staticStr)){
            filterChain.doFilter(request,response);
            return;
        }
        if(dyStr.contains(path)){
            filterChain.doFilter(request,response);
            return;
        }
        if(paths==null){
            response.sendRedirect("/index.jsp");
            return;
        }
        if(paths.contains(path)){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect("/NoAccess.jsp");
        }
    }

    public void destroy() {
    }
}
