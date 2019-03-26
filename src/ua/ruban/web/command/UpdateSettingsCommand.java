package ua.ruban.web.command;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.db.DBManager;
import ua.ruban.db.entity.User;
import ua.ruban.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

public class UpdateSettingsCommand extends Command {

    private static final long serialVersionUID = 800508027636683842L;

    private static final Logger log = Logger.getLogger(UpdateSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        log.debug("Command starts");

        HttpSession session = request.getSession();
        log.debug("Session-->"+ session.getAttribute("user"));

        if (session.getAttribute("user") == null){
            throw new AppException("Need login in system");
        }
        User user = (User)session.getAttribute("user");

        String localeSet = request.getParameter("localeSet");
        log.debug("localeSet-->"+ localeSet);
        String firstName = request.getParameter("firstName");
        log.debug("firstName-->"+ firstName);
        String lastName = request.getParameter("lastName");
        log.debug("lastName-->"+ lastName);
        String password = request.getParameter("password");
        log.debug("password-->"+ password);

        if (localeSet.length() !=0){
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeSet);
            session.setAttribute("defaultLocale", localeSet);
            user.setLocaleName(localeSet);
        }

        if (firstName.length() !=0){
            user.setFirstName(firstName);
        }

        if (lastName.length() !=0){
            user.setLastName(lastName);
        }

        if (password.length() !=0){
            user.setPassword(password);
        }

        DBManager.getInstance().updateUser(user);

        log.debug("Command finished");
        return Path.PAGE_SETTINGS;
    }
}
