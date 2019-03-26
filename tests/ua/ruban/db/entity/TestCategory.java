package ua.ruban.db.entity;

import org.junit.Test;
import ua.ruban.exception.DBException;

import static org.junit.Assert.assertEquals;

public class TestCategory {

    @Test
    public void testCategory(){

        Category category = new Category();
        category.setId(0);
        category.setCategory("auto");
        category.setCategoryId(1);
        assertEquals(0, category.getId());
        assertEquals("auto", category.getCategory());
        assertEquals(1, category.getCategoryId());
    }
}
