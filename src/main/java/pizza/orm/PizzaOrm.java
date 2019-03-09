package pizza.orm;

import pizza.service.database.Column;
import pizza.service.database.Database;
import pizza.service.database.IntegerColumn;
import pizza.service.database.StringColumn;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PizzaOrm extends BaseOrm {
    // Table name
    private static final String TABLE   = "pizza";

    // Column names
    private static final String ID              = "id";
    private static final String NAME            = "name";
    private static final String AMOUNT_IN_CENTS = "amount_in_cents";
    private static final String PIZZA_ORDER_ID  = "pizza_order_id";
    private static final String ENUM_CRUST_ID   = "enum_crust_id";
    private static final String ENUM_SAUCE_ID   = "enum_sauce_id";
    private static final String ENUM_CHEESE_ID  = "enum_cheese_id";

    /**
     * Create Constructor
     *
     * @param _name             - Name of the store
     * @param _amount_in_cents  - Base cost of pizza in cents
     * @param _pizza_order_id   - Owning order
     * @param _enum_crust_id    - Type of crust
     * @param _enum_sauce_id    - Type of sauce
     * @param _enum_cheese_id   - Type of cheese
     */
    public PizzaOrm(String _name, int _amount_in_cents, long _pizza_order_id, long _enum_crust_id, long _enum_sauce_id,
                    long _enum_cheese_id, boolean _createTable) throws Exception {
        super(TABLE);

        // Populate non-identifier columns
        addColumn(new StringColumn(NAME, _name));
        addColumn(new IntegerColumn(AMOUNT_IN_CENTS, _amount_in_cents));
        addColumn(new IntegerColumn(PIZZA_ORDER_ID, _pizza_order_id));
        addColumn(new IntegerColumn(ENUM_CRUST_ID, _enum_crust_id));
        addColumn(new IntegerColumn(ENUM_SAUCE_ID, _enum_sauce_id));
        addColumn(new IntegerColumn(ENUM_CHEESE_ID, _enum_cheese_id));

        // Insert new row into the database
        long id = insert(_createTable);

        addColumn(new IntegerColumn(ID, id));
    }

    /**
     * Lookup Constructor
     *
     * @param id - Pizza's unique identifier
     */
    public PizzaOrm(long id) throws Exception {
        super(TABLE);

        ResultSet results = lookupById(id);
        // TODO: Verify one row returned
        results.next();

        // Get row and populate columns
        addColumn(new IntegerColumn(ID, results.getLong(ID)));
        addColumn(new StringColumn(NAME, results.getString(NAME)));
        addColumn(new IntegerColumn(AMOUNT_IN_CENTS, results.getLong(AMOUNT_IN_CENTS)));
        addColumn(new IntegerColumn(PIZZA_ORDER_ID, results.getLong(PIZZA_ORDER_ID)));
        addColumn(new IntegerColumn(ENUM_CRUST_ID, results.getLong(ENUM_CRUST_ID)));
        addColumn(new IntegerColumn(ENUM_SAUCE_ID, results.getLong(ENUM_SAUCE_ID)));
        addColumn(new IntegerColumn(ENUM_CHEESE_ID, results.getLong(ENUM_CHEESE_ID)));
    }

    /**
     * Lookup By ResultSet Constructor
     *
     * @param _resultSet - Pizza row
     */
    private PizzaOrm(ResultSet _resultSet) throws Exception {
        super(TABLE);

        // Get row and populate columns
        addColumn(new IntegerColumn(ID, _resultSet.getLong(ID)));
        addColumn(new StringColumn(NAME, _resultSet.getString(NAME)));
        addColumn(new IntegerColumn(AMOUNT_IN_CENTS, _resultSet.getLong(AMOUNT_IN_CENTS)));
        addColumn(new IntegerColumn(PIZZA_ORDER_ID, _resultSet.getLong(PIZZA_ORDER_ID)));
        addColumn(new IntegerColumn(ENUM_CRUST_ID, _resultSet.getLong(ENUM_CRUST_ID)));
        addColumn(new IntegerColumn(ENUM_SAUCE_ID, _resultSet.getLong(ENUM_SAUCE_ID)));
        addColumn(new IntegerColumn(ENUM_CHEESE_ID, _resultSet.getLong(ENUM_CHEESE_ID)));
    }

    /**
     * Lookup By Pizza Order Constructor
     *
     * @param _pizzaOrderId - Pizza order identifier
     */
    public static ArrayList<PizzaOrm> LookupByPizzaOrderId(long _pizzaOrderId) throws Exception {
        Database database = Database.getInstance();
        ResultSet results = database.read(TABLE, PIZZA_ORDER_ID + " = '" + _pizzaOrderId + "'");

        ArrayList<PizzaOrm> pizzaOrms = new ArrayList<PizzaOrm>();
        while (results.next()) {
            pizzaOrms.add(new PizzaOrm(results));
        }

        return pizzaOrms;
    }

    public long getId() {
        Column column = getColumn(ID);
        return ((IntegerColumn)column).getValue();
    }

    public String getName() {
        Column column = getColumn(NAME);
        return ((StringColumn)column).getValue();
    }

    public int getAmountInCents() {
        Column column = getColumn(AMOUNT_IN_CENTS);
        return (int)((IntegerColumn)column).getValue();
    }

    public long getPizzaOrderId() {
        Column column = getColumn(PIZZA_ORDER_ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getEnumCrustId() {
        Column column = getColumn(ENUM_CRUST_ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getEnumSauceId() {
        Column column = getColumn(ENUM_SAUCE_ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getEnumCheeseId() {
        Column column = getColumn(ENUM_CHEESE_ID);
        return ((IntegerColumn)column).getValue();
    }
}
