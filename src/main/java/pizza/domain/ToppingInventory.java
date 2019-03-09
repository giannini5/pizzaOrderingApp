package pizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pizza.orm.ToppingInventoryOrm;
import pizza.orm.EnumToppingTypeOrm;

public class ToppingInventory extends BaseInventory {

    private ToppingInventoryOrm toppingInventoryOrm;
    private Inventory           inventory;
    private EnumToppingTypeOrm  toppingTypeOrm;

    /**
     * Create constructor
     *
     * @param _inventory - Inventory
     */
    public ToppingInventory(Inventory _inventory, String toppingType, int count) throws Exception {
        super(_inventory);

        toppingTypeOrm      = new EnumToppingTypeOrm(toppingType);
        toppingInventoryOrm = new ToppingInventoryOrm(_inventory.getId(), toppingTypeOrm.getId(), count, false);
    }

    /**
     * Lookup By ID constructor
     *
     * @param _id - ToppingInventory unique identifier
     */
    public ToppingInventory(long _id) throws Exception {
        super(null);

        toppingInventoryOrm = new ToppingInventoryOrm(_id);
        toppingTypeOrm      = new EnumToppingTypeOrm(toppingInventoryOrm.getTypeId());
        setInventory(new Inventory(toppingInventoryOrm.getInventoryId(), true));
    }

    /**
     * Lookup By Inventory and Type constructor
     *
     * @param _inventory - Inventory
     * @param _toppingType - Topping Type
     */
    public ToppingInventory(Inventory _inventory, String _toppingType) throws Exception {
        super(_inventory);

        toppingTypeOrm      = new EnumToppingTypeOrm(_toppingType);
        toppingInventoryOrm = new ToppingInventoryOrm(_inventory.getId(), toppingTypeOrm.getId());
    }

    public long getId() {
        return toppingInventoryOrm.getId();
    }

    public String getType() {
        return toppingTypeOrm.getType();
    }

    public long getCount() {
        return toppingInventoryOrm.getCount();
    }

    public void add(long count) throws Exception {
        toppingInventoryOrm.addCount(count);
    }

    public void subtract(long count) throws Exception {
        toppingInventoryOrm.subtractCount(count);
    }
}
