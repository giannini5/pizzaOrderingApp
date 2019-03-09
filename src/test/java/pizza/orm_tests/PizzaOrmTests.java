package pizza.orm_tests;

import org.junit.BeforeClass;
import org.junit.Test;
import pizza.orm.PizzaOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PizzaOrmTests {
    private static PizzaOrm pizza;

    private static String PIZZA_NAME        = "Cheese";
    private static int    AMOUNT_IN_CENTS   = 500;
    private static long   PIZZA_ORDER_ID    = 1;
    private static long   ENUM_CRUST_ID     = 2;
    private static long   ENUM_SAUCE_ID     = 3;
    private static long   ENUM_CHEESE_ID    = 4;

    @BeforeClass
    public static void classSetup() throws Exception {
        pizza = new PizzaOrm(PIZZA_NAME, AMOUNT_IN_CENTS, PIZZA_ORDER_ID, ENUM_CRUST_ID, ENUM_SAUCE_ID, ENUM_CHEESE_ID,
                true);
    }

    @Test
    public void create() throws Exception {
        // Verify Results
        assertTrue(pizza.getId() > 0);
        assertEquals(PIZZA_NAME, pizza.getName());
        assertEquals(AMOUNT_IN_CENTS, pizza.getAmountInCents());
        assertEquals(PIZZA_ORDER_ID, pizza.getPizzaOrderId());
        assertEquals(ENUM_CRUST_ID, pizza.getEnumCrustId());
        assertEquals(ENUM_SAUCE_ID, pizza.getEnumSauceId());
        assertEquals(ENUM_CHEESE_ID, pizza.getEnumCheeseId());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        PizzaOrm pizza2 = new PizzaOrm(pizza.getId());

        // Verify Results
        assertEquals(pizza.getId(), pizza2.getId());
        assertEquals(pizza.getName(), pizza2.getName());
        assertEquals(pizza.getAmountInCents(), pizza2.getAmountInCents());
        assertEquals(pizza.getPizzaOrderId(), pizza2.getPizzaOrderId());
        assertEquals(pizza.getEnumCrustId(), pizza2.getEnumCrustId());
        assertEquals(pizza.getEnumSauceId(), pizza2.getEnumSauceId());
        assertEquals(pizza.getEnumCheeseId(), pizza2.getEnumCheeseId());
    }
}

