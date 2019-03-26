package ua.ruban.web.command.admin;

import org.apache.log4j.Logger;
import ua.ruban.Path;
import ua.ruban.db.DBManager;
import ua.ruban.db.entity.Category;
import ua.ruban.db.entity.Edition;
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

public class EditEditionsCommand extends Command {

    private static final long serialVersionUID = 8276153437229113879L;

    private static final Logger log = Logger.getLogger(EditEditionsCommand.class);

    private Edition edition;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        log.debug("Command started");

        HttpSession session = request.getSession();
        log.debug("Session-->"+ session.getAttribute("user"));
        User user = (User)session.getAttribute("user");

        String addEdition = request.getParameter("addEdition");
        log.debug("addEdition-->"+addEdition);

        String operationEd = request.getParameter("buttonEd");
        log.debug("operationEd-->"+operationEd);
        String itemButEdit = request.getParameter("itemButEdit");
        log.debug("itemButEdit-->"+itemButEdit);

        if (itemButEdit != null){
            edition = DBManager.getInstance().findEditionById(Integer.parseInt(itemButEdit), user.getLocaleName());
        }

        String edName = request.getParameter("edName");
        log.debug("edName-->"+edName);
        String priceMonth = request.getParameter("priceMonth");
        log.debug("priceMonth-->"+priceMonth);
        String frequency = request.getParameter("frequency");
        log.debug("frequency-->"+frequency);

        String selectCategory = request.getParameter("selectCategory");
        log.debug("selectCategory ->"+selectCategory);

        if (selectCategory != null && selectCategory.equals("0") && operationEd != null && !operationEd.equals("updEd")){
            throw new DBException("Please, select category edition.");
        }

        boolean change = false;
        boolean newEd = false;

        if (addEdition !=null){
            edition = new Edition();
            newEd = true;
        }

        if (edName !=null && edName.length() !=0){
            edition.setEdName(edName);
            change =true;
        }

        if (priceMonth != null && priceMonth.length() !=0){
            edition.setPriceMonth(Integer.parseInt(priceMonth));
            change =true;
        }

        if (selectCategory != null && !selectCategory.equals("0")){
            edition.setCategoryId(Integer.parseInt(selectCategory));
            change =true;
        }

        if (frequency != null && frequency.length() !=0){
            edition.setFrequency(Integer.parseInt(frequency));
            change =true;
        }

        if (operationEd != null && operationEd.equals("addEd") && change){
            DBManager.getInstance().insertEdition(edition);

        }

        if (operationEd != null && operationEd.equals("updEd") && change){
            DBManager.getInstance().updateEdition(edition);
        }

        List<Category> cate = DBManager.getInstance().findAllCategories(user.getLocaleName());
        // put category items list to the request
        request.setAttribute("newEd", newEd);
        request.setAttribute("edition", edition);
        request.setAttribute("cate", cate);
        log.trace("cate --> " + cate);
        log.trace("edition --> " + edition);

        log.debug("Command finished");
        return Path.PAGE_EDIT_EDITIONS;
    }
}
