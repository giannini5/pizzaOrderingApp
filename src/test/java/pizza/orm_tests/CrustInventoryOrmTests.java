package pizza.orm_tests;

import org.junit.Test;
import pizza.orm.CrustInventoryOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CrustInventoryOrmTests {
    @Test
    public void create() throws Exception {
        // Run Test
        CrustInventoryOrm crustInventory = new CrustInventoryOrm(1, 2, 3, true);

        // Verify Results
        assertTrue(crustInventory.getId() > 0);
        assertEquals(1, crustInventory.getInventoryId());
        assertEquals(2, crustInventory.getTypeId());
        assertEquals((long)3, (long)crustInventory.getCount());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        CrustInventoryOrm crustInventory = new CrustInventoryOrm(2, 2, 3, false);
        CrustInventoryOrm crustInventory2 = new CrustInventoryOrm(crustInventory.getId());

        // Verify Results
        assertEquals(crustInventory.getId(), crustInventory2.getId());
        assertEquals(crustInventory.getInventoryId(), crustInventory2.getInventoryId());
        assertEquals(crustInventory.getTypeId(), crustInventory2.getTypeId());
        assertEquals(crustInventory.getCount(), (long)crustInventory2.getCount());
    }
}

