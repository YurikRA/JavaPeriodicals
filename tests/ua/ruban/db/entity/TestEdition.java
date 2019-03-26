package ua.ruban.db.entity;

import org.junit.Test;
import ua.ruban.exception.DBException;

import static org.junit.Assert.assertEquals;

public class TestEdition {

    @Test
    public void testEdition() {

        Edition edition = new Edition();
        edition.setEdName("EcoDrive");
        edition.setPriceMonth(50);
        edition.setCategoryId(2);
        edition.setFrequency(5);
        edition.setCategory("Auto");
        assertEquals("EcoDrive", edition.getEdName());
        assertEquals(50, edition.getPriceMonth());
        assertEquals(2, edition.getCategoryId());
        assertEquals(5, edition.getFrequency());
        assertEquals("Auto", edition.getCategory());
    }
}
