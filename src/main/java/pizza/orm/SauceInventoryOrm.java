package pizza.orm;

public class SauceInventoryOrm extends BaseInventoryOrm {
    // Table name
    private static final String TABLE   = "sauce_inventory";

    /**
     * Create Constructor
     *
     * @param _inventoryId     - InventoryOrm identifier
     * @param _enumSauceTypeId - Sauce type unique identifier
     * @param _count           - Amount in inventory
     * @param _createTable     - True if table should be created
     *
     * @throws Exception - Errors during create
     */
    public SauceInventoryOrm(long _inventoryId, long _enumSauceTypeId, int _count, boolean _createTable) throws Exception {
        super(TABLE, _inventoryId, _enumSauceTypeId, _count, _createTable);
    }

    /**
     * Lookup By Id Constructor
     *
     * @param _id - Sauce InventoryOrm's unique identifier
     *
     * @throws Exception - Errors during lookup
     */
    public SauceInventoryOrm(long _id) throws Exception {
        super(TABLE, _id);
    }

    /**
     * Lookup By Unique Index Constructor
     *
     * @param _inventoryId      - InventoryOrm Id
     * @param _enumSauceTypeId  - Sauce Type Id
     *
     * @throws Exception - Errors during lookup
     */
    public SauceInventoryOrm(long _inventoryId, long _enumSauceTypeId) throws Exception {
        super(TABLE, _inventoryId, _enumSauceTypeId);
    }

    @Override
    protected String getUniqueIndexSQL() {
        return "unique index ux_sauce_inventory_type" + "(" + INVENTORY_ID + "," + TYPE_ID + ")";
    }
}
