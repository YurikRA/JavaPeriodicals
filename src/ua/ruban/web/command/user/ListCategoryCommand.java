package ua.ruban.web.command.user;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.db.DBManager;
import ua.ruban.db.entity.Category;
import ua.ruban.db.entity.User;
import ua.ruban.exception.DBException;
import ua.ruban.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListCategoryCommand extends Command {

    private static final long serialVersionUID = -5782520664021358157L;

    private static final Logger log = Logger.getLogger(ListCategoryCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DBException {
        log.debug("Command starts");

        HttpSession session = request.getSession();
        log.debug("Session-->"+ session.getAttribute("user"));
        User user = (User)session.getAttribute("user");

        // get category items list
        List<Category> categoryItems = DBManager.getInstance().findAllCategories(user.getLocaleName());
        log.trace("Found in DB: categoryItems --> " + categoryItems);

        // put category items list to the request
        request.setAttribute("categoryItems", categoryItems);
        log.trace("Set the request attribute: categoryItems --> " + categoryItems);

        log.debug("Command finished");
        return Path.PAGE_LIST_CATEGORY;
    }
}
