package pizza.orm;

import pizza.service.database.Column;
import pizza.service.database.IntegerColumn;

import java.sql.ResultSet;

public class InventoryOrm extends BaseOrm {
    // Table name
    private static final String TABLE   = "inventory";

    // Column names
    private static final String ID              = "id";
    private static final String PIZZA_STORE_ID  = "pizza_store_id";

    /**
     * Create Constructor
     *
     * @param _pizzaStoreId - Store identifier
     */
    public InventoryOrm(Long _pizzaStoreId, boolean _createTable) throws Exception {
        super(TABLE);

        addColumn(new IntegerColumn(PIZZA_STORE_ID, _pizzaStoreId));

        // Insert new row into the database
        long id = insert(_createTable);

        addColumn(new IntegerColumn(ID, id));
    }

    /**
     * Lookup Constructor
     *
     * @param isInventoryIdentifier - If true then id is an inventoryId, otherwise id is a pizzaStoreId
     * @param id                    - Inventory identifier or Pizza Store Identifier
     */
    public InventoryOrm(boolean isInventoryIdentifier, long id) throws Exception {
        super(TABLE);

        ResultSet results;
        if (isInventoryIdentifier) {
            results = lookupByInventoryId(id);
        } else {
            results = lookupByPizzaStoreId(id);
        }

        // TODO: Verify one row returned
        results.next();

        // Get row and populate columns
        addColumn(new IntegerColumn(ID, results.getLong(ID)));
        addColumn(new IntegerColumn(PIZZA_STORE_ID, results.getLong(PIZZA_STORE_ID)));
    }

    /**
     * Lookup By Id Constructor
     *
     * @param id - Inventory identifier
     *
     * @return ResultSet
     */
    private ResultSet lookupByInventoryId(long id) throws Exception {
        return lookupById(id);
    }

    /**
     * Lookup By Pizza Store Id Constructor
     *
     * @param pizzaStoreId - Pizza store identifier
     *
     * @return ResultSet
     */
    private ResultSet lookupByPizzaStoreId(long pizzaStoreId) throws Exception {
        String uniqueIndexFilter = PIZZA_STORE_ID + " = " + Long.toString(pizzaStoreId);
        return lookupByUniqueIndex(uniqueIndexFilter);
    }

    public long getId() {
        Column column = getColumn(ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getPizzaStoreId() {
        Column column = getColumn(PIZZA_STORE_ID);
        return ((IntegerColumn)column).getValue();
    }
}
