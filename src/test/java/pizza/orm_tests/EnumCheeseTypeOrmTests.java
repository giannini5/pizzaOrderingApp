package pizza.orm_tests;

import org.junit.BeforeClass;
import org.junit.Test;
import pizza.orm.EnumCheeseTypeOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnumCheeseTypeOrmTests {
    private static EnumCheeseTypeOrm enumCheeseType;

    @BeforeClass
    public static void classSetup() throws Exception {
        // Class Setup
        enumCheeseType = new EnumCheeseTypeOrm(EnumCheeseTypeOrm.MOZZARELLA, 2, true);
    }

    @Test
    public void create() throws Exception {
        // Verify Results
        assertTrue(enumCheeseType.getId() > 0);
        assertEquals(EnumCheeseTypeOrm.MOZZARELLA, enumCheeseType.getType());
        assertEquals((long)2, (long)enumCheeseType.getAmountInCents());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        EnumCheeseTypeOrm enumCheeseType2 = new EnumCheeseTypeOrm(enumCheeseType.getId());

        // Verify Results
        assertEquals(enumCheeseType.getId(), enumCheeseType2.getId());
        assertEquals(enumCheeseType.getType(), enumCheeseType.getType());
        assertEquals(enumCheeseType.getAmountInCents(), enumCheeseType.getAmountInCents());
    }
}

