package pizza.orm;

import pizza.service.database.Column;
import pizza.service.database.IntegerColumn;
import pizza.service.database.StringColumn;

import java.sql.ResultSet;

public class PizzaStoreOrm extends BaseOrm {
    // Table name
    private static final String TABLE   = "pizza_store";

    // Column names
    private static final String ID      = "id";
    private static final String NAME    = "name";
    private static final String ADDRESS = "address";

    /**
     * Create Constructor
     *
     * @param _name     - Name of the store
     * @param _address  - Store's address
     */
    public PizzaStoreOrm(String _name, String _address, boolean _createTable) throws Exception {
        super(TABLE);

        // Populate non-identifier columns
        addColumn(new StringColumn(NAME, _name));
        addColumn(new StringColumn(ADDRESS, _address));

        // Insert new row into the database
        long id = insert(_createTable);

        addColumn(new IntegerColumn(ID, id));
    }

    /**
     * Lookup Constructor
     *
     * @param id - Store's unique identifier
     */
    public PizzaStoreOrm(long id) throws Exception {
        super(TABLE);

        ResultSet results = lookupById(id);
        // TODO: Verify one row returned
        results.next();

        // Get row and populate columns
        addColumn(new IntegerColumn(ID, results.getLong(ID)));
        addColumn(new StringColumn(NAME, results.getString(NAME)));
        addColumn(new StringColumn(ADDRESS, results.getString(ADDRESS)));
    }

    public long getId() {
        Column column = getColumn(ID);
        return ((IntegerColumn)column).getValue();
    }

    public String getName() {
        Column column = getColumn(NAME);
        return ((StringColumn)column).getValue();
    }

    public String getAddress() {
        Column column = getColumn(ADDRESS);
        return ((StringColumn)column).getValue();
    }

    @Override
    protected String getUniqueIndexSQL() {
        return "unique index ux_name_address("
            + NAME
            + ","
            + ADDRESS
            + ")";
    }
}
