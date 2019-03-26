package ua.ruban.web.command.user;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.db.DBManager;
import ua.ruban.db.entity.User;
import ua.ruban.exception.AppException;
import ua.ruban.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Registration extends Command {

    private static final long serialVersionUID = 6858140910160535523L;

    private static final Logger log = Logger.getLogger(Registration.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        log.debug("Command starts");

        String login = request.getParameter("login");
        log.trace("login-->"+ login);
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        log.trace("firstName-->"+ firstName);
        String lastName = request.getParameter("lastName");
        log.trace("lastName-->"+ lastName);
        String language = request.getParameter("language");
        log.trace("language-->"+ language);

        if (login == null){
            log.trace("returnFirst-->");
            return Path.PAGE_REGISTRATION;
        }

        User us = DBManager.getInstance().findUserByLogin(login);
        log.trace("User us-->"+ us);

        if (us == null){
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLocaleName(language);
            user.setRoleId(1);
            user.setMoney(0);
            user.setActiveId(0);
            DBManager.getInstance().insertUser(user);
        }else {
            throw new AppException("Change your login");
        }
        return Path.PAGE_LOGIN;
    }
}
