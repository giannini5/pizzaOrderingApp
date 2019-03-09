package pizza.orm;

public class EnumSauceTypeOrm extends BaseEnumTypeOrm {
    // Table name
    private static final String TABLE   = "enum_sauce_type";

    // Sauce types
    public static final String MARINARA = "marinara";

    /**
     * Create Constructor
     *
     * @param _type - Sauce Type
     */
    public EnumSauceTypeOrm(String _type, int _amountInCents, boolean _createTable) throws Exception {
        super(TABLE, _type, _amountInCents, _createTable);
    }

    /**
     * Lookup Constructor
     *
     * @param id - Store's unique identifier
     */
    public EnumSauceTypeOrm(long id) throws Exception {
        super(TABLE, id);
    }

    /**
     * Lookup By Type Constructor
     *
     * @param _type - Sauce type
     */
    public EnumSauceTypeOrm(String _type) throws Exception {
        super(TABLE, _type);
    }

    @Override
    protected String getUniqueIndexSQL() {
        return "unique index ux_sauce_type" + "(" + TYPE + ")";
    }
}
