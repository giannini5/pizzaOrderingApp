package pizza.domain_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pizza.domain.PizzaStore;
import pizza.domain.Promotion;
import org.junit.Test;
import pizza.orm.*;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PromotionTests {
    private static Promotion promotion;

    private static String NAME          = "Dave Pizza";
    private static Date   START_DATE    = Date.valueOf("2019-03-06");
    private static Date   END_DATE      = Date.valueOf("2019-03-07");
    private static String TYPE          = EnumPromotionTypeOrm.DOLLARS_OFF;
    private static int    VALUE         = 4;

    @BeforeClass
    public static void classSetup() throws Exception {
        TestHelper.primeDatabase();
        PizzaStore pizzaStore = new PizzaStore(TestHelper.store.getId());
        promotion = new Promotion(pizzaStore, NAME, START_DATE, END_DATE, TYPE, VALUE);
    }

    @AfterClass
    public static void classTeardown() throws Exception {
        Database.getInstance().dropAllObjects();
    }

    @Test
    public void create() {
        // Verification
        assertTrue(promotion.getId() > 0);
        assertEquals(NAME, promotion.getName());
        assertEquals(START_DATE, promotion.getStartDate());
        assertEquals(END_DATE, promotion.getEndDate());
        assertEquals(TYPE, promotion.getType());
        assertEquals(VALUE, promotion.getValue());
    }

    @Test
    public void lookupById() throws Exception {
        Promotion promotion2 = new Promotion(promotion.getId());

        // Verification
        assertEquals(promotion.getId(), promotion2.getId());
        assertEquals(promotion.getPizzaStore().getId(), promotion2.getPizzaStore().getId());
        assertEquals(promotion.getName(), promotion2.getName());
        assertEquals(promotion.getStartDate(), promotion2.getStartDate());
        assertEquals(promotion.getEndDate(), promotion2.getEndDate());
        assertEquals(promotion.getType(), promotion2.getType());
        assertEquals(promotion.getValue(), promotion2.getValue());
    }

    @Test
    public void lookupByName() throws Exception {
        Promotion promotion2 = new Promotion(promotion.getPizzaStore(), promotion.getName());

        // Verification
        assertEquals(promotion.getId(), promotion2.getId());
        assertEquals(promotion.getPizzaStore().getId(), promotion2.getPizzaStore().getId());
        assertEquals(promotion.getName(), promotion2.getName());
        assertEquals(promotion.getStartDate(), promotion2.getStartDate());
        assertEquals(promotion.getEndDate(), promotion2.getEndDate());
        assertEquals(promotion.getType(), promotion2.getType());
        assertEquals(promotion.getValue(), promotion2.getValue());
    }
}
