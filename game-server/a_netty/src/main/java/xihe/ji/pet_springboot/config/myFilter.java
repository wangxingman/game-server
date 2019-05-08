package xihe.ji.pet_springboot.config;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class myFilter implements Filter {





    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url=httpServletRequest.getServletPath();
        httpServletRequest.setAttribute("path",url);
        if (!url.contains("upload")) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin",  httpServletRequest.getHeader("Origin"));
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }
}
