package pizza.orm;

public class EnumPromotionTypeOrm extends BaseEnumTypeOrm {
    // Table name
    private static final String TABLE   = "enum_promotion_type";

    // Promotion types
    public static final String DOLLARS_OFF = "dollars_off";
    public static final String PERCENT_OFF = "percent_off";

    /**
     * Create Constructor
     *
     * @param _type - Promotion Type
     */
    public EnumPromotionTypeOrm(String _type, boolean _createTable) throws Exception {
        super(TABLE, _type, 0, _createTable);
    }

    /**
     * Lookup Constructor
     *
     * @param _type - Promotion Type
     */
    public EnumPromotionTypeOrm(String _type) throws Exception {
        super(TABLE, _type);
    }

    /**
     * Lookup Constructor
     *
     * @param id - Store's unique identifier
     */
    public EnumPromotionTypeOrm(long id) throws Exception {
        super(TABLE, id);
    }

    @Override
    protected String getUniqueIndexSQL() {
        return "unique index ux_promotion_type" + "(" + TYPE + ")";
    }
}
