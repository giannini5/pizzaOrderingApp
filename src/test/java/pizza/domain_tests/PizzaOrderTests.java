package pizza.domain_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pizza.domain.Pizza;
import pizza.domain.PizzaStore;
import pizza.domain.PizzaOrder;
import org.junit.Test;
import pizza.domain.Promotion;
import pizza.orm.*;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PizzaOrderTests {
    private static PizzaOrder pizzaOrder;

    private static String     NAME          = "David Giannini";
    private static String     ADDRESS       = "2910";
    private static String     PHONE         = "123";
    private static Date       ORDER_DATE    = Date.valueOf("2019-03-06");
    private static Promotion  PROMOTION;
    private static PizzaStore PIZZA_STORE;

    @BeforeClass
    public static void classSetup() throws Exception {
        TestHelper.primeDatabase();
        PROMOTION   = new Promotion(TestHelper.promotionOrm.getId());
        PIZZA_STORE = new PizzaStore(TestHelper.store.getId());

        pizzaOrder = new PizzaOrder(PIZZA_STORE, PROMOTION, ORDER_DATE, NAME, ADDRESS, PHONE);
    }

    @AfterClass
    public static void classTeardown() throws Exception {
        Database.getInstance().dropAllObjects();
    }

    @Test
    public void create() {
        // Verification
        assertTrue(pizzaOrder.getId() > 0);
        assertEquals(NAME, pizzaOrder.getName());
        assertEquals(ADDRESS, pizzaOrder.getAddress());
        assertEquals(PHONE, pizzaOrder.getPhone());
        assertEquals(ORDER_DATE, pizzaOrder.getOrderDate());
        assertEquals(PROMOTION.getId(), pizzaOrder.getPromotion().getId());
        assertEquals(PIZZA_STORE.getId(), pizzaOrder.getPizzaStore().getId());
    }

    @Test
    public void lookupById() throws Exception {
        PizzaOrder pizzaOrder2 = new PizzaOrder(pizzaOrder.getId());

        // Verification
        assertEquals(pizzaOrder.getId(), pizzaOrder2.getId());
        assertEquals(pizzaOrder.getPizzaStore().getId(), pizzaOrder2.getPizzaStore().getId());
        assertEquals(pizzaOrder.getPromotion().getId(), pizzaOrder2.getPromotion().getId());
        assertEquals(pizzaOrder.getName(), pizzaOrder2.getName());
        assertEquals(pizzaOrder.getAddress(), pizzaOrder2.getAddress());
        assertEquals(pizzaOrder.getPhone(), pizzaOrder2.getPhone());
        assertEquals(pizzaOrder.getOrderDate(), pizzaOrder2.getOrderDate());
    }

    @Test
    public void getCost() throws Exception {
        Pizza pizza = new Pizza(pizzaOrder, "Cheese", 450, EnumCrustTypeOrm.THICK, EnumSauceTypeOrm.MARINARA, EnumCheeseTypeOrm.MOZZARELLA);

        // Verification
        assertEquals(498, pizzaOrder.getCost());
    }
}
