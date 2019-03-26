package ua.ruban.db.entity;

import org.junit.Test;
import ua.ruban.db.DBManager;
import ua.ruban.exception.DBException;

import static org.junit.Assert.assertEquals;

public class TestUser {

    @Test
    public void testUser() {

        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setLocaleName("en");
        user.setRoleId(0);
        user.setMoney(0);
        user.setActiveId(0);
        assertEquals("admin", user.getLogin());
        assertEquals("admin", user.getPassword());
        assertEquals("Ivan", user.getFirstName());
        assertEquals("Ivanov", user.getLastName());
        assertEquals("en", user.getLocaleName());
        assertEquals(0, user.getRoleId());
        assertEquals(0, user.getMoney());
        assertEquals(0, user.getActiveId());
    }
}
