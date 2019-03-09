package pizza.orm_tests;

import org.junit.Test;
import pizza.orm.ToppingInventoryOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ToppingInventoryOrmTests {
    @Test
    public void create() throws Exception {
        // Run Test
        ToppingInventoryOrm toppingInventory = new ToppingInventoryOrm(1, 2, 3, true);

        // Verify Results
        assertTrue(toppingInventory.getId() > 0);
        assertEquals(1, toppingInventory.getInventoryId());
        assertEquals(2, toppingInventory.getTypeId());
        assertEquals((long)3, (long)toppingInventory.getCount());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        ToppingInventoryOrm toppingInventory = new ToppingInventoryOrm(2, 2, 3, false);
        ToppingInventoryOrm toppingInventory2 = new ToppingInventoryOrm(toppingInventory.getId());

        // Verify Results
        assertEquals(toppingInventory.getId(), toppingInventory2.getId());
        assertEquals(toppingInventory.getInventoryId(), toppingInventory2.getInventoryId());
        assertEquals(toppingInventory.getTypeId(), toppingInventory2.getTypeId());
        assertEquals(toppingInventory.getCount(), (long)toppingInventory2.getCount());
    }
}

