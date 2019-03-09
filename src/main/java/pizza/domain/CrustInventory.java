package pizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pizza.orm.CrustInventoryOrm;
import pizza.orm.EnumCrustTypeOrm;

public class CrustInventory extends BaseInventory {

    private CrustInventoryOrm   crustInventoryOrm;
    private Inventory           inventory;
    private EnumCrustTypeOrm    crustTypeOrm;

    /**
     * Create constructor
     *
     * @param _inventory - Inventory
     */
    public CrustInventory(Inventory _inventory, String crustType, int count) throws Exception {
        super(_inventory);

        crustTypeOrm        = new EnumCrustTypeOrm(crustType);
        crustInventoryOrm   = new CrustInventoryOrm(_inventory.getId(), crustTypeOrm.getId(), count, false);
    }

    /**
     * Lookup By ID constructor
     *
     * @param _id - CrustInventory unique identifier
     */
    public CrustInventory(long _id) throws Exception {
        super(null);

        crustInventoryOrm   = new CrustInventoryOrm(_id);
        crustTypeOrm        = new EnumCrustTypeOrm(crustInventoryOrm.getTypeId());
        setInventory(new Inventory(crustInventoryOrm.getInventoryId(), true));
    }

    /**
     * Lookup By Inventory and Type constructor
     *
     * @param _inventory - Inventory
     * @param _crustType - Crust Type
     */
    public CrustInventory(Inventory _inventory, String _crustType) throws Exception {
        super(_inventory);

        crustTypeOrm        = new EnumCrustTypeOrm(_crustType);
        crustInventoryOrm   = new CrustInventoryOrm(_inventory.getId(), crustTypeOrm.getId());
    }

    public long getId() {
        return crustInventoryOrm.getId();
    }

    public String getType() {
        return crustTypeOrm.getType();
    }

    public long getCount() {
        return crustInventoryOrm.getCount();
    }

    public void add(long count) throws Exception {
        crustInventoryOrm.addCount(count);
    }

    public void subtract(long count) throws Exception {
        crustInventoryOrm.subtractCount(count);
    }
}
