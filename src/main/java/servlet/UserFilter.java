package servlet;

import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class UserFilter {

    @WebFilter(
            urlPatterns = "/user",
            filterName = "UserFilter",
            description = "add temp User data")
    public class FilterServlet implements javax.servlet.Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            try {
                request.setAttribute("user", UserServiceImpl.getInstance().getTempUser());
            } catch (Exception e) {
                e.printStackTrace();
            }
            chain.doFilter(request, response);

        }

        @Override
        public void destroy() {
        }

    }
}