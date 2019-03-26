package ua.ruban.db;

import org.apache.log4j.Logger;
import ua.ruban.db.entity.Category;
import ua.ruban.db.entity.Edition;
import ua.ruban.db.entity.Subscriptions;
import ua.ruban.db.entity.User;
import ua.ruban.exception.DBException;
import ua.ruban.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DBManager {

    private static final Logger log = Logger.getLogger(DBManager.class);

    // singleton

    private static DBManager instance;

    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            // period - the name of data source
            ds = (DataSource) envContext.lookup("jdbc/periodicals");
            log.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            log.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    private DataSource ds;

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in
     * your WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return DB connection.
     */
    public Connection getConnection() throws DBException {
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            log.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }

    //SQL

    private static final String SQL_FIND_EDITION_BY_SEARCH = "SELECT t1.*, t2.*  FROM editions t1 JOIN categories t2 ON t1.categoryId = t2.id WHERE t1.edName =?";
    private static final String SQL_INSERT_SUBSCRIPTION = "INSERT INTO subscriptions VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET login=?, password=?, firstName=?, lastName=?, localeName=?, money=?,activeId=? WHERE id=?";
    private static final String SQL_FIND_USERS_SUBSCRIPTIONS = "SELECT * FROM subscriptions WHERE userId =?";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String SQL_DELETE_USER_BY_ID = "delete from users where id =?";
    private static final String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM categories ORDER BY category";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users WHERE roleId =1 ORDER BY lastName";
    private static final String SQL_FIND_ALL_EDITIONS = "SELECT t1.*, t2.*  FROM editions t1 JOIN categories t2 ON t1.categoryId = t2.id ORDER BY edName";
    private static final String SQL_FIND_EDITION_BY_ID = "SELECT t1.*, t2.*  FROM editions t1 JOIN categories t2 ON t1.categoryId = t2.id WHERE t1.id=?";
    private static final String SQL_UPDATE_EDITION = "UPDATE editions SET edName=?, priceMonth=?, categoryId=?, frequency=? WHERE id=?";
    private static final String SQL_INSERT_EDITION = "INSERT INTO editions VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String SQL_DELETE_EDITION_BY_ID = "delete from editions where id =?";
    private static final String SQL_INSERT_CATEGORIES = "INSERT INTO categories VALUES (DEFAULT, ?, ?)";
    private static final String SQL_FIND_ALL_SUBSCRIPTION = "SELECT * FROM subscriptions";


    public List<Subscriptions> findAllSubs() throws DBException {
        List<Subscriptions> usersList = new ArrayList<Subscriptions>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_SUBSCRIPTION);
            while (rs.next()) {
                usersList.add(extractSubscription(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            log.error(Messages.ERR_CANNOT_OBTAIN_ALL_SUBS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_SUBS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return usersList;
    }

    /**
     * Returns a user with the given login.
     *
     * @param login
     *            User login.
     * @return User entity.
     */
    public User findUserByLogin(String login) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    /**
     * Returns a user with the given id.
     *
     * @param id
     *            User id.
     * @return User entity.
     */
    public User findUserById(int id) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    /**
     * Returns all users.
     *
     * @return List<User> entity.
     */
    public List<User> findAllUsers() throws DBException {
        List<User> usersList = new ArrayList<User>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_USERS);
            while (rs.next()) {
                usersList.add(extractUser(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            log.error(Messages.ERR_CANNOT_OBTAIN_ALL_USERS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_USERS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return usersList;
    }


    public void insertUser(User user) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_USER,Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, user.getLogin());
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k++,user.getLocaleName());
            pstmt.setInt(k++,user.getRoleId());
            pstmt.setInt(k++,user.getMoney());
            pstmt.setInt(k++, user.getActiveId());
            pstmt.execute();
            con.commit();
        }catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void deleteUserById(int id) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_USER_BY_ID);
            pstmt.setInt(1, id);
            pstmt.execute();
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_DELETE_USER_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public List<Category> findAllCategories(String local) throws DBException {
        List<Category> categoriesList = new ArrayList<Category>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_CATEGORIES);
            while (rs.next()){
                categoriesList.add(extractCategory(rs, local));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            log.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
        } finally {
            close(con, stmt, rs);
        }
        return categoriesList;
    }

    public void insertCategory(String cName, String cNameRu) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_CATEGORIES,Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, cName);
            pstmt.setString(k++, cNameRu);
            pstmt.execute();
            con.commit();
        }catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_INSERT_CATEGORY, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void insertEdition(Edition edition) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_EDITION,Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, edition.getEdName());
            pstmt.setInt(k++, edition.getPriceMonth());
            pstmt.setInt(k++, edition.getCategoryId());
            pstmt.setInt(k++, edition.getFrequency());
            pstmt.execute();
            con.commit();
        }catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_INSERT_EDITION, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void deleteEditionById(int id) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_EDITION_BY_ID);
            pstmt.setInt(1, id);
            pstmt.execute();
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_DELETE_EDITION_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public List<Edition> findEditionsByCategoryId(String[] ids, String locale) throws DBException {
        List<Edition> listEditions = new ArrayList<Edition>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            // create SQL query like "... id IN (1, 2, 7)"
            StringBuilder query = new StringBuilder(
                    "SELECT t1.*, t2.*  FROM editions t1 " +
                            "JOIN categories t2 ON t1.categoryId = t2.id " +
                            "WHERE t1.categoryId IN (");
            for (String idAsString : ids) {
                if (idAsString != null){
                    log.debug("idAsString-->"+idAsString);
                    query.append(idAsString).append(", ");
                }
            }
            query.delete(query.length()-2, query.length());
            query.append(");");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                listEditions.add(extractEdition(rs, locale));
            }
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_EDITION, ex);
        } finally {
            close(con, stmt, rs);
        }
        return listEditions;
    }

    public List<Edition> findEditionsBySearch(String search, String local) throws DBException {
        List<Edition> listEdSearch = new ArrayList<Edition>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_EDITION_BY_SEARCH);
            pstmt.setString(1, search);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listEdSearch.add(extractEdition(rs, local));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_EDITION, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return listEdSearch;
    }

    public List<Edition> findAllEditions(String local) throws DBException {
        List<Edition> listEditions = new ArrayList<Edition>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_EDITIONS);
            while (rs.next()){
                listEditions.add(extractEdition(rs, local));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            log.error(Messages.ERR_CANNOT_OBTAIN_EDITIONS, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_EDITIONS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return listEditions;
    }

    public Edition findEditionById(int id, String local) throws DBException {
        Edition edition = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_EDITION_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                edition = extractEdition(rs, local);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_EDITION, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return edition;
    }

    public List<Subscriptions> listSubscriptByEditions(String[] ids, User user) throws DBException {
        List<Subscriptions> listSubscriptions = new ArrayList<Subscriptions>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            // create SQL query like "... id IN (1, 2, 7)"
            StringBuilder query = new StringBuilder(
                    "SELECT t1.*, t2.*  FROM editions t1 " +
                            "JOIN categories t2 ON t1.categoryId = t2.id " +
                            "WHERE t1.id IN (");
            for (String idAsString : ids) {
                query.append(idAsString).append(",");
            }
            query.delete(query.length()-1, query.length());
            query.append(");");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                listSubscriptions.add(extractSubscriptionByEdition(rs, user));
            }
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(
                    Messages.ERR_CANNOT_OBTAIN_EDITIONS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return listSubscriptions;
    }

    public boolean insertSubscription(List<Subscriptions> listSub) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_SUBSCRIPTION,Statement.RETURN_GENERATED_KEYS);
            for(Subscriptions sub : listSub){
                int k = 1;
                pstmt.setString(k++, sub.getNameEdition());
                pstmt.setInt(k++,sub.getPriceEd());
                pstmt.setInt(k++, sub.getStartMonth());
                pstmt.setInt(k++, sub.getEndMonth());
                pstmt.setInt(k++, sub.getSum());
                pstmt.setInt(k++, sub.getYearEd());
                pstmt.setInt(k++, sub.getUserId());
                pstmt.setInt(k++, sub.getStatusId());
                pstmt.setString(k++, sub.getDate());
                pstmt.setInt(k++,sub.getEditionId());
                pstmt.execute();
            }
            con.commit();
            result = true;
        }catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_INSERT_SUBSCRIPTION, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return result;
    }


    public List<Subscriptions> findUserSubscriptions(User user) throws DBException {
        List<Subscriptions> listUserSubscriptions = new ArrayList<Subscriptions>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USERS_SUBSCRIPTIONS);
            log.trace("userId-->" + user.getId());
            pstmt.setInt(1, user.getId());
            rs = pstmt.executeQuery();
            log.trace("rs-->" + rs);
            while (rs.next()) {
                listUserSubscriptions.add(extractSubscription(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_SUBSCRIPTION, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return listUserSubscriptions;
    }


    /**
     * Update user.
     *
     * @param user
     *            user to update.
     * @throws DBException
     */
    public boolean updateUser(User user) throws DBException {
        boolean result;
        Connection con = null;
        try {
            con = getConnection();
            updateUser(con, user);
            con.commit();
            result = true;
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
        } finally {
            close(con);
        }
        return result;
    }

    // Entity access methods (for transactions)

    /**
     * Update user.
     *
     * @param user
     *            user to update.
     * @throws SQLException
     */
    private void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(SQL_UPDATE_USER);
            int k = 1;
            pstmt.setString(k++, user.getLogin());
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k++, user.getLocaleName());
            pstmt.setInt(k++, user.getMoney());
            pstmt.setInt(k++, user.getActiveId());
            pstmt.setLong(k, user.getId());
            pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }
    }

    public void updateEdition(Edition edition) throws DBException {
        Connection con = null;
        try {
            con = getConnection();
            updateEdition(con, edition);
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_EDITION, ex);
        } finally {
            close(con);
        }
    }


    private void updateEdition(Connection con, Edition edition) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(SQL_UPDATE_EDITION);
            int k = 1;
            pstmt.setString(k++, edition.getEdName());
            pstmt.setInt(k++, edition.getPriceMonth());
            pstmt.setInt(k++, edition.getCategoryId());
            pstmt.setInt(k++, edition.getFrequency());
            pstmt.setInt(k++, edition.getId());
            pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }
    }

    // DB util methods

    /**
     * Commits and close the given connection.
     *
     * @param con
     *            Connection to be committed and closed.
     */
    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con
     *            Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks a connection.
     *
     * @param con
     *            Connection to be rollbacked.
     */
    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                log.error("Cannot rollback transaction", ex);
            }
        }
    }

    /**
     * Closes a connection.
     *
     * @param con
     *            Connection to be closed.
     */
    private void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                log.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes a statement object.
     */
    private void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                log.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes a result set object.
     */
    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                log.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }

    /**
     * Closes resources.
     */
    private void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    // Other methods

    private Category extractCategory(ResultSet rs, String local) throws SQLException {
        Category category = new Category();
        category.setCategoryId(rs.getInt(Fields.ENTITY_ID));
        if (local.equals("ru")){
            category.setCategory(rs.getString(Fields.CATEGORY_NAME_RU));
        }else{
            category.setCategory(rs.getString(Fields.CATEGORY_NAME));
        }
        return category;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setLocaleName(rs.getString(Fields.USER_LOCALE_NAME));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        user.setMoney(rs.getInt(Fields.USER_MONEY));
        user.setActiveId(rs.getInt(Fields.USER_ACTIVE_ID));
        return user;
    }

    /**
     * Extracts a edition item from the result set.
     *
     * @param rs
     *            Result set from which a edition item entity will be extracted.
     * @return Edition item entity.
     */
    private Edition extractEdition(ResultSet rs, String local) throws SQLException {
        Edition edition = new Edition();
        edition.setId(rs.getInt(Fields.ENTITY_ID));
        edition.setEdName(rs.getString(Fields.EDITION_NAME));
        edition.setPriceMonth(rs.getInt(Fields.EDITION_PRICE));
        edition.setCategoryId(rs.getInt(Fields.EDITION_CATEGORY));
        edition.setFrequency(rs.getInt(Fields.EDITION_FREQUENCY));
        if (local.equals("ru")){
            edition.setCategory(rs.getString(Fields.CATEGORY_NAME_RU));
        }else{
            edition.setCategory(rs.getString(Fields.CATEGORY_NAME));
        }
        return edition;
    }

    /**
     * Extracts a subscription item from the result set.
     *
     * @param rs
     *            Result set from which a menu item entity will be extracted.
     * @return Subscriptions item entity.
     */
    private Subscriptions extractSubscription(ResultSet rs) throws SQLException {
        Subscriptions subscriptions = new Subscriptions();
        subscriptions.setId(rs.getInt(Fields.ENTITY_ID));
        subscriptions.setNameEdition(rs.getString(Fields.SUBSCRIPTION_NAME));
        subscriptions.setPriceEd(rs.getInt(Fields.SUBSCRIPTION_PRICE));
        subscriptions.setStartMonth(rs.getInt(Fields.SUBSCRIPTION_START_MONTH));
        subscriptions.setEndMonth(rs.getInt(Fields.SUBSCRIPTION_END_MONTH));
        subscriptions.setSum(rs.getInt(Fields.SUBSCRIPTION_SUM));
        subscriptions.setYearEd(rs.getInt(Fields.SUBSCRIPTION_YEAR_ED));
        subscriptions.setUserId(rs.getInt(Fields.SUBSCRIPTION_USER_ID));
        subscriptions.setStatusId(rs.getInt(Fields.SUBSCRIPTION_STATUS_ID));
        subscriptions.setDate(rs.getString(Fields.SUBSCRIPTION_DATE));
        subscriptions.setEditionId(rs.getInt(Fields.SUBSCRIPTION_EDITION_ID));
        return subscriptions;
    }

    private Subscriptions extractSubscriptionByEdition(ResultSet rs, User user) throws SQLException {
        Subscriptions subscriptions = new Subscriptions();
        subscriptions.setId(rs.getInt(Fields.ENTITY_ID));
        subscriptions.setNameEdition(rs.getString(Fields.EDITION_NAME));
        subscriptions.setPriceEd(rs.getInt(Fields.EDITION_PRICE));
        subscriptions.setStartMonth(0);
        subscriptions.setEndMonth(0);
        subscriptions.setSum(rs.getInt(Fields.EDITION_PRICE));
        subscriptions.setYearEd(0);
        subscriptions.setUserId(user.getId());
        subscriptions.setStatusId(0);
        subscriptions.setDate(
                new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        subscriptions.setEditionId(rs.getInt(Fields.ENTITY_ID));
        return subscriptions;
    }

}
