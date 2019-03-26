package ua.ruban.db.entity;

import org.junit.Test;

public class TestAllEntity {

    @Test
    public void testAllEntity(){
        new TestUser().testUser();
        new TestCategory().testCategory();
        new TestEdition().testEdition();
        new TestSubscription().testSubscription();
    }
}
