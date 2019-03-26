package ua.ruban;

/**
 * Path holder (jsp pages, controller commands).
 *
 * @author Y.Ruban
 *
 */

public class Path {

    // pages
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/errorPage.jsp";
    public static final String PAGE_LIST_CATEGORY = "/WEB-INF/jsp/client/listCategory.jsp";
    public static final String PAGE_LIST_EDITIONS = "/WEB-INF/jsp/client/listEditions.jsp";
    public static final String PAGE_LIST_SUBSCRIPTIONS = "/WEB-INF/jsp/client/listSubscriptions.jsp";

    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
    public static final String PAGE_LIST_USER_SUBSCRIPTIONS = "/WEB-INF/jsp/client/listUserSubscriptions.jsp";
    public static final String PAGE_TOP_UP_ACCOUNT = "/WEB-INF/jsp/client/topUpAccount.jsp";
    public static final String PAGE_REGISTRATION = "/WEB-INF/jsp/client/registration.jsp";


    public static final String PAGE_LIST_USERS = "/WEB-INF/jsp/admin/listUsers.jsp";
    public static final String PAGE_LIST_ALL_EDITIONS = "/WEB-INF/jsp/admin/listAllEditions.jsp";
    public static final String PAGE_EDIT_EDITIONS = "/WEB-INF/jsp/admin/editEdition.jsp";
    public static final String PAGE_ADD_CATEGORY = "/WEB-INF/jsp/admin/addCategory.jsp";

    // commands
    public static final String COMMAND_LIST_CATEGORY = "/controller?command=listCategory";
    public static final String COMMAND_LIST_USER_SUBSCRIPTIONS = "/controller?command=userSubscriptions";
    public static final String COMMAND_LIST_USERS = "/controller?command=listUsers";
}
