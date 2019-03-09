package pizza.service.database;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    // Database access info
    private static final String DB_URL      = "jdbc:h2:mem:pizza_ordering";
    private static final String DB_USER     = "sa";
    private static final String DB_PASSWORD = "";

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    private Connection connection;

    private Database() {
        try {
            System.out.println("Starting up database ...");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Create a new table in the database
     *
     * @param tableName - Name of table for insert
     * @param values    - Values for create table: (<value1>, ..., <valueN>)
     *
     * @throws Exception - SQL database problem
     */
    public void createTable(String tableName, String values) throws Exception {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + values + ")";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            throw new Exception("SQL CREATE for table '" + tableName + "' failed with message: " + e.getMessage());
        }
    }

    /**
     * Insert a new row into the database
     *
     * @param tableName - Name of table for insert
     * @param fields    - Fields for insert: <field1>, ..., <fieldN>
     * @param values    - Values for insert: <value1>, ..., <valueN>
     *
     * @return long     - Unique identifier of new row
     *
     * @throws Exception - SQL database problem
     */
    public long insert(String tableName, String fields, String values) throws Exception {
        try {
            String sql = "INSERT INTO " + tableName + " (" + fields + ") VALUES (" + values + ")";
            System.out.println(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new Exception("SQL statement '" + sql + "' did not return an index");
            }
        } catch (Exception e) {
            throw new Exception("SQL INSERT for table '" + tableName + "' failed with message: " + e.getMessage());
        }
    }

    /**
     * Update a row in the database
     *
     * @param tableName     - Name of table for insert
     * @param columnName    - Name of column to be updated
     * @param columnValue   - New value
     * @param idName        - Name of unique identifier
     * @param id            - Rows unique identifier
     *
     * @throws Exception - SQL database problem
     */
    public void update(String tableName, String columnName, long columnValue, String idName, long id) throws Exception {
        try {
            String sql = "UPDATE " + tableName + " set " + columnName + " = " + Long.toString(columnValue)
                    + " where id = "+ Long.toString(id);
            System.out.println(sql);

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            throw new Exception("SQL UPDATE for table '" + tableName + "' failed with message: " + e.getMessage());
        }
    }

    /**
     * Delete a row in the database
     *
     * @param tableName     - Name of table for insert
     * @param id            - Rows unique identifier
     *
     * @throws Exception - SQL database problem
     */
    public void delete(String tableName, long id) throws Exception {
        try {
            String sql = "delete " + tableName + " where id = "+ Long.toString(id);
            System.out.println(sql);

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            throw new Exception("SQL DELETE for table '" + tableName + "' failed with message: " + e.getMessage());
        }
    }

    /**
     * Read data from the database
     *
     * @param tableName   - Name of table for insert
     * @param whereFilter - SQL where clause filter
     *
     * @return ResultSet - ResultSet from read
     *
     * @throws Exception - SQL database problem
     */
    public ResultSet read(String tableName, String whereFilter) throws Exception {
        try {
            String sql = "SELECT * FROM " + tableName + " WHERE " + whereFilter;
            System.out.println(sql);
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            throw new Exception("SQL INSERT for table '" + tableName + "' failed with message: " + e.getMessage());
        }
    }

    /**
     * Drop all objects from the database
     */
    public void dropAllObjects() throws Exception {
        try {
            String sql = "DROP ALL OBJECTS";
            System.out.println(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            throw new Exception("DROP ALL OBJECTS failed with message: " + e.getMessage());
        }
    }

    public void setAutoCommit(boolean value) throws Exception {
        connection.setAutoCommit(value);
    }

    public void commmit() throws Exception {
        connection.commit();
    }

    public void rollback() throws Exception {
        connection.rollback();
    }
}
