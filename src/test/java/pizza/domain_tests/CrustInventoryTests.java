package pizza.domain_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pizza.domain.Inventory;
import pizza.domain.CrustInventory;
import org.junit.Test;
import pizza.orm.EnumCrustTypeOrm;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

import static org.junit.Assert.*;

public class CrustInventoryTests {
    private static CrustInventory crustInventory;

    private static Inventory INVENTORY;

    @BeforeClass
    public static void classSetup() throws Exception {
        TestHelper.primeDatabase();
        INVENTORY       = new Inventory(TestHelper.inventoryOrm.getId(), true);
        crustInventory  = new CrustInventory(TestHelper.thinCrustInventory.getId());
    }

    @AfterClass
    public static void classTeardown() throws Exception {
        Database.getInstance().dropAllObjects();
    }

    @Test
    public void create() throws Exception {
        // Verification
        assertTrue(crustInventory.getId() > 0);
        assertEquals(INVENTORY.getId(), crustInventory.getInventory().getId());
        assertEquals(EnumCrustTypeOrm.THIN, crustInventory.getType());
        assertEquals(5, crustInventory.getCount());
    }

    @Test
    public void lookupById() throws Exception {
        CrustInventory crustInventory2 = new CrustInventory(crustInventory.getId());

        // Verification
        assertEquals(crustInventory.getId(), crustInventory2.getId());
        assertEquals(crustInventory.getType(), crustInventory2.getType());
        assertEquals(crustInventory.getCount(), crustInventory2.getCount());
        assertEquals(crustInventory.getInventory().getId(), crustInventory2.getInventory().getId());
    }

    @Test
    public void lookupByInventoryAndType() throws Exception {
        CrustInventory crustInventory2 = new CrustInventory(INVENTORY, crustInventory.getType());

        // Verification
        assertEquals(crustInventory.getId(), crustInventory2.getId());
        assertEquals(crustInventory.getType(), crustInventory2.getType());
        assertEquals(crustInventory.getCount(), crustInventory2.getCount());
        assertEquals(crustInventory.getInventory().getId(), crustInventory2.getInventory().getId());
    }

    @Test
    public void changeInventory() throws Exception {
        crustInventory.add(5);
        assertEquals(10, crustInventory.getCount());

        crustInventory.subtract(4);
        assertEquals(6, crustInventory.getCount());
    }

    @Test
    public void exceptionOnInventoryChange() throws Exception {
        try {
            crustInventory.subtract(5000);
            fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
