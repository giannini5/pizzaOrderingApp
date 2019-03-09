package pizza.orm_tests;

import org.junit.BeforeClass;
import org.junit.Test;
import pizza.orm.EnumSauceTypeOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnumSauceTypeOrmTests {
    private static EnumSauceTypeOrm enumSauceType;

    @BeforeClass
    public static void classSetup() throws Exception {
        // Class Setup
        enumSauceType = new EnumSauceTypeOrm(EnumSauceTypeOrm.MARINARA, 2, true);
    }

    @Test
    public void create() throws Exception {
        // Verify Results
        assertTrue(enumSauceType.getId() > 0);
        assertEquals(EnumSauceTypeOrm.MARINARA, enumSauceType.getType());
        assertEquals((long)2, (long)enumSauceType.getAmountInCents());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        EnumSauceTypeOrm enumSauceType2 = new EnumSauceTypeOrm(enumSauceType.getId());

        // Verify Results
        assertEquals(enumSauceType.getId(), enumSauceType2.getId());
        assertEquals(enumSauceType.getType(), enumSauceType.getType());
        assertEquals(enumSauceType.getAmountInCents(), enumSauceType.getAmountInCents());
    }
}

