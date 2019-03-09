package pizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pizza.orm.CheeseInventoryOrm;
import pizza.orm.EnumCheeseTypeOrm;

public class CheeseInventory extends BaseInventory {

    private CheeseInventoryOrm  cheeseInventoryOrm;
    private EnumCheeseTypeOrm   cheeseTypeOrm;

    /**
     * Create constructor
     *
     * @param _inventory - Inventory
     */
    public CheeseInventory(Inventory _inventory, String cheeseType, int count) throws Exception {
        super(_inventory);
        cheeseTypeOrm       = new EnumCheeseTypeOrm(cheeseType);
        cheeseInventoryOrm  = new CheeseInventoryOrm(_inventory.getId(), cheeseTypeOrm.getId(), count, false);
    }

    /**
     * Lookup By ID constructor
     *
     * @param _id - CheeseInventory unique identifier
     */
    public CheeseInventory(long _id) throws Exception {
        super(null);

        cheeseInventoryOrm  = new CheeseInventoryOrm(_id);
        cheeseTypeOrm       = new EnumCheeseTypeOrm(cheeseInventoryOrm.getTypeId());
        setInventory(new Inventory(cheeseInventoryOrm.getInventoryId(), true));
    }

    /**
     * Lookup By Inventory and Type constructor
     *
     * @param _inventory - Inventory
     * @param _cheeseType - Cheese Type
     */
    public CheeseInventory(Inventory _inventory, String _cheeseType) throws Exception {
        super(_inventory);

        cheeseTypeOrm       = new EnumCheeseTypeOrm(_cheeseType);
        cheeseInventoryOrm  = new CheeseInventoryOrm(_inventory.getId(), cheeseTypeOrm.getId());
    }

    public long getId() {
        return cheeseInventoryOrm.getId();
    }

    public String getType() {
        return cheeseTypeOrm.getType();
    }

    public long getCount() {
        return cheeseInventoryOrm.getCount();
    }

    public void add(long count) throws Exception {
        cheeseInventoryOrm.addCount(count);
    }

    public void subtract(long count) throws Exception {
        cheeseInventoryOrm.subtractCount(count);
    }
}
