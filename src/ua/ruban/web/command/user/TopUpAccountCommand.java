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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TopUpAccountCommand extends Command {

    private static final long serialVersionUID = -8837349002388071498L;

    private static final Logger log = Logger.getLogger(TopUpAccountCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        log.debug("Command starts");

        HttpSession session = request.getSession();
        log.debug("Session-->"+ session.getAttribute("user"));

        User user = (User)session.getAttribute("user");
        String sum = request.getParameter("sum");
        log.trace("sum-->"+ sum);

        if (user == null){
            throw new AppException("Enter to your account");
        }

        if (sum != null){
            int res = user.getMoney();
            user.setMoney(Integer.parseInt(sum)+res);
            if (!DBManager.getInstance().updateUser(user)){
                user.setMoney(res);
            }
            return Path.COMMAND_LIST_USER_SUBSCRIPTIONS;
        }

        log.debug("Command finished");
        return Path.PAGE_TOP_UP_ACCOUNT;
    }
}
