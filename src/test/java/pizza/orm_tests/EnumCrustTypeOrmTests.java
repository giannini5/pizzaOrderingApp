package pizza.orm_tests;

import org.junit.BeforeClass;
import org.junit.Test;
import pizza.orm.EnumCrustTypeOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnumCrustTypeOrmTests {
    private static EnumCrustTypeOrm enumCrustType;

    @BeforeClass
    public static void classSetup() throws Exception {
        // Class Setup
        enumCrustType = new EnumCrustTypeOrm(EnumCrustTypeOrm.THICK, 2, true);
    }

    @Test
    public void create() throws Exception {
        // Verify Results
        assertTrue(enumCrustType.getId() > 0);
        assertEquals(EnumCrustTypeOrm.THICK, enumCrustType.getType());
        assertEquals((long)2, (long)enumCrustType.getAmountInCents());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        EnumCrustTypeOrm enumCrustType2 = new EnumCrustTypeOrm(enumCrustType.getId());

        // Verify Results
        assertEquals(enumCrustType.getId(), enumCrustType2.getId());
        assertEquals(enumCrustType.getType(), enumCrustType.getType());
        assertEquals(enumCrustType.getAmountInCents(), enumCrustType.getAmountInCents());
    }
}

