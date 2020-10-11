package spm.app.Filter;

import javax.servlet.*;
import java.io.IOException;

public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("拦截器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截器执行");
    }

    @Override
    public void destroy() {
        System.out.println("拦截器销毁");
    }
}
