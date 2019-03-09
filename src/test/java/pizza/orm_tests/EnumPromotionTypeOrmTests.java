package pizza.orm_tests;

import org.junit.BeforeClass;
import org.junit.Test;
import pizza.orm.EnumPromotionTypeOrm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnumPromotionTypeOrmTests {
    private static EnumPromotionTypeOrm enumPromotionType;

    @BeforeClass
    public static void classSetup() throws Exception {
        // Class Setup
        enumPromotionType = new EnumPromotionTypeOrm(EnumPromotionTypeOrm.DOLLARS_OFF, true);
    }

    @Test
    public void create() throws Exception {
        // Verify Results
        assertTrue(enumPromotionType.getId() > 0);
        assertEquals(EnumPromotionTypeOrm.DOLLARS_OFF, enumPromotionType.getType());
        assertEquals((long)0, (long)enumPromotionType.getAmountInCents());
    }

    @Test
    public void lookupById() throws Exception {
        // Run Test
        EnumPromotionTypeOrm enumPromotionType2 = new EnumPromotionTypeOrm(enumPromotionType.getId());

        // Verify Results
        assertEquals(enumPromotionType.getId(), enumPromotionType2.getId());
        assertEquals(enumPromotionType.getType(), enumPromotionType.getType());
        assertEquals(enumPromotionType.getAmountInCents(), enumPromotionType.getAmountInCents());
    }
}

