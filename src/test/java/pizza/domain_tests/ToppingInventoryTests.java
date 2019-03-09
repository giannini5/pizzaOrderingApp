package pizza.domain_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pizza.domain.Inventory;
import pizza.domain.ToppingInventory;
import org.junit.Test;
import pizza.orm.EnumToppingTypeOrm;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

import static org.junit.Assert.*;

public class ToppingInventoryTests {
    private static ToppingInventory toppingInventory;

    private static Inventory INVENTORY;

    @BeforeClass
    public static void classSetup() throws Exception {
        TestHelper.primeDatabase();
        INVENTORY           = new Inventory(TestHelper.inventoryOrm.getId(), true);
        toppingInventory    = new ToppingInventory(TestHelper.pepperoniInventory.getId());
    }

    @AfterClass
    public static void classTeardown() throws Exception {
        Database.getInstance().dropAllObjects();
    }

    @Test
    public void create() throws Exception {
        // Verification
        assertTrue(toppingInventory.getId() > 0);
        assertEquals(INVENTORY.getId(), toppingInventory.getInventory().getId());
        assertEquals(EnumToppingTypeOrm.PEPPERONI, toppingInventory.getType());
        assertEquals(5, toppingInventory.getCount());
    }

    @Test
    public void lookupById() throws Exception {
        ToppingInventory toppingInventory2 = new ToppingInventory(toppingInventory.getId());

        // Verification
        assertEquals(toppingInventory.getId(), toppingInventory2.getId());
        assertEquals(toppingInventory.getType(), toppingInventory2.getType());
        assertEquals(toppingInventory.getCount(), toppingInventory2.getCount());
        assertEquals(toppingInventory.getInventory().getId(), toppingInventory2.getInventory().getId());
    }

    @Test
    public void lookupByInventoryAndType() throws Exception {
        ToppingInventory toppingInventory2 = new ToppingInventory(INVENTORY, toppingInventory.getType());

        // Verification
        assertEquals(toppingInventory.getId(), toppingInventory2.getId());
        assertEquals(toppingInventory.getType(), toppingInventory2.getType());
        assertEquals(toppingInventory.getCount(), toppingInventory2.getCount());
        assertEquals(toppingInventory.getInventory().getId(), toppingInventory2.getInventory().getId());
    }

    @Test
    public void changeInventory() throws Exception {
        toppingInventory.add(5);
        assertEquals(10, toppingInventory.getCount());

        toppingInventory.subtract(4);
        assertEquals(6, toppingInventory.getCount());
    }

    @Test
    public void exceptionOnInventoryChange() throws Exception {
        try {
            toppingInventory.subtract(5000);
            fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
