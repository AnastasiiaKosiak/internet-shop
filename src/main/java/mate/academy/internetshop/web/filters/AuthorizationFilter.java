package mate.academy.internetshop.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class AuthorizationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    private static final Injector INJECTOR = Injector.getInstance("mate.academy.internetshop");
    private Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();
    private final UserService userService = (UserService)INJECTOR.getInstance(UserService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/users/all", Set.of(Role.RoleName.valueOf("ADMIN")));
        protectedUrls.put("/products/add", Set.of(Role.RoleName.valueOf("ADMIN")));
        protectedUrls.put("/products/admin", Set.of(Role.RoleName.valueOf("ADMIN")));
        protectedUrls.put("/products/delete", Set.of(Role.RoleName.valueOf("ADMIN")));
        protectedUrls.put("/order/delete", Set.of(Role.RoleName.valueOf("ADMIN")));
        protectedUrls.put("/order/admin", Set.of(Role.RoleName.valueOf("ADMIN")));
        protectedUrls.put("/order/create", Set.of(Role.RoleName.valueOf("USER")));
        protectedUrls.put("/order/all", Set.of(Role.RoleName.valueOf("USER")));
        protectedUrls.put("/products/all", Set.of(Role.RoleName.valueOf("USER")));
        protectedUrls.put("/cart/all", Set.of(Role.RoleName.valueOf("USER")));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String requestedUrl = req.getServletPath();
        if (protectedUrls.get(requestedUrl) == null) {
            filterChain.doFilter(req, resp);
            return;
        }
        Long userId = (Long)req.getSession().getAttribute(USER_ID);
        if (userId == null) {
            LOGGER.info("The user with such login or password doesn't exist");
            resp.sendRedirect("/login");
            return;
        }
        User user = userService.get(userId).get();
        if (isAuthorized(user, protectedUrls.get(requestedUrl))) {
            filterChain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isAuthorized(User user, Set<Role.RoleName> authorizedRoles) {
        for (Role.RoleName authorizedRole : authorizedRoles) {
            for (Role userRole : user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
