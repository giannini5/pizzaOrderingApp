package pizza.domain;

import pizza.orm.*;

import java.sql.Date;

public class PizzaStore {

    private PizzaStoreOrm pizzaStoreOrm;

    /**
     * Create constructor
     *
     * @param _name     - Name of the store
     * @param _address  - Address of the store
     */
    public PizzaStore(String _name, String _address) throws Exception {
        pizzaStoreOrm = new PizzaStoreOrm(_name, _address, false);
    }

    /**
     * Lookup constructor
     *
     * @param _id   - Store's unique identifier
     */
    public PizzaStore(long _id) throws Exception {
        pizzaStoreOrm = new PizzaStoreOrm(_id);
    }

    public void createDefaultInventory() throws Exception {
        Inventory inventory = new Inventory(this);
        new CrustInventory(inventory, EnumCrustTypeOrm.THIN, 0);
        new CrustInventory(inventory, EnumCrustTypeOrm.THICK, 0);
        new CheeseInventory(inventory, EnumCheeseTypeOrm.MOZZARELLA, 0);
        new SauceInventory(inventory, EnumSauceTypeOrm.MARINARA, 0);
        new ToppingInventory(inventory, EnumToppingTypeOrm.PEPPERONI, 0);
        new ToppingInventory(inventory, EnumToppingTypeOrm.MUSHROOMS, 0);
    }

    public void createDefaultPromotion() throws Exception {
        new Promotion(this, "No Discount For You!", Date.valueOf("1970-01-01"),
                Date.valueOf("3000-01-01"), EnumPromotionTypeOrm.DOLLARS_OFF, 0);
    }

    public long getId() {
        return pizzaStoreOrm.getId();
    }

    public String getName() {
        return pizzaStoreOrm.getName();
    }

    public String getAddress() {
        return pizzaStoreOrm.getAddress();
    }
}
