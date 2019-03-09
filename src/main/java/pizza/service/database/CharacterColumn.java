package pizza.service.database;

public class CharacterColumn extends Column {
    private char value;

    public CharacterColumn(String _name, char _value) {
        super(_name, Column.STRING, "");
        value = _value;
    }

    @Override
    public String getStringValue() {
        return "'" + value + "'";
    }
}
