package pizza.orm_tests;

import org.junit.Test;
import pizza.orm.CheeseInventoryOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheeseInventoryOrmTests {
    @Test
    public void create() throws Exception {
        // Run Test
        CheeseInventoryOrm cheeseInventory = new CheeseInventoryOrm(1, 2, 3, true);

        // Verify Results
        assertTrue(cheeseInventory.getId() > 0);
        assertEquals(1, cheeseInventory.getInventoryId());
        assertEquals(2, cheeseInventory.getTypeId());
        assertEquals((long)3, (long)cheeseInventory.getCount());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        CheeseInventoryOrm cheeseInventory = new CheeseInventoryOrm(2, 2, 3, false);
        CheeseInventoryOrm cheeseInventory2 = new CheeseInventoryOrm(cheeseInventory.getId());

        // Verify Results
        assertEquals(cheeseInventory.getId(), cheeseInventory2.getId());
        assertEquals(cheeseInventory.getInventoryId(), cheeseInventory2.getInventoryId());
        assertEquals(cheeseInventory.getTypeId(), cheeseInventory2.getTypeId());
        assertEquals(cheeseInventory.getCount(), (long)cheeseInventory2.getCount());
    }
}

