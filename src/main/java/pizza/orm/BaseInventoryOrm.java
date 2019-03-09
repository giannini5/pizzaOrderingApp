package pizza.orm;

import pizza.service.database.Column;
import pizza.service.database.IntegerColumn;

import java.sql.ResultSet;

public class BaseInventoryOrm extends BaseOrm {
    // Column names
    private static final String ID      = "id";
    static final String INVENTORY_ID    = "inventory_id";
    static final String TYPE_ID         = "type_id";
    private static final String COUNT   = "count";

    /**
     * Create Constructor
     *
     * @param _tableName    - Name of inventory table
     * @param _inventoryId  - InventoryOrm identifier
     * @param _count        - Amount in inventory
     * @param _createTable  - True if table should be created
     *
     * @throws Exception - Errors during create
     */
    public BaseInventoryOrm(String _tableName, long _inventoryId, long _typeId, int _count, boolean _createTable) throws Exception {
        super(_tableName);

        // Populate non-identifier columns
        addColumn(new IntegerColumn(INVENTORY_ID, _inventoryId));
        addColumn(new IntegerColumn(TYPE_ID, _typeId));
        addColumn(new IntegerColumn(COUNT, _count));

        // Insert new row into the database
        long id = insert(_createTable);

        addColumn(new IntegerColumn(ID, id));
    }

    /**
     * Lookup Constructor
     *
     * @param _tableName - Name of inventory table
     * @param _id        - Crust InventoryOrm's unique identifier
     *
     * @throws Exception - Errors during lookup
     */
    public BaseInventoryOrm(String _tableName, long _id) throws Exception {
        super(_tableName);

        ResultSet results = lookupById(_id);
        // TODO: Verify one row returned
        addColumns(results);
    }

    /**
     * Lookup Constructor
     *
     * @param _tableName    - Name of inventory table
     * @param _enumTypeId   - InventoryOrm's unique type identifier
     *
     * @throws Exception - Errors during lookup
     */
    public BaseInventoryOrm(String _tableName, long _inventoryId, long _enumTypeId) throws Exception {
        super(_tableName);

        String uniqueIndexFilter = INVENTORY_ID + " = " + Long.toString(_inventoryId) + " AND " + TYPE_ID + " = " +
                Long.toString(_enumTypeId);
        ResultSet results = lookupByUniqueIndex(uniqueIndexFilter);
        // TODO: Verify one row returned
        addColumns(results);
    }

    private void addColumns(ResultSet results) throws Exception {
        results.next();
        addColumn(new IntegerColumn(ID, results.getLong(ID)));
        addColumn(new IntegerColumn(INVENTORY_ID, results.getLong(INVENTORY_ID)));
        addColumn(new IntegerColumn(TYPE_ID, results.getLong(TYPE_ID)));
        addColumn(new IntegerColumn(COUNT, results.getInt(COUNT)));
    }

    public long getId() {
        Column column = getColumn(ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getInventoryId() {
        Column column = getColumn(INVENTORY_ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getTypeId() {
        Column column = getColumn(TYPE_ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getCount() {
        Column column = getColumn(COUNT);
        return ((IntegerColumn)column).getValue();
    }

    public void addCount(long amountToAdd) throws Exception {
        long newCount = getCount() + amountToAdd;

        update(COUNT, newCount, ID, getId());

        Column column = getColumn(COUNT);
        ((IntegerColumn)column).setValue(newCount);
    }

    public void subtractCount(long amountToSubtract) throws Exception {
        long currentCount = getCount();
        long newCount     = currentCount - amountToSubtract;

        if (newCount < 0) {
            throw new Exception("Operation rejected, negative inventory would result: current: " +
                    Long.toString(currentCount) + " amountToSubtract: " + Long.toString(amountToSubtract));
        }

        update(COUNT, newCount, ID, getId());

        Column column = getColumn(COUNT);
        ((IntegerColumn)column).setValue(newCount);
    }
}
