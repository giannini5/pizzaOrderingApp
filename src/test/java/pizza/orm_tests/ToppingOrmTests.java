package pizza.orm_tests;

import org.junit.BeforeClass;
import org.junit.Test;
import pizza.orm.ToppingOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ToppingOrmTests {
    private static ToppingOrm topping;

    @BeforeClass
    public static void classSetup() throws Exception {
        topping = new ToppingOrm(1, 2, true);
    }

    @Test
    public void create() throws Exception {
        // Verify Results
        assertTrue(topping.getId() > 0);
        assertEquals(1, topping.getPizzaId());
        assertEquals(2, topping.getEnumTopplingId());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        ToppingOrm topping2 = new ToppingOrm(topping.getId());

        // Verify Results
        assertEquals(topping.getId(), topping2.getId());
        assertEquals(topping.getPizzaId(), topping2.getPizzaId());
        assertEquals(topping.getEnumTopplingId(), topping2.getEnumTopplingId());
    }
}

