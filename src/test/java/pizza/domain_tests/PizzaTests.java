package pizza.domain_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pizza.domain.PizzaOrder;
import pizza.domain.PizzaStore;
import pizza.domain.Pizza;
import org.junit.Test;
import pizza.domain.Promotion;
import pizza.orm.*;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PizzaTests {
    private static Pizza pizza;

    private static PizzaOrder PIZZA_ORDER;
    private static String     NAME              = "Cheese";
    private static int        AMOUNT_IN_CENTS   = 500;
    private static String     CRUST_TYPE        = EnumCrustTypeOrm.THICK;
    private static String     SAUCE_TYPE        = EnumSauceTypeOrm.MARINARA;
    private static String     CHEESE_TYPE       = EnumCheeseTypeOrm.MOZZARELLA;

    @BeforeClass
    public static void classSetup() throws Exception {
        TestHelper.primeDatabase();
        PIZZA_ORDER = new PizzaOrder(TestHelper.pizzaOrderOrm.getId());

        pizza = new Pizza(PIZZA_ORDER, NAME, AMOUNT_IN_CENTS, CRUST_TYPE, SAUCE_TYPE, CHEESE_TYPE);
    }

    @AfterClass
    public static void classTeardown() throws Exception {
        Database.getInstance().dropAllObjects();
    }

    @Test
    public void create() {
        // Verification
        assertTrue(pizza.getId() > 0);
        assertEquals(PIZZA_ORDER.getId(), pizza.getPizzaOrder().getId());
        assertEquals(NAME, pizza.getName());
        assertEquals(AMOUNT_IN_CENTS, pizza.getAmountInCents());
        assertEquals(CRUST_TYPE, pizza.getCrustType());
        assertEquals(SAUCE_TYPE, pizza.getSauceType());
        assertEquals(CHEESE_TYPE, pizza.getCheeseType());
    }

    @Test
    public void lookupById() throws Exception {
        Pizza pizza2 = new Pizza(pizza.getId());

        // Verification
        assertEquals(pizza.getId(), pizza2.getId());
        assertEquals(pizza.getPizzaOrder().getId(), pizza2.getPizzaOrder().getId());
        assertEquals(pizza.getName(), pizza2.getName());
        assertEquals(pizza.getAmountInCents(), pizza2.getAmountInCents());
        assertEquals(pizza.getCrustType(), pizza2.getCrustType());
        assertEquals(pizza.getSauceType(), pizza2.getSauceType());
        assertEquals(pizza.getCheeseType(), pizza2.getCheeseType());
    }

    @Test
    public void getCost() throws Exception {
        // Verification
        assertEquals(550, pizza.getCost());
    }
}
