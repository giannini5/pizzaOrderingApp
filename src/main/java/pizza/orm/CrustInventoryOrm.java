package pizza.orm;

public class CrustInventoryOrm extends BaseInventoryOrm {
    // Table name
    private static final String TABLE   = "crust_inventory";

    /**
     * Create Constructor
     *
     * @param _inventoryId      - InventoryOrm identifier
     * @param _enumCrustTypeId  - Crust type unique identifier
     * @param _count            - Amount in inventory
     * @param _createTable      - True if table should be created
     *
     * @throws Exception - Errors during create
     */
    public CrustInventoryOrm(long _inventoryId, long _enumCrustTypeId, int _count, boolean _createTable) throws Exception {
        super(TABLE, _inventoryId, _enumCrustTypeId, _count, _createTable);
    }

    /**
     * Lookup By Id Constructor
     *
     * @param _id - Crust InventoryOrm's unique identifier
     *
     * @throws Exception - Errors during lookup
     */
    public CrustInventoryOrm(long _id) throws Exception {
        super(TABLE, _id);
    }

    /**
     * Lookup By Unique Index Constructor
     *
     * @param _inventoryId      - InventoryOrm Id
     * @param _enumCrustTypeId  - Crust Type Id
     *
     * @throws Exception - Errors during lookup
     */
    public CrustInventoryOrm(long _inventoryId, long _enumCrustTypeId) throws Exception {
        super(TABLE, _inventoryId, _enumCrustTypeId);
    }

    @Override
    protected String getUniqueIndexSQL() {
        return "unique index ux_crust_inventory_type" + "(" + INVENTORY_ID + "," + TYPE_ID + ")";
    }
}
