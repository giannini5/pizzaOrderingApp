package pizza.orm;

import pizza.service.database.Column;
import pizza.service.database.IntegerColumn;
import pizza.service.database.StringColumn;

import java.sql.Date;
import java.sql.ResultSet;

public class PizzaOrderOrm extends BaseOrm {
    // Table name
    private static final String TABLE   = "pizza_order";

    // Column names
    private static final String ID              = "id";
    private static final String PIZZA_STORE_ID  = "pizza_store_id";
    private static final String PROMOTION_ID    = "promotion_id";
    private static final String ORDER_DATE      = "order_date";
    private static final String NAME            = "name";
    private static final String ADDRESS         = "address";
    private static final String PHONE           = "phone";

    /**
     * Create Constructor
     *
     * @param _pizzaStoreId - Pizza Store servicing the order
     * @param _promotionId  - Promotion for pizza discount
     * @param _orderDate    - Date of order
     * @param _name         - Name of person placing the order
     * @param _address      - Address of order for delivery
     * @param _phone        - Phone number of person placing the order
     */
    public PizzaOrderOrm(long _pizzaStoreId, long _promotionId, Date _orderDate, String _name, String _address,
                         String _phone, boolean _createTable) throws Exception {
        super(TABLE);

        // Populate non-identifier columns
        addColumn(new IntegerColumn(PIZZA_STORE_ID, _pizzaStoreId));
        addColumn(new IntegerColumn(PROMOTION_ID, _promotionId));
        addColumn(new StringColumn(ORDER_DATE, _orderDate.toString()));
        addColumn(new StringColumn(NAME, _name));
        addColumn(new StringColumn(ADDRESS, _address));
        addColumn(new StringColumn(PHONE, _phone));

        // Insert new row into the database
        long id = insert(_createTable);

        addColumn(new IntegerColumn(ID, id));
    }

    /**
     * Lookup Constructor
     *
     * @param id - Pizza's unique identifier
     */
    public PizzaOrderOrm(long id) throws Exception {
        super(TABLE);

        ResultSet results = lookupById(id);
        // TODO: Verify one row returned
        results.next();

        // Get row and populate columns
        addColumn(new IntegerColumn(ID, results.getLong(ID)));
        addColumn(new IntegerColumn(PIZZA_STORE_ID, results.getLong(PIZZA_STORE_ID)));
        addColumn(new IntegerColumn(PROMOTION_ID, results.getLong(PROMOTION_ID)));
        addColumn(new StringColumn(ORDER_DATE, results.getString(ORDER_DATE)));
        addColumn(new StringColumn(NAME, results.getString(NAME)));
        addColumn(new StringColumn(ADDRESS, results.getString(ADDRESS)));
        addColumn(new StringColumn(PHONE, results.getString(PHONE)));
    }

    public long getId() {
        Column column = getColumn(ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getPizzaStoreId() {
        Column column = getColumn(PIZZA_STORE_ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getPromotionId() {
        Column column = getColumn(PROMOTION_ID);
        return ((IntegerColumn)column).getValue();
    }

    public Date getOrderDate() {
        Column column = getColumn(ORDER_DATE);
        return Date.valueOf(((StringColumn)column).getValue());
    }

    public String getName() {
        Column column = getColumn(NAME);
        return ((StringColumn)column).getValue();
    }

    public String getAddress() {
        Column column = getColumn(ADDRESS);
        return ((StringColumn)column).getValue();
    }

    public String getPhone() {
        Column column = getColumn(PHONE);
        return ((StringColumn)column).getValue();
    }
}
