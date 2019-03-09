package pizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pizza.orm.InventoryOrm;

import java.sql.Date;

public class Inventory {

    private InventoryOrm    inventoryOrm;
    private PizzaStore      pizzaStore;

    // Types of inventory
    public static final String CRUST      = "crust";
    public static final String SAUCE      = "sauce";
    public static final String CHEESE     = "cheese";
    public static final String TOPPING    = "topping";

    /**
     * Create constructor
     *
     * @param _pizzaStore   - Pizza Store
     */
    public Inventory(PizzaStore _pizzaStore) throws Exception {
        pizzaStore      = _pizzaStore;
        inventoryOrm   = new InventoryOrm(pizzaStore.getId(), false);
    }

    /**
     * Lookup By ID constructor
     *
     * @param _id                    - Inventory's unique identifier or Pizza Store's unique identifier
     * @param _isInventoryIdentifier - True if _id is for Inventory; false if for PizzaStore
     */
    public Inventory(long _id, boolean _isInventoryIdentifier) throws Exception {
        inventoryOrm   = new InventoryOrm(_isInventoryIdentifier, _id);
        pizzaStore     = new PizzaStore(inventoryOrm.getPizzaStoreId());
    }

    /**
     * Factory class to lookup an inventory sub-type
     *
     * @param inventoryType     - Type of inventory, see Inventory for types
     * @param inventorySubType  - Sub inventory type, see Inventory type classes for subtypes - extensible
     *
     * @return BaseInventory
     */
    public BaseInventory getSubInventory(String inventoryType, String inventorySubType) throws Exception {
        switch (inventoryType) {
            case Inventory.CRUST:
                return new CrustInventory(this, inventorySubType);
            case Inventory.SAUCE:
                return new SauceInventory(this, inventorySubType);
            case Inventory.CHEESE:
                return new CheeseInventory(this, inventorySubType);
            case Inventory.TOPPING:
                return new ToppingInventory(this, inventorySubType);
            default:
                throw new Exception("Unrecognized inventoryType: " + inventoryType);
        }
    }

    public long getId() {
        return inventoryOrm.getId();
    }

    @JsonIgnore
    public PizzaStore getPizzaStore() {
        return pizzaStore;
    }
}
