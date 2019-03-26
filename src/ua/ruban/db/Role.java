package ua.ruban.db;

import ua.ruban.db.entity.User;

/**
 * Role entity.
 *
 * @author Y.Ruban
 *
 */

public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
