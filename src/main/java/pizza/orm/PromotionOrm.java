package pizza.orm;

import pizza.service.database.Column;
import pizza.service.database.IntegerColumn;
import pizza.service.database.StringColumn;

import java.sql.ResultSet;
import java.sql.Date;

public class PromotionOrm extends BaseOrm {
    // Table name
    private static final String TABLE   = "promotion";

    // Column names
    private static final String ID                      = "id";
    private static final String PIZZA_STRORE_ID         = "pizza_store_id";
    private static final String NAME                    = "name";
    private static final String START_DATE              = "start_date";
    private static final String END_DATE                = "end_date";
    private static final String ENUM_PROMOTION_TYPE_ID  = "enum_promotion_type_id";
    private static final String VALUE                   = "value";

    /**
     * Create Constructor
     *
     * @param _pizzaStoreId         - Pizza Store identifier
     * @param _name                 - Name of the store
     * @param _startDate            - Start of promotion
     * @param _endDate              - End of promotion
     * @param _enumPromotionTypeId  - Promotion type
     * @param _value                - Promotion value
     */
    public PromotionOrm(long _pizzaStoreId, String _name, Date _startDate, Date _endDate, long _enumPromotionTypeId,
                        int _value, boolean _createTable) throws Exception {
        super(TABLE);

        // Populate non-identifier columns
        addColumn(new IntegerColumn(PIZZA_STRORE_ID, _pizzaStoreId));
        addColumn(new StringColumn(NAME, _name));
        addColumn(new StringColumn(START_DATE, _startDate.toString()));
        addColumn(new StringColumn(END_DATE, _endDate.toString()));
        addColumn(new IntegerColumn(ENUM_PROMOTION_TYPE_ID, _enumPromotionTypeId));
        addColumn(new IntegerColumn(VALUE, _value));

        // Insert new row into the database
        long id = insert(_createTable);

        addColumn(new IntegerColumn(ID, id));
    }

    /**
     * Lookup by ID Constructor
     *
     * @param id - Promotion's unique identifier
     */
    public PromotionOrm(long id) throws Exception {
        super(TABLE);

        ResultSet results = lookupById(id);
        // TODO: Verify one row returned
        results.next();

        // Get row and populate columns
        addColumn(new IntegerColumn(ID, results.getLong(ID)));
        addColumn(new IntegerColumn(PIZZA_STRORE_ID, results.getLong(PIZZA_STRORE_ID)));
        addColumn(new StringColumn(NAME, results.getString(NAME)));
        addColumn(new StringColumn(START_DATE, results.getString(START_DATE)));
        addColumn(new StringColumn(END_DATE, results.getString(END_DATE)));
        addColumn(new IntegerColumn(ENUM_PROMOTION_TYPE_ID, results.getLong(ENUM_PROMOTION_TYPE_ID)));
        addColumn(new IntegerColumn(VALUE, results.getInt(VALUE)));
    }

    /**
     * Lookup by name Constructor
     *
     * @param _name - Promotion's name
     */
    public PromotionOrm(String _name) throws Exception {
        super(TABLE);

        ResultSet results = lookupByUniqueIndex(PromotionOrm.NAME, _name);
        // TODO: Verify one row returned
        results.next();

        // Get row and populate columns
        addColumn(new IntegerColumn(ID, results.getLong(ID)));
        addColumn(new IntegerColumn(PIZZA_STRORE_ID, results.getLong(PIZZA_STRORE_ID)));
        addColumn(new StringColumn(NAME, results.getString(NAME)));
        addColumn(new StringColumn(START_DATE, results.getString(START_DATE)));
        addColumn(new StringColumn(END_DATE, results.getString(END_DATE)));
        addColumn(new IntegerColumn(ENUM_PROMOTION_TYPE_ID, results.getLong(ENUM_PROMOTION_TYPE_ID)));
        addColumn(new IntegerColumn(VALUE, results.getInt(VALUE)));
    }

    public long getId() {
        Column column = getColumn(ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getPizzaStoreId() {
        Column column = getColumn(PIZZA_STRORE_ID);
        return ((IntegerColumn)column).getValue();
    }

    public String getName() {
        Column column = getColumn(NAME);
        return ((StringColumn)column).getValue();
    }

    public Date getStartDate() {
        Column column = getColumn(START_DATE);
        String dateString = ((StringColumn)column).getValue();
        return Date.valueOf(dateString);
    }

    public Date getEndDate() {
        Column column = getColumn(END_DATE);
        String dateString = ((StringColumn)column).getValue();
        return Date.valueOf(dateString);
    }

    public long getEnumPromotionTypeId() {
        Column column = getColumn(ENUM_PROMOTION_TYPE_ID);
        return ((IntegerColumn)column).getValue();
    }

    public int getValue() {
        Column column = getColumn(VALUE);
        return (int)((IntegerColumn)column).getValue();
    }

    @Override
    protected String getUniqueIndexSQL() {
        return "unique index ux_promotion_name" + "(" + NAME + ")";
    }
}
