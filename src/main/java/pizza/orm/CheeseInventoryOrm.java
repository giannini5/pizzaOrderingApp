package pizza.orm;

public class CheeseInventoryOrm extends BaseInventoryOrm {
    // Table name
    private static final String TABLE   = "cheese_inventory";

    /**
     * Create Constructor
     *
     * @param _inventoryId      - InventoryOrm identifier
     * @param _enumCheeseTypeId - Cheese type unique identifier
     * @param _count            - Amount in inventory
     * @param _createTable      - True if table should be created
     *
     * @throws Exception - Errors during create
     */
    public CheeseInventoryOrm(long _inventoryId, long _enumCheeseTypeId, int _count, boolean _createTable) throws Exception {
        super(TABLE, _inventoryId, _enumCheeseTypeId, _count, _createTable);
    }

    /**
     * Lookup By Id Constructor
     *
     * @param _id - Cheese InventoryOrm's unique identifier
     *
     * @throws Exception - Errors during lookup
     */
    public CheeseInventoryOrm(long _id) throws Exception {
        super(TABLE, _id);
    }

    /**
     * Lookup By Unique Index Constructor
     *
     * @param _inventoryId      - InventoryOrm Id
     * @param _enumCheeseTypeId  - Cheese Type Id
     *
     * @throws Exception - Errors during lookup
     */
    public CheeseInventoryOrm(long _inventoryId, long _enumCheeseTypeId) throws Exception {
        super(TABLE, _inventoryId, _enumCheeseTypeId);
    }

    @Override
    protected String getUniqueIndexSQL() {
        return "unique index ux_cheese_inventory_type" + "(" + INVENTORY_ID + "," + TYPE_ID + ")";
    }
}
