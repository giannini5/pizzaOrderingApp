package pizza.orm_tests;

import org.junit.Test;
import pizza.orm.BaseEnumTypeOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseEnumTypeOrmTests {
    private static String TEST_TABLE_NAME_PREFIX = "testBaseEnum";

    @Test
    public void create() throws Exception {
        // Run Test
        BaseEnumTypeOrm baseEnumTypeOrm = new BaseEnumTypeOrm(TEST_TABLE_NAME_PREFIX + "CREATE", "testCreate", 2, true);

        // Verify Results
        assertTrue(baseEnumTypeOrm.getId() > 0);
        assertEquals("testCreate", baseEnumTypeOrm.getType());
        assertEquals((long)2, (long) baseEnumTypeOrm.getAmountInCents());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        BaseEnumTypeOrm baseEnumTypeOrm = new BaseEnumTypeOrm(TEST_TABLE_NAME_PREFIX + "LOOKUP_BY_ID", "testLookupById", 2, true);
        BaseEnumTypeOrm baseEnumTypeOrm2 = new BaseEnumTypeOrm(TEST_TABLE_NAME_PREFIX + "LOOKUP_BY_ID", baseEnumTypeOrm.getId());

        // Verify Results
        assertEquals(baseEnumTypeOrm.getId(), baseEnumTypeOrm2.getId());
        assertEquals("testLookupById", baseEnumTypeOrm2.getType());
        assertEquals((long)2, (long) baseEnumTypeOrm.getAmountInCents());
    }
}

