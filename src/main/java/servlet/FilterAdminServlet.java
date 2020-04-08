package servlet;

import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        urlPatterns = "/admin/*",
        filterName = "UserFilter",
        description = "Filter don't pass users to admin")
public class FilterAdminServlet implements javax.servlet.Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (UserServiceImpl.getInstance().getTempUser().getRole().equals("user")){
                HttpServletResponse resp = (HttpServletResponse)response;
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_GATEWAY);
                ((HttpServletResponse) response).sendRedirect("/user");
                chain.doFilter(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HttpServletResponse resp = (HttpServletResponse)response;
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }
}
