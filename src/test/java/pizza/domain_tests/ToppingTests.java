package pizza.domain_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pizza.domain.Pizza;
import pizza.domain.Topping;
import org.junit.Test;
import pizza.orm.*;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ToppingTests {
    private static Topping              topping;
    private static Pizza                PIZZA;
    private static EnumToppingTypeOrm   TOPPING_TYPE;

    @BeforeClass
    public static void classSetup() throws Exception {
        TestHelper.primeDatabase();
        PIZZA           = new Pizza(TestHelper.pizzaOrm.getId());
        TOPPING_TYPE    = TestHelper.mushrooms;

        topping = new Topping(PIZZA, TOPPING_TYPE.getType());
    }

    @AfterClass
    public static void classTeardown() throws Exception {
        Database.getInstance().dropAllObjects();
    }

    @Test
    public void create() {
        // Verification
        assertTrue(topping.getId() > 0);
        assertEquals(PIZZA.getId(), topping.getPizza().getId());
        assertEquals(TOPPING_TYPE.getType(), topping.getToppingType());
        assertEquals(TOPPING_TYPE.getAmountInCents(), topping.getAmountInCents());
    }

    @Test
    public void lookupById() throws Exception {
        Topping topping2 = new Topping(topping.getId());

        // Verification
        assertEquals(topping.getId(), topping2.getId());
        assertEquals(topping.getAmountInCents(), topping2.getAmountInCents());
        assertEquals(topping.getPizza().getId(), topping2.getPizza().getId());
        assertEquals(topping.getToppingType(), topping2.getToppingType());
    }
}
