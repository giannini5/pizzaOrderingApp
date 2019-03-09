package pizza.orm;

import pizza.service.database.Column;
import pizza.service.database.Database;
import pizza.service.database.IntegerColumn;

import java.sql.ResultSet;
import java.util.*;

/**
 * Base functionality for ORMs
 */
class BaseOrm {
    private List<Column>        columns;
    private String              tableName;
    private Database            database;
    private Map<String, Column> columnsMap;

    private static final String ID = "id";

    BaseOrm(String _tableName) {
        tableName   = _tableName;
        columns     = new ArrayList<Column>();
        columnsMap  = new HashMap<String, Column>();
        database    = Database.getInstance();
    }

    private void createTable() throws Exception {
        database.createTable(tableName, getValuesForCreate());
    }

    /**
     * Add column
     *
     * @param column - Column to add
     */
    void addColumn(Column column) {
        columns.add(column);
        columnsMap.put(column.getName(), column);
    }

    /**
     * Get column by name
     *
     * @param name - Column name
     *
     * @return Column - column associated with name
     */
    Column getColumn(String name) {
        return columnsMap.get(name);
    }

    /**
     * Insert new row into the database
     *
     * @param _createTable - True if table should be created
     *
     * @return long - unique identifier for added row
     * @throws Exception - SQL insert error
     */
    long insert(boolean _createTable) throws Exception {
        if (_createTable) {
            createTable();
        }

        return database.insert(tableName, getFieldsForInsert(), getValuesForInsert());
    }

    /**
     * Update existing row in the database
     *
     * @param columnName    - Name of column to be updated
     * @param columnValue   - New value
     * @param idName        - Name of rows unique identifier
     * @param id            - Rows unique identifier
     *
     * @throws Exception - SQL insert error
     */
    void update(String columnName, long columnValue, String idName, long id) throws Exception {
        database.update(tableName, columnName, columnValue, idName, id);
    }

    /**
     * Lookup a row in the database based on rows unique identifier
     *
     * @param id - Row's unique identifier
     *
     * @return ResultSet - Row attributes
     * @throws Exception - SQL insert error
     */
    ResultSet lookupById(long id) throws Exception {
        return database.read(tableName, "id = " + Long.toString(id));
    }

    /**
     * Lookup a row in the database based on rows unique index
     *
     * @param columnName - name of column
     * @param value      - value of column
     *
     * @return ResultSet - Row attributes
     * @throws Exception - SQL insert error
     */
    ResultSet lookupByUniqueIndex(String columnName, String value) throws Exception {
        // TODO: assert only one row
        return database.read(tableName, columnName + " = '" + value + "'");
    }

    /**
     * Lookup a row in the database based on rows unique index
     *
     * @param uniqueIndexFilter - Where clause filter
     *
     * @return ResultSet - Row attributes
     * @throws Exception - SQL insert error
     */
    ResultSet lookupByUniqueIndex(String uniqueIndexFilter) throws Exception {
        return database.read(tableName, uniqueIndexFilter);
    }

    /**
     * Delete a row in the database based on rows unique identifier
     *
     * @param id - Row's unique identifier
     */
    public void delete(long id) throws Exception {
        database.delete(tableName, ((IntegerColumn)getColumn(ID)).getValue());
    }

    /**
     * @return String - "<column1 value>, ..., <columnN value>"
     */
    private String getValuesForInsert() {
        StringBuilder values = new StringBuilder();

        boolean addComma = false;
        for (Column column : columns) {
            if (addComma) {
                values.append(",");
            }

            values.append(column.getStringValue());
            addComma = true;
        }

        return values.toString();
    }

    /**
     * @return String - "<column1 name> , ..., <columnN name>"
     */
    private String getFieldsForInsert() {
        StringBuilder fields = new StringBuilder();

        boolean addComma = false;
        for (Column column : columns) {
            if (addComma) {
                fields.append(",");
            }

            fields.append(column.getName());
            addComma = true;
        }

        return fields.toString();
    }

    /**
     * @return ArrayList - "<column1 name>, ..., <columnN name>"
     */
    private ArrayList getFieldsForUpdate() {
        ArrayList<String> fields = new ArrayList<String>();

        for (Column column : columns) {
            boolean result = fields.add(column.getName());
            assert(result);
        }

        return fields;
    }

    /**
     * @return ArrayList - "<column1 value>, ..., <columnN value>"
     */
    private ArrayList getValuesForUpdate() {
        ArrayList<String> values = new ArrayList<String>();

        for (Column column : columns) {
            boolean result = values.add(column.getStringValue());
            assert(result);
        }

        return values;
    }

    /**
     * @return String - "<column1> <type> <constraint>, ..., <columnN> <type> <constraint>, primary key (id) [, unique index ux_<index name> (column1, ..., columnN)]"
     */
    private String getValuesForCreate() {
        StringBuilder values = new StringBuilder();

        values.append("id");
        values.append(" ");
        values.append("bigint auto_increment");

        for (Column column : columns) {
            values.append(",");
            values.append(column.getName());
            values.append(" ");
            values.append(column.getType());
            values.append(" ");
            values.append(column.getConstraint());
        }

        values.append(",");
        values.append("PRIMARY KEY (ID)");

        String uniqueKeySQL = getUniqueIndexSQL();
        if (uniqueKeySQL.length() > 0) {
            values.append(",");
            values.append(uniqueKeySQL);
        }

        return values.toString();
    }

    /**
     * Get the unique index SQL string if any
     * @return ""
     */
    protected String getUniqueIndexSQL() {
        return "";
    }

    @Override
    public String toString() {
        StringBuilder values = new StringBuilder();

        values.append(tableName);
        values.append(": ");

        boolean addComma = false;
        for (Column column : columns) {
            if (addComma) {
                values.append(",");
            }

            values.append(column.getName());
            values.append(":");
            values.append(column.getStringValue());

            addComma = true;
        }

        return values.toString();
    }
}