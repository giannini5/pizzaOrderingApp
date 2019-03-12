package pizza.service.database;

import pizza.orm.*;

import java.sql.Date;

public class TestHelper {
    public static EnumPromotionTypeOrm percentOff;
    public static EnumPromotionTypeOrm dollarsOff;

    public static EnumCrustTypeOrm thinCrust;
    public static EnumCrustTypeOrm thickCrust;
    public static EnumCheeseTypeOrm mozzarella;
    public static EnumSauceTypeOrm marinara;
    public static EnumToppingTypeOrm pepperoni;
    public static EnumToppingTypeOrm mushrooms;

    public static PizzaStoreOrm store;

    public static InventoryOrm inventoryOrm;
    public static CrustInventoryOrm thinCrustInventory;
    public static CrustInventoryOrm thickCrustInventory;
    public static CheeseInventoryOrm mozzarellaInventory;
    public static SauceInventoryOrm marinaraInventory;
    public static ToppingInventoryOrm pepperoniInventory;
    public static ToppingInventoryOrm mushroomsInventory;

    public static PromotionOrm promotionOrm;
    public static PizzaOrderOrm pizzaOrderOrm;
    public static PizzaOrm pizzaOrm;
    public static ToppingOrm toppingOrm;

    public static void primeDatabase() throws Exception {
        // Drop all objects and create new ones
        Database.getInstance().dropAllObjects();

        // Create enum tables
        dollarsOff = new EnumPromotionTypeOrm(EnumPromotionTypeOrm.DOLLARS_OFF, true);
        percentOff = new EnumPromotionTypeOrm(EnumPromotionTypeOrm.PERCENT_OFF, false);

        thinCrust  = new EnumCrustTypeOrm(EnumCrustTypeOrm.THIN, 0, true);
        thickCrust = new EnumCrustTypeOrm(EnumCrustTypeOrm.THICK, 50, false);
        mozzarella = new EnumCheeseTypeOrm(EnumCheeseTypeOrm.MOZZARELLA, 0, true);
        marinara   = new EnumSauceTypeOrm(EnumSauceTypeOrm.MARINARA, 0, true);
        pepperoni  = new EnumToppingTypeOrm(EnumToppingTypeOrm.PEPPERONI, 150, true);
        mushrooms  = new EnumToppingTypeOrm(EnumToppingTypeOrm.MUSHROOMS, 150, false);

        // Create a new Pizza Store
        store = new PizzaStoreOrm("Daves Pizza", "2910 Paseo del Refugio", true);

        // Create inventoryOrm for store
        inventoryOrm        = new InventoryOrm(store.getId(), true);
        thinCrustInventory  = new CrustInventoryOrm((long) inventoryOrm.getId(), (int)thinCrust.getId(), 5, true);
        thickCrustInventory = new CrustInventoryOrm((long) inventoryOrm.getId(), (int)thickCrust.getId(), 5, false);
        mozzarellaInventory = new CheeseInventoryOrm((long) inventoryOrm.getId(), (int)mozzarella.getId(), 5, true);
        marinaraInventory   = new SauceInventoryOrm((long) inventoryOrm.getId(), (int)marinara.getId(), 5, true);
        pepperoniInventory  = new ToppingInventoryOrm((long) inventoryOrm.getId(), (int)pepperoni.getId(), 5, true);
        mushroomsInventory  = new ToppingInventoryOrm((long) inventoryOrm.getId(), (int)mushrooms.getId(), 5, false);

        // Create Promotion
        Date startDate = Date.valueOf("2019-03-05");
        Date endDate = Date.valueOf("2019-03-06");
        promotionOrm = new PromotionOrm(store.getId(), "2_DOLLARS_OFF", startDate, endDate, dollarsOff.getId(), 2, true);

        // Create Pizza Order
        Date orderDate = Date.valueOf("2019-03-06");
        pizzaOrderOrm = new PizzaOrderOrm(store.getId(), promotionOrm.getId(), orderDate, "Dave", "Address", "Phone", true);

        // Create Pizza
        pizzaOrm = new PizzaOrm("DeepDish", 5000, pizzaOrderOrm.getId(), thinCrust.getId(), marinara.getId(), mozzarella.getId(), true);

        // Create topping
        toppingOrm = new ToppingOrm(pizzaOrm.getId(), pepperoni.getId(), true);
    }
}
