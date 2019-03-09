package pizza.orm_tests;

import org.junit.BeforeClass;
import org.junit.Test;
import pizza.orm.PromotionOrm;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PromotionOrmTests {
    private static PromotionOrm promotion;

    private static long   PIZZA_STORE_ID            = 5;
    private static String NAME                      = "Buy one Get one Free";
    private static String START_DATE                = "2019-03-15";
    private static String END_DATE                  = "2019-04-15";
    private static long   ENUM_PROMOTION_TYPE_ID    = 1;
    private static int    VALUE                     = 10;

    @BeforeClass
    public static void classSetup() throws Exception {
        Date startDate = Date.valueOf(START_DATE);
        Date endDate   = Date.valueOf(END_DATE);
        promotion = new PromotionOrm(PIZZA_STORE_ID, NAME, startDate, endDate, ENUM_PROMOTION_TYPE_ID, VALUE, true);
    }

    @Test
    public void create() throws Exception {
        // Verify Results
        Date startDate = Date.valueOf(START_DATE);
        Date endDate   = Date.valueOf(END_DATE);

        assertTrue(promotion.getId() > 0);
        assertEquals(PIZZA_STORE_ID, promotion.getPizzaStoreId());
        assertEquals(NAME, promotion.getName());
        assertEquals(startDate, promotion.getStartDate());
        assertEquals(endDate, promotion.getEndDate());
        assertEquals(ENUM_PROMOTION_TYPE_ID, promotion.getEnumPromotionTypeId());
        assertEquals(VALUE, promotion.getValue());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        PromotionOrm promotion2 = new PromotionOrm(promotion.getId());

        // Verify Results
        assertEquals(promotion.getId(), promotion2.getId());
        assertEquals(promotion.getPizzaStoreId(), promotion2.getPizzaStoreId());
        assertEquals(promotion.getName(), promotion2.getName());
        assertEquals(promotion.getStartDate(), promotion2.getStartDate());
        assertEquals(promotion.getEndDate(), promotion2.getEndDate());
        assertEquals(promotion.getEnumPromotionTypeId(), promotion2.getEnumPromotionTypeId());
        assertEquals(promotion.getValue(), promotion2.getValue());
    }
}

