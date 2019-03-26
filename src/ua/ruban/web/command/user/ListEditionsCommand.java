package ua.ruban.web.command.user;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.db.DBManager;
import ua.ruban.db.entity.Edition;
import ua.ruban.db.entity.User;
import ua.ruban.exception.AppException;
import ua.ruban.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListEditionsCommand extends Command {

    private static final long serialVersionUID = 3419269145043420867L;

    private static final Logger log = Logger.getLogger(ListEditionsCommand.class);

    private List<Edition> editionsItems;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        log.debug("Command starts");

        HttpSession session = request.getSession();
        log.debug("Session-->"+ session.getAttribute("user"));
        User user = (User)session.getAttribute("user");

        String sort = request.getParameter("sort");
        String selectSet = request.getParameter("selectSet");
        String textSearch = request.getParameter("textSearch");

        if (sort == null && selectSet == null && textSearch == null) {
            // obtain itemCategoryId from the request
            String itemCategoryId[] = request.getParameterValues("itemCategoryId");

            if (itemCategoryId == null) {
                throw new AppException("You have not selected a category.");
            }

            // get edition items list
            editionsItems = DBManager.getInstance().findEditionsByCategoryId(itemCategoryId, user.getLocaleName());
            log.trace("Found in DB: editionsItems NULL --> " + editionsItems);
        }

        if (textSearch !=null ){
            if (!textSearch.equals("")){
                log.trace("textSearch CHAR -->");
                //get edition items list
                editionsItems = DBManager.getInstance().findEditionsBySearch(textSearch.trim(), user.getLocaleName());
                log.trace("Found in DB: editionsItems SEARCH --> " + editionsItems);

            }
        }

        if (sort !=null){
            if ("edName".equals(sort)){
                Collections.sort(editionsItems, new Comparator<Edition>() {
                    public int compare(Edition o1, Edition o2) {
                        if ("ASC".equals(selectSet)){
                            return (int)(o1.getEdName().compareTo(o2.getEdName()));
                        }else {
                            return (int)(o2.getEdName().compareTo(o1.getEdName()));
                        }
                    }
                });
            }
            if ("priceMonth".equals(sort)){
                Collections.sort(editionsItems, new Comparator<Edition>() {
                    public int compare(Edition o1, Edition o2) {
                        if ("ASC".equals(selectSet)){
                            return (int)(o1.getPriceMonth() - o2.getPriceMonth());
                        }else {
                            return (int)(o2.getPriceMonth() - o1.getPriceMonth());
                        }
                    }
                });
            }
        }

        // put edition items list to the request
        request.setAttribute("editionsItems", editionsItems);
        log.trace("Set the request attribute: editionsItems --> " + editionsItems);

        log.debug("Command finished");
        return Path.PAGE_LIST_EDITIONS;
    }


}
