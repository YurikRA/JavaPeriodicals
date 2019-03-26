package ua.ruban.web.command.admin;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.db.DBManager;
import ua.ruban.db.entity.User;
import ua.ruban.exception.AppException;
import ua.ruban.exception.DBException;
import ua.ruban.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListUsersActionCommand extends Command {

    private static final long serialVersionUID = -6147477032688602270L;

    private static final Logger log = Logger.getLogger(ListUsersActionCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        log.debug("Command starts");

        HttpSession session = request.getSession();
        log.debug("Session-->"+ session.getAttribute("user"));
        User us = (User)session.getAttribute("user");
        if (us == null || us.getRoleId() == 1){
            throw new AppException("Enter like admin");
        }

        String itemButBlocking = request.getParameter("itemButBlocking");
        log.debug("itemButBlocking-->"+ itemButBlocking);
        String itemButUnlocking = request.getParameter("itemButUnlocking");
        log.debug("itemButUnlocking-->"+ itemButUnlocking);
        String itemButDelete = request.getParameter("itemButDelete");
        log.debug("itemButDelete-->"+ itemButDelete);

        if (itemButBlocking !=null){
            updateUs(Integer.parseInt(itemButBlocking), 1);
        }

        if (itemButUnlocking != null){
            updateUs(Integer.parseInt(itemButUnlocking), 0);
        }

        if (itemButDelete != null){
            DBManager.getInstance().deleteUserById(Integer.parseInt(itemButDelete));
        }

        // get users items list
        List<User> usersItems = DBManager.getInstance().findAllUsers();
        log.trace("Found in DB: usersItems --> " + usersItems);

        // put users items list to the request
        request.setAttribute("usersItems", usersItems);
        log.trace("Set the request attribute: usersItems --> " + usersItems);

        log.debug("Command finished");
        return Path.PAGE_LIST_USERS;
    }

    private void updateUs(int id, int op) throws DBException {
        User user = DBManager.getInstance().findUserById(id);
        user.setActiveId(op);
        DBManager.getInstance().updateUser(user);
    }
}
