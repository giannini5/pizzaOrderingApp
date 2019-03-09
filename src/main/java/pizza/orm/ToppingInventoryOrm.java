package pizza.orm;

public class ToppingInventoryOrm extends BaseInventoryOrm {
    // Table name
    private static final String TABLE   = "topping_inventory";

    /**
     * Create Constructor
     *
     * @param _inventoryId       - InventoryOrm identifier
     * @param _enumToppingTypeId - Topping type unique identifier
     * @param _count             - Amount in inventory
     * @param _createTable       - True if table should be created
     *
     * @throws Exception - Errors during create
     */
    public ToppingInventoryOrm(long _inventoryId, long _enumToppingTypeId, int _count, boolean _createTable) throws Exception {
        super(TABLE, _inventoryId, _enumToppingTypeId, _count, _createTable);
    }

    /**
     * Lookup By Id Constructor
     *
     * @param _id - Topping InventoryOrm's unique identifier
     *
     * @throws Exception - Errors during lookup
     */
    public ToppingInventoryOrm(long _id) throws Exception {
        super(TABLE, _id);
    }

    /**
     * Lookup By Unique Index Constructor
     *
     * @param _inventoryId      - InventoryOrm Id
     * @param _enumToppingTypeId  - Topping Type Id
     *
     * @throws Exception - Errors during lookup
     */
    public ToppingInventoryOrm(long _inventoryId, long _enumToppingTypeId) throws Exception {
        super(TABLE, _inventoryId, _enumToppingTypeId);
    }

    @Override
    protected String getUniqueIndexSQL() {
        return "unique index ux_topping_inventory_type" + "(" + INVENTORY_ID + "," + TYPE_ID + ")";
    }
}
