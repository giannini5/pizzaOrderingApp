package pizza.orm;

import pizza.service.database.Column;
import pizza.service.database.Database;
import pizza.service.database.IntegerColumn;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ToppingOrm extends BaseOrm {
    // Table name
    private static final String TABLE   = "topping";

    // Column names
    private static final String ID              = "id";
    private static final String PIZZA_ID        = "pizza_id";
    private static final String ENUM_TOPPING_ID = "enum_topping_id";

    /**
     * Create Constructor
     *
     * @param _pizzaId       - Pizza identifier
     * @param _enumToppingId - Topping identifier
     */
    public ToppingOrm(long _pizzaId, long _enumToppingId, boolean _createTable) throws Exception {
        super(TABLE);

        addColumn(new IntegerColumn(PIZZA_ID, _pizzaId));
        addColumn(new IntegerColumn(ENUM_TOPPING_ID, _enumToppingId));

        // Insert new row into the database
        long id = insert(_createTable);

        addColumn(new IntegerColumn(ID, id));
    }

    /**
     * Lookup By Id Constructor
     *
     * @param id - Topping identifier
     */
    public ToppingOrm(long id) throws Exception {
        super(TABLE);

        ResultSet results = lookupById(id);

        // TODO: Verify one row returned
        results.next();

        // Get row and populate columns
        addColumn(new IntegerColumn(ID, results.getLong(ID)));
        addColumn(new IntegerColumn(PIZZA_ID, results.getLong(PIZZA_ID)));
        addColumn(new IntegerColumn(ENUM_TOPPING_ID, results.getLong(ENUM_TOPPING_ID)));
    }

    /**
     * Lookup By Id Constructor
     *
     * @param _resultSet - Topping row
     */
    private ToppingOrm(ResultSet _resultSet) throws Exception {
        super(TABLE);

        // Get row and populate columns
        addColumn(new IntegerColumn(ID, _resultSet.getLong(ID)));
        addColumn(new IntegerColumn(PIZZA_ID, _resultSet.getLong(PIZZA_ID)));
        addColumn(new IntegerColumn(ENUM_TOPPING_ID, _resultSet.getLong(ENUM_TOPPING_ID)));
    }

    /**
     * Get list of toppings for a pizza
     *
     * @param _pizzaId - Pizza's unique identifier
     * @return ArrayList
     */
    public static ArrayList<ToppingOrm> LookupByPizzaId(long _pizzaId) throws Exception {
        Database database = Database.getInstance();
        ResultSet results = database.read(TABLE, PIZZA_ID + " = '" + _pizzaId + "'");

        ArrayList<ToppingOrm> toppingOrms = new ArrayList<ToppingOrm>();
        while (results.next()) {
            toppingOrms.add(new ToppingOrm(results));
        }

        return toppingOrms;
    }

    public long getId() {
        Column column = getColumn(ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getPizzaId() {
        Column column = getColumn(PIZZA_ID);
        return ((IntegerColumn)column).getValue();
    }

    public long getEnumTopplingId() {
        Column column = getColumn(ENUM_TOPPING_ID);
        return ((IntegerColumn)column).getValue();
    }
}
