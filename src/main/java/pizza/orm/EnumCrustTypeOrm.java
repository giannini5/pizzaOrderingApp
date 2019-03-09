package pizza.orm;

public class EnumCrustTypeOrm extends BaseEnumTypeOrm {
    // Table name
    private static final String TABLE   = "enum_crust_type";

    // Crust types
    public static final String THIN  = "thin";
    public static final String THICK = "thick";

    /**
     * Create Constructor
     *
     * @param _type - Crust Type
     */
    public EnumCrustTypeOrm(String _type, int _amountInCents, boolean _createTable) throws Exception {
        super(TABLE, _type, _amountInCents, _createTable);
    }

    /**
     * Lookup Constructor
     *
     * @param id - Store's unique identifier
     */
    public EnumCrustTypeOrm(long id) throws Exception {
        super(TABLE, id);
    }

    /**
     * Lookup By Type Constructor
     *
     * @param _type - Cheese type
     */
    public EnumCrustTypeOrm(String _type) throws Exception {
        super(TABLE, _type);
    }

    @Override
    protected String getUniqueIndexSQL() {
        return "unique index ux_crust_type" + "(" + TYPE + ")";
    }
}
