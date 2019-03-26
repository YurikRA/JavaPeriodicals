package ua.ruban.web.command.user;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.db.DBManager;
import ua.ruban.db.entity.Subscriptions;
import ua.ruban.db.entity.User;
import ua.ruban.exception.AppException;
import ua.ruban.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListUserSubscriptionsCommand extends Command {

    private static final long serialVersionUID = -4961972118149179165L;

    private static final Logger log = Logger.getLogger(ListUserSubscriptionsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        log.debug("Command starts");

        HttpSession session = request.getSession();
        log.debug("Session-->"+ session.getAttribute("user"));
        User user = (User)session.getAttribute("user");

        // get subscription items list
        List<Subscriptions> listUserSubscriptions = DBManager.getInstance().findUserSubscriptions(user);
        log.trace("Found in DB: listUserSubscriptions --> " + listUserSubscriptions);

        // put subscription items list to the request
        request.setAttribute("listUserSubscriptions", listUserSubscriptions);
        log.trace("Set the request attribute: listUserSubscriptions --> " + listUserSubscriptions);

        log.debug("Command finished");
        return Path.PAGE_LIST_USER_SUBSCRIPTIONS;
    }
}
