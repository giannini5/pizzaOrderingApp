package pizza.domain_tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import pizza.domain.Inventory;
import pizza.domain.CheeseInventory;
import org.junit.Test;
import pizza.orm.EnumCheeseTypeOrm;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

import static org.junit.Assert.*;

public class CheeseInventoryTests {
    private static CheeseInventory cheeseInventory;

    private static Inventory INVENTORY;

    @BeforeClass
    public static void classSetup() throws Exception {
        TestHelper.primeDatabase();
        INVENTORY       = new Inventory(TestHelper.inventoryOrm.getId(), true);
        cheeseInventory = new CheeseInventory(TestHelper.mozzarellaInventory.getId());
    }

    @AfterClass
    public static void classTeardown() throws Exception {
        Database.getInstance().dropAllObjects();
    }

    @Test
    public void create() throws Exception {
        // Verification
        assertTrue(cheeseInventory.getId() > 0);
        assertEquals(INVENTORY.getId(), cheeseInventory.getInventory().getId());
        assertEquals(EnumCheeseTypeOrm.MOZZARELLA, cheeseInventory.getType());
        assertEquals(5, cheeseInventory.getCount());
    }

    @Test
    public void lookupById() throws Exception {
        CheeseInventory cheeseInventory2 = new CheeseInventory(cheeseInventory.getId());

        // Verification
        assertEquals(cheeseInventory.getId(), cheeseInventory2.getId());
        assertEquals(cheeseInventory.getType(), cheeseInventory2.getType());
        assertEquals(cheeseInventory.getCount(), cheeseInventory2.getCount());
        assertEquals(cheeseInventory.getInventory().getId(), cheeseInventory2.getInventory().getId());
    }

    @Test
    public void lookupByInventoryAndType() throws Exception {
        CheeseInventory cheeseInventory2 = new CheeseInventory(INVENTORY, cheeseInventory.getType());

        // Verification
        assertEquals(cheeseInventory.getId(), cheeseInventory2.getId());
        assertEquals(cheeseInventory.getType(), cheeseInventory2.getType());
        assertEquals(cheeseInventory.getCount(), cheeseInventory2.getCount());
        assertEquals(cheeseInventory.getInventory().getId(), cheeseInventory2.getInventory().getId());
    }

    @Test
    public void changeInventory() throws Exception {
        cheeseInventory.add(5);
        assertEquals(10, cheeseInventory.getCount());

        cheeseInventory.subtract(4);
        assertEquals(6, cheeseInventory.getCount());
    }

    @Test
    public void exceptionOnInventoryChange() throws Exception {
        try {
            cheeseInventory.subtract(5000);
            fail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
