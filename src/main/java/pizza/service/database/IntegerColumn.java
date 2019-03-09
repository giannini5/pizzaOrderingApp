package pizza.service.database;

public class IntegerColumn extends Column {
    private long value;

    public IntegerColumn(String _name, long _value) {
        super(_name, Column.INT, "");
        value = _value;
    }

    @Override
    public String getStringValue() {
        return Long.toString(value);
    }

    public long getValue() {
        return value;
    }

    public void setValue(long _value) {
        value = _value;
    }
}
