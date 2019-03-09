package pizza.orm_tests;

import org.junit.Test;
import pizza.orm.BaseInventoryOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseInventoryOrmOrmTests {
    private static String TEST_TABLE_NAME_PREFIX = "testBaseInventory";

    @Test
    public void create() throws Exception {
        // Run Test
        BaseInventoryOrm baseInventoryOrm = new BaseInventoryOrm(TEST_TABLE_NAME_PREFIX + "CREATE", 1, 2, 3, true);

        // Verify Results
        assertTrue(baseInventoryOrm.getId() > 0);
        assertEquals(1, baseInventoryOrm.getInventoryId());
        assertEquals(2, baseInventoryOrm.getTypeId());
        assertEquals((long)3, (long) baseInventoryOrm.getCount());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        BaseInventoryOrm baseInventoryOrm = new BaseInventoryOrm(TEST_TABLE_NAME_PREFIX + "LOOKUP_BY_ID", 1, 2, 3, true);
        BaseInventoryOrm baseInventoryOrm2 = new BaseInventoryOrm(TEST_TABLE_NAME_PREFIX + "LOOKUP_BY_ID", baseInventoryOrm.getId());

        // Verify Results
        assertEquals(baseInventoryOrm.getId(), baseInventoryOrm2.getId());
        assertEquals(baseInventoryOrm.getInventoryId(), baseInventoryOrm.getInventoryId());
        assertEquals(baseInventoryOrm.getTypeId(), baseInventoryOrm.getTypeId());
        assertEquals(baseInventoryOrm.getCount(), (long) baseInventoryOrm.getCount());
    }
}

