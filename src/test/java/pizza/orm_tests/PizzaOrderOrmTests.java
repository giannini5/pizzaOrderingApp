package pizza.orm_tests;

import org.junit.BeforeClass;
import org.junit.Test;
import pizza.orm.PizzaOrderOrm;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PizzaOrderOrmTests {
    private static PizzaOrderOrm pizza;

    private static long   PIZZA_STORE_ID    = 1;
    private static long   PROMOTION_ID      = 2;
    private static String ORDER_DATE        = "2019-03-05";
    private static String NAME              = "David Giannini";
    private static String ADDRESS           = "2910 Paseo del Refugio, Santa Barbara, CA 93105";
    private static String PHONE             = "805.252.3944";

    @BeforeClass
    public static void classSetup() throws Exception {
        Date orderDate = Date.valueOf(ORDER_DATE);
        pizza = new PizzaOrderOrm(PIZZA_STORE_ID, PROMOTION_ID, orderDate, NAME, ADDRESS, PHONE, true);
    }

    @Test
    public void create() throws Exception {
        // Verify Results
        assertTrue(pizza.getId() > 0);
        assertEquals(PIZZA_STORE_ID, pizza.getPizzaStoreId());
        assertEquals(PROMOTION_ID, pizza.getPromotionId());
        assertEquals(ORDER_DATE, pizza.getOrderDate().toString());
        assertEquals(NAME, pizza.getName());
        assertEquals(ADDRESS, pizza.getAddress());
        assertEquals(PHONE, pizza.getPhone());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        PizzaOrderOrm pizza2 = new PizzaOrderOrm(pizza.getId());

        // Verify Results
        assertEquals(pizza.getId(), pizza2.getId());
        assertEquals(pizza.getPizzaStoreId(), pizza2.getPizzaStoreId());
        assertEquals(pizza.getPromotionId(), pizza2.getPromotionId());
        assertEquals(pizza.getOrderDate(), pizza2.getOrderDate());
        assertEquals(pizza.getName(), pizza2.getName());
        assertEquals(pizza.getAddress(), pizza2.getAddress());
        assertEquals(pizza.getPhone(), pizza2.getPhone());
    }
}

