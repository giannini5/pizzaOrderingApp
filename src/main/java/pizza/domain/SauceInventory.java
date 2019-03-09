package pizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pizza.orm.SauceInventoryOrm;
import pizza.orm.EnumSauceTypeOrm;

public class SauceInventory extends BaseInventory {

    private SauceInventoryOrm   sauceInventoryOrm;
    private Inventory           inventory;
    private EnumSauceTypeOrm    sauceTypeOrm;

    /**
     * Create constructor
     *
     * @param _inventory - Inventory
     */
    public SauceInventory(Inventory _inventory, String sauceType, int count) throws Exception {
        super(_inventory);

        sauceTypeOrm        = new EnumSauceTypeOrm(sauceType);
        sauceInventoryOrm   = new SauceInventoryOrm(_inventory.getId(), sauceTypeOrm.getId(), count, false);
    }

    /**
     * Lookup By ID constructor
     *
     * @param _id - SauceInventory unique identifier
     */
    public SauceInventory(long _id) throws Exception {
        super(null);

        sauceInventoryOrm   = new SauceInventoryOrm(_id);
        sauceTypeOrm        = new EnumSauceTypeOrm(sauceInventoryOrm.getTypeId());
        setInventory(new Inventory(sauceInventoryOrm.getInventoryId(), true));
    }

    /**
     * Lookup By Inventory and Type constructor
     *
     * @param _inventory - Inventory
     * @param _sauceType - Sauce Type
     */
    public SauceInventory(Inventory _inventory, String _sauceType) throws Exception {
        super(_inventory);

        sauceTypeOrm        = new EnumSauceTypeOrm(_sauceType);
        sauceInventoryOrm   = new SauceInventoryOrm(_inventory.getId(), sauceTypeOrm.getId());
    }

    public long getId() {
        return sauceInventoryOrm.getId();
    }

    public String getType() {
        return sauceTypeOrm.getType();
    }

    public long getCount() {
        return sauceInventoryOrm.getCount();
    }

    public void add(long count) throws Exception {
        sauceInventoryOrm.addCount(count);
    }

    public void subtract(long count) throws Exception {
        sauceInventoryOrm.subtractCount(count);
    }
}
