package pizza.orm;

public class EnumToppingTypeOrm extends BaseEnumTypeOrm {
    // Table name
    private static final String TABLE   = "enum_topping_type";

    // Topping types
    public static final String PEPPERONI = "pepperoni";
    public static final String MUSHROOMS = "mushroom";

    /**
     * Create Constructor
     *
     * @param _type - Topping Type
     */
    public EnumToppingTypeOrm(String _type, int _amountInCents, boolean _createTable) throws Exception {
        super(TABLE, _type, _amountInCents, _createTable);
    }

    /**
     * Lookup Constructor
     *
     * @param id - Store's unique identifier
     */
    public EnumToppingTypeOrm(long id) throws Exception {
        super(TABLE, id);
    }

    /**
     * Lookup By Type Constructor
     *
     * @param _type - Topping type
     */
    public EnumToppingTypeOrm(String _type) throws Exception {
        super(TABLE, _type);
    }

    @Override
    protected String getUniqueIndexSQL() {
        return "unique index ux_topping_type" + "(" + TYPE + ")";
    }
}
