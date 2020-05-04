package mate.academy.internetshop.security;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User user = userService.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException("Incorrect username or password"));
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new AuthenticationException("Incorrect username or password");
    }
}
