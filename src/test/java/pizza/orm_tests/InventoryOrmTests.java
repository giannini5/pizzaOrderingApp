package pizza.orm_tests;

import org.junit.BeforeClass;
import org.junit.Test;
import pizza.orm.InventoryOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InventoryOrmTests {
    private static InventoryOrm inventory;

    @BeforeClass
    public static void classSetup() throws Exception {
        inventory = new InventoryOrm((long)1, true);
    }

    @Test
    public void create() throws Exception {
        // Verify Results
        assertTrue(inventory.getId() > 0);
        assertEquals(1, inventory.getPizzaStoreId());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        InventoryOrm inventory2 = new InventoryOrm(true, inventory.getId());

        // Verify Results
        assertEquals(inventory.getId(), inventory2.getId());
        assertEquals(inventory.getPizzaStoreId(), inventory2.getPizzaStoreId());
    }

    @Test
    public void lookupByPizzaStoreId() throws Exception {
        // Run Test
        InventoryOrm inventory2 = new InventoryOrm(false, inventory.getPizzaStoreId());

        // Verify Results
        assertEquals(inventory.getId(), inventory2.getId());
        assertEquals(inventory.getPizzaStoreId(), inventory2.getPizzaStoreId());
    }
}

