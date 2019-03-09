package pizza.service.database;

public abstract class Column {
    static final String INT    = "INTEGER";
    static final String CHAR   = "CHAR";
    static final String STRING = "VARCHAR(256)";

    private String name;
    private String type;
    private String constraint;

    Column() {}

    Column (String _name, String _type, String _constraint) {
        // TODO: Add precondition to verify type

        name        = _name;
        type        = _type;
        constraint  = _constraint;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getConstraint() {
        return constraint;
    }

    public abstract String getStringValue();
}
