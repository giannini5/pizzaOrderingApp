package pizza.domain_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pizza.domain.PizzaStore;
import pizza.domain.Inventory;
import org.junit.Test;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InventoryTests {
    private static Inventory inventory;

    private static PizzaStore PIZZA_STORE;

    @BeforeClass
    public static void classSetup() throws Exception {
        TestHelper.primeDatabase();
        PIZZA_STORE = new PizzaStore(TestHelper.store.getId());
        inventory = new Inventory(PIZZA_STORE.getId(), false);
    }

    @AfterClass
    public static void classTeardown() throws Exception {
        Database.getInstance().dropAllObjects();
    }

    @Test
    public void create() throws Exception {
        Inventory createInventory = new Inventory(PIZZA_STORE);

        // Verification
        assertTrue(createInventory.getId() > 0);
        assertEquals(PIZZA_STORE.getId(), createInventory.getPizzaStore().getId());
    }

    @Test
    public void lookupById() throws Exception {
        Inventory inventory2 = new Inventory(inventory.getId(), true);

        // Verification
        assertEquals(inventory.getId(), inventory2.getId());
        assertEquals(inventory.getPizzaStore().getId(), inventory2.getPizzaStore().getId());
    }

    @Test
    public void lookupByPizzaStoreId() throws Exception {
        Inventory inventory2 = new Inventory(PIZZA_STORE.getId(), false);

        // Verification
        assertEquals(inventory.getId(), inventory2.getId());
        assertEquals(inventory.getPizzaStore().getId(), inventory2.getPizzaStore().getId());
    }
}
