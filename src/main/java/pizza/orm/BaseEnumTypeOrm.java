package pizza.orm;

import pizza.service.database.Column;
import pizza.service.database.IntegerColumn;
import pizza.service.database.StringColumn;

import java.sql.ResultSet;

public class BaseEnumTypeOrm extends BaseOrm {
    // Column names
    private static final String ID              = "id";
    static final String TYPE                    = "type";
    private static final String AMOUNT_IN_CENTS = "amount_in_cents";

    /**
     * Create Constructor
     *
     * @param _type             - Enum unique name
     * @param _amountInCents    - Amount in cents
     * @param _createTable      - True if table should be created
     */
    public BaseEnumTypeOrm(String _tableName, String _type, int _amountInCents, boolean _createTable) throws Exception {
        super(_tableName);

        // Populate non-identifier columns
        // TODO: Validate _type
        addColumn(new StringColumn(TYPE, _type));
        addColumn(new IntegerColumn(AMOUNT_IN_CENTS, _amountInCents));

        // Insert new row into the database
        long id = insert(_createTable);

        addColumn(new IntegerColumn(ID, id));
    }

    /**
     * Lookup By Id Constructor
     *
     * @param _tableName - Name of database table
     * @param _id        - Table's unique identifier
     */
    public BaseEnumTypeOrm(String _tableName, long _id) throws Exception {
        super(_tableName);

        ResultSet results = lookupById(_id);
        // TODO: Verify one row returned
        addColumns(results);
    }

    /**
     * Lookup By Type Constructor
     *
     * @param _tableName - Name of database table
     * @param _type      - Table's unique identifier
     */
    BaseEnumTypeOrm(String _tableName, String _type) throws Exception {
        super(_tableName);

        ResultSet results = lookupByUniqueIndex(TYPE, _type);
        // TODO: Verify one row returned
        addColumns(results);
    }

    private void addColumns(ResultSet results) throws Exception {
        results.next();

        // Get row and populate columns
        addColumn(new IntegerColumn(ID, results.getLong(ID)));
        addColumn(new StringColumn(TYPE, results.getString(TYPE)));
        addColumn(new IntegerColumn(AMOUNT_IN_CENTS, results.getInt(AMOUNT_IN_CENTS)));
    }

    public long getId() {
        Column column = getColumn(ID);
        return ((IntegerColumn)column).getValue();
    }

    public String getType() {
        Column column = getColumn(TYPE);
        return ((StringColumn)column).getValue();
    }

    public int getAmountInCents() {
        Column column = getColumn(AMOUNT_IN_CENTS);
        return (int)((IntegerColumn)column).getValue();
    }
}
