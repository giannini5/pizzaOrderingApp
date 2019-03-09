package pizza.domain_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pizza.domain.Inventory;
import pizza.domain.SauceInventory;
import org.junit.Test;
import pizza.orm.EnumSauceTypeOrm;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

import static org.junit.Assert.*;

public class SauceInventoryTests {
    private static SauceInventory sauceInventory;

    private static Inventory INVENTORY;

    @BeforeClass
    public static void classSetup() throws Exception {
        TestHelper.primeDatabase();
        INVENTORY       = new Inventory(TestHelper.inventoryOrm.getId(), true);
        sauceInventory  = new SauceInventory(TestHelper.marinaraInventory.getId());
    }

    @AfterClass
    public static void classTeardown() throws Exception {
        Database.getInstance().dropAllObjects();
    }

    @Test
    public void create() throws Exception {
        // Verification
        assertTrue(sauceInventory.getId() > 0);
        assertEquals(INVENTORY.getId(), sauceInventory.getInventory().getId());
        assertEquals(EnumSauceTypeOrm.MARINARA, sauceInventory.getType());
        assertEquals(5, sauceInventory.getCount());
    }

    @Test
    public void lookupById() throws Exception {
        SauceInventory sauceInventory2 = new SauceInventory(sauceInventory.getId());

        // Verification
        assertEquals(sauceInventory.getId(), sauceInventory2.getId());
        assertEquals(sauceInventory.getType(), sauceInventory2.getType());
        assertEquals(sauceInventory.getCount(), sauceInventory2.getCount());
        assertEquals(sauceInventory.getInventory().getId(), sauceInventory2.getInventory().getId());
    }

    @Test
    public void lookupByInventoryAndType() throws Exception {
        SauceInventory sauceInventory2 = new SauceInventory(INVENTORY, sauceInventory.getType());

        // Verification
        assertEquals(sauceInventory.getId(), sauceInventory2.getId());
        assertEquals(sauceInventory.getType(), sauceInventory2.getType());
        assertEquals(sauceInventory.getCount(), sauceInventory2.getCount());
        assertEquals(sauceInventory.getInventory().getId(), sauceInventory2.getInventory().getId());
    }

    @Test
    public void changeInventory() throws Exception {
        sauceInventory.add(5);
        assertEquals(10, sauceInventory.getCount());

        sauceInventory.subtract(4);
        assertEquals(6, sauceInventory.getCount());
    }

    @Test
    public void exceptionOnInventoryChange() throws Exception {
        try {
            sauceInventory.subtract(5000);
            fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
