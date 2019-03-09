package pizza.orm_tests;

import org.junit.Test;
import pizza.orm.SauceInventoryOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SauceInventoryOrmTests {
    @Test
    public void create() throws Exception {
        // Run Test
        SauceInventoryOrm sauceInventory = new SauceInventoryOrm(1, 2, 3, true);

        // Verify Results
        assertTrue(sauceInventory.getId() > 0);
        assertEquals(1, sauceInventory.getInventoryId());
        assertEquals(2, sauceInventory.getTypeId());
        assertEquals((long)3, (long)sauceInventory.getCount());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        SauceInventoryOrm sauceInventory = new SauceInventoryOrm(2, 2, 3, false);
        SauceInventoryOrm sauceInventory2 = new SauceInventoryOrm(sauceInventory.getId());

        // Verify Results
        assertEquals(sauceInventory.getId(), sauceInventory2.getId());
        assertEquals(sauceInventory.getInventoryId(), sauceInventory2.getInventoryId());
        assertEquals(sauceInventory.getTypeId(), sauceInventory2.getTypeId());
        assertEquals(sauceInventory.getCount(), (long)sauceInventory2.getCount());
    }
}

