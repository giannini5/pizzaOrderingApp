package pizza.orm_tests;

import org.junit.BeforeClass;
import org.junit.Test;
import pizza.orm.EnumToppingTypeOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnumToppingTypeOrmTests {
    private static EnumToppingTypeOrm enumToppingType;

    @BeforeClass
    public static void classSetup() throws Exception {
        // Class Setup
        enumToppingType = new EnumToppingTypeOrm(EnumToppingTypeOrm.PEPPERONI, 2, true);
    }

    @Test
    public void create() throws Exception {
        // Verify Results
        assertTrue(enumToppingType.getId() > 0);
        assertEquals(EnumToppingTypeOrm.PEPPERONI, enumToppingType.getType());
        assertEquals((long)2, (long)enumToppingType.getAmountInCents());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        EnumToppingTypeOrm enumToppingType2 = new EnumToppingTypeOrm(enumToppingType.getId());

        // Verify Results
        assertEquals(enumToppingType.getId(), enumToppingType2.getId());
        assertEquals(enumToppingType.getType(), enumToppingType.getType());
        assertEquals(enumToppingType.getAmountInCents(), enumToppingType.getAmountInCents());
    }
}

