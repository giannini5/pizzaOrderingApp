package pizza.service.database;

public class StringColumn extends Column {
    private String value;

    public StringColumn(String _name, String _value) {
        super(_name, Column.STRING, "");
        value = _value;
    }

    // TODO: SQL escape
    @Override
    public String getStringValue() {
        return "'" + value + "'";
    }

    public String getValue() {
        return value;
    }
}
