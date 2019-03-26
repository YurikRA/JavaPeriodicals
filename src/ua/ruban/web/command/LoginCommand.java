package ua.ruban.web.command;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.db.DBManager;
import ua.ruban.db.Role;
import ua.ruban.db.entity.User;
import ua.ruban.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * Login command.
 *
 * @author Y.Ruban
 */
public class LoginCommand extends Command {

    private static final long serialVersionUID = -7598504179693846897L;

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        log.debug("Command starts");

        HttpSession session = request.getSession();

        // obtain login and password from a request
        String login = request.getParameter("login");
        log.trace("Request parameter: login --> " + login);

        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        User user = DBManager.getInstance().findUserByLogin(login);
        log.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            throw new AppException("Cannot find user with such login/password");
        }

        if (user != null && user.getActiveId() == 1){
            throw new AppException("Your account has been blocked by the administrator.");
        }

        Role userRole = Role.getRole(user);
        log.trace("userRole --> " + userRole);

        String forward = Path.PAGE_ERROR_PAGE;

        if (userRole == Role.ADMIN) {
            forward = Path.COMMAND_LIST_USERS;
        }

        if (userRole == Role.CLIENT) {
            forward = Path.COMMAND_LIST_CATEGORY;
        }

        session.setAttribute("user", user);
        log.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", userRole);
        log.trace("Set the session attribute: userRole --> " + userRole);

        log.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        // work with i18n
        String userLocaleName = user.getLocaleName();
        log.trace("userLocalName --> " + userLocaleName);

        if (userLocaleName != null && !userLocaleName.isEmpty()) {
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);

            session.setAttribute("defaultLocale", userLocaleName);
            log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);

            log.info("Locale for user: defaultLocale --> " + userLocaleName);
        }

        log.debug("Command finished");
        return forward;
    }

}