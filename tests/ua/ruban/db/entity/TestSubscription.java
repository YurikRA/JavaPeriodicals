package ua.ruban.db.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestSubscription {

    @Test
    public void testSubscription() {

        Subscriptions subscriptions = new Subscriptions();
        subscriptions.setNameEdition("EcoDrive");
        subscriptions.setPriceEd(100);
        subscriptions.setStartMonth(2);
        subscriptions.setEndMonth(10);
        subscriptions.setSum(800);
        subscriptions.setYearEd(2020);
        subscriptions.setUserId(3);
        subscriptions.setStatusId(1);
        subscriptions.setDate("16-03-2019");
        subscriptions.setEditionId(3);
        assertEquals("EcoDrive", subscriptions.getNameEdition());
        assertEquals(100, subscriptions.getPriceEd());
        assertEquals(2, subscriptions.getStartMonth());
        assertEquals(10, subscriptions.getEndMonth());
        assertEquals(800, subscriptions.getSum());
        assertEquals(2020, subscriptions.getYearEd());
        assertEquals(3, subscriptions.getUserId());
        assertEquals(1, subscriptions.getStatusId());
        assertEquals("16-03-2019", subscriptions.getDate());
        assertEquals(3, subscriptions.getEditionId());
    }
}
