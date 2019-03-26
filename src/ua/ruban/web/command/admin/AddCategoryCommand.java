package ua.ruban.web.command.admin;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.db.DBManager;
import ua.ruban.exception.AppException;
import ua.ruban.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCategoryCommand extends Command {

    private static final long serialVersionUID = -4660070085264057920L;

    private static final Logger log = Logger.getLogger(AddCategoryCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        log.debug("Command starts");

        String categoryName = request.getParameter("categoryName");
        log.debug("categoryName-->"+categoryName);
        String categoryNameRu = request.getParameter("categoryNameRu");
        log.debug("categoryNameRu-->"+categoryNameRu);

        if (categoryName != null && categoryName.length() !=0  && categoryNameRu.length() != 0){
            DBManager.getInstance().insertCategory(categoryName, categoryNameRu);
            return Path.COMMAND_LIST_CATEGORY;
        }

        return Path.PAGE_ADD_CATEGORY;
    }
}
