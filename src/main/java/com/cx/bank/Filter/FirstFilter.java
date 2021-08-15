
package com.cx.bank.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName FirstFilter
 * @Description 对请求的编码进行处理
 * @Author Bruce Xu
 * @Date 2021/8/5 15:19
 * @Version 1.0
 */
@WebFilter(filterName = "FirstFilter", urlPatterns = "/*", initParams = @WebInitParam(name = "encoding", value = "GBK"))
public class FirstFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.encoding = config.getInitParameter("encoding");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        req.setCharacterEncoding(encoding);
        resp.setContentType("text/html;charset=" + encoding);
        chain.doFilter(req, resp);
    }
}
