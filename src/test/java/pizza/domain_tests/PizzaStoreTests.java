package pizza.domain_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pizza.domain.PizzaStore;
import org.junit.Test;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PizzaStoreTests {
    private static PizzaStore pizzaStore;

    private static String NAME      = "Dave Pizza";
    private static String ADDRESS   = "Waterloo, CA";

    @BeforeClass
    public static void classSetup() throws Exception {
        TestHelper.primeDatabase();
        pizzaStore = new PizzaStore(NAME, ADDRESS);
    }

    @AfterClass
    public static void classTeardown() throws Exception {
        Database.getInstance().dropAllObjects();
    }

    @Test
    public void create() {
        // Verification
        assertTrue(pizzaStore.getId() > 0);
        assertEquals(NAME, pizzaStore.getName());
        assertEquals(ADDRESS, pizzaStore.getAddress());
    }
}
