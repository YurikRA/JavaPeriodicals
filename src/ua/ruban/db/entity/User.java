package ua.ruban.db.entity;

/**
 * User entity.
 *
 * @author Y.Ruban
 *
 */

public class User extends Entity {

    private static final long serialVersionUID = 7175487599862857307L;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String localeName;

    private int roleId;

    private int money;

    private int activeId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocaleName() {
        return localeName;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getActiveId() {
        return activeId;
    }

    public void setActiveId(int activeId) {
        this.activeId = activeId;
    }

    @Override
    public String toString() {
        return "User [login=" + login + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", localeName=" + localeName + ", roleId=" + roleId
                + ", money=" + money + ", activeId=" + activeId +"]";
    }
}
