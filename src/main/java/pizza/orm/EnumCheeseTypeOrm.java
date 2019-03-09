package pizza.orm;

public class EnumCheeseTypeOrm extends BaseEnumTypeOrm {
    // Table name
    private static final String TABLE   = "enum_cheese_type";

    // Cheese types
    public static final String MOZZARELLA = "mozzarella";

    /**
     * Create Constructor
     *
     * @param _type - Cheese Type
     */
    public EnumCheeseTypeOrm(String _type, int _amountInCents, boolean _createTable) throws Exception {
        super(TABLE, _type, _amountInCents, _createTable);
    }

    /**
     * Lookup By ID Constructor
     *
     * @param id - Store's unique identifier
     */
    public EnumCheeseTypeOrm(long id) throws Exception {
        super(TABLE, id);
    }

    /**
     * Lookup By Type Constructor
     *
     * @param _type - Cheese type
     */
    public EnumCheeseTypeOrm(String _type) throws Exception {
        super(TABLE, _type);
    }

    @Override
    protected String getUniqueIndexSQL() {
        return "unique index ux_cheese_type" + "(" + TYPE + ")";
    }
}
