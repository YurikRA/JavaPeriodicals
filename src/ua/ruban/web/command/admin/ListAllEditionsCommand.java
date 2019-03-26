package ua.ruban.web.command.admin;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.db.DBManager;
import ua.ruban.db.entity.Edition;
import ua.ruban.db.entity.Subscriptions;
import ua.ruban.db.entity.User;
import ua.ruban.exception.AppException;
import ua.ruban.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListAllEditionsCommand extends Command {

    private static final long serialVersionUID = 8276153437229113879L;

    private static final Logger log = Logger.getLogger(ListAllEditionsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        log.debug("Command started");

        HttpSession session = request.getSession();
        log.debug("Session-->"+ session.getAttribute("user"));
        User user = (User)session.getAttribute("user");

        String itemButDelete = request.getParameter("itemButDelete");
        log.debug("itemButDelete-->"+itemButDelete);
        if (itemButDelete != null){
            DBManager.getInstance().deleteEditionById(Integer.parseInt(itemButDelete));
        }

        // get editions items list
        List<Edition> editionsItems = DBManager.getInstance().findAllEditions(user.getLocaleName());
        log.trace("Found in DB: editionsItems --> " + editionsItems);

        //get all subscriptions
        List<Subscriptions> listSub = DBManager.getInstance().findAllSubs();
        //create list sum subscription
        List<Integer> countEd = new ArrayList<>();

        for (Edition ed: editionsItems){
            int sum = 0;
            for (Subscriptions subs : listSub){
                if ((ed.getEdName().equals(subs.getNameEdition())) && (ed.getPriceMonth() == subs.getPriceEd())){
                    sum +=1;
                }
            }
            countEd.add(sum);
        }

        request.setAttribute("countEd", countEd);
        log.trace("Set the request attribute: countEd --> " + countEd);

        // put editions items list to the request
        request.setAttribute("editionsItems", editionsItems);
        log.trace("Set the request attribute: editionsItems --> " + editionsItems);

        log.debug("Command finished");
        return Path.PAGE_LIST_ALL_EDITIONS;
    }
}
