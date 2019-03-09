package pizza.orm_tests;

import org.junit.BeforeClass;
import org.junit.Test;
import pizza.orm.PizzaStoreOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PizzaStoreOrmTests {
    private static PizzaStoreOrm pizzaStore;

    private static String STORE_NAME    = "Pizza Bobs";
    private static String STORE_ADDRESS = "Embarcadero del Mar";

    @BeforeClass
    public static void classSetup() throws Exception {
        pizzaStore = new PizzaStoreOrm(STORE_NAME, STORE_ADDRESS, true);
    }

    @Test
    public void create() throws Exception {
        // Verify Results
        assertTrue(pizzaStore.getId() > 0);
        assertEquals(STORE_NAME, pizzaStore.getName());
        assertEquals(STORE_ADDRESS, pizzaStore.getAddress());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        PizzaStoreOrm pizzaStore2 = new PizzaStoreOrm(pizzaStore.getId());

        // Verify Results
        assertEquals(pizzaStore.getId(), pizzaStore2.getId());
        assertEquals(pizzaStore.getName(), pizzaStore2.getName());
        assertEquals(pizzaStore.getAddress(), pizzaStore2.getAddress());
    }
}

