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
import java.util.ArrayList;
import java.util.List;

public class ListSubscriptionsCommand extends Command {

    private final static long serialVersionUID = -2239509398313038043L;

    private static final Logger log = Logger.getLogger(ListSubscriptionsCommand.class);

    private List<Subscriptions> checkSub;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        log.debug("Command starts");

        HttpSession session = request.getSession();
        log.debug("Session-->"+ session.getAttribute("user"));
        if (session.getAttribute("user") == null){
            throw new AppException("Need login in system");
        }
        User user = (User)session.getAttribute("user");

        String itemStart[] = request.getParameterValues("stMonth");
        String itemEnd[] = request.getParameterValues("edMonth");
        String itemYear[] = request.getParameterValues("ye");
        String itemEdId[] = request.getParameterValues("itemEdId");

        if (itemEdId != null){
            checkSub = DBManager.getInstance().listSubscriptByEditions(itemEdId, user);
        }

        if (checkSub != null && itemStart != null && itemEnd != null){
            log.trace("checkSub-->"+checkSub);
            for (int i=0; i<checkSub.size(); i++){
                int st = Integer.parseInt(itemStart[i]);
                int ed = Integer.parseInt(itemEnd[i]);

                if ((ed-st)<0){
                    throw new AppException("Start month must be > end month");
                }
                checkSub.get(i).setSum((ed-st+1)*checkSub.get(i).getPriceEd());
                checkSub.get(i).setStartMonth(st);
                checkSub.get(i).setEndMonth(ed);
                checkSub.get(i).setYearEd(Integer.parseInt(itemYear[i]));
            }
        }

        String itemSubId[] = request.getParameterValues("itemSubId");
        log.trace("itemSubId[]--> " + itemSubId);

        if (itemSubId != null){
            int cost=0;
            List<Subscriptions> tmpSub = new ArrayList<Subscriptions>();
            for(int i=0; i<itemSubId.length; i++){
                Subscriptions sp = checkSub.get(Integer.parseInt(itemSubId[i])-1);
                cost += sp.getSum();
                tmpSub.add(sp);
                tmpSub.get(i).setStatusId(1);
                log.trace("Cost--> "+ cost);
            }
            if (cost>user.getMoney()){
                tmpSub.clear();
                throw new AppException("No money, please top up your account");
            } else {
                //updateUser
                user.setMoney(user.getMoney()-cost);
                DBManager.getInstance().updateUser(user);
                log.trace("Update user --> " + user);
                boolean rt = DBManager.getInstance().insertSubscription(tmpSub);
                log.trace("Result insert Subscriptions to BD--> "+ rt);
                for(int i=0; i<itemSubId.length; i++){
                    Subscriptions sp = checkSub.get(Integer.parseInt(itemSubId[i])-1);
                    sp.setStatusId(1);
                    log.trace("Set status paid--> "+ sp);
                }
            }
        }
        // put subscription items list to the request
        request.setAttribute("checkSub", checkSub);
        log.trace("Set the request attribute: checkSub --> " + checkSub);

        log.debug("Command finished");
        return Path.PAGE_LIST_SUBSCRIPTIONS;
    }

}
