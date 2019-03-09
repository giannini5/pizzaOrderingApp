package pizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BaseInventory {
    protected Inventory inventory;

    // Supported operations
    public static final String ADD      = "add";
    public static final String SUBTRACT = "subtract";

    /**
     * Create constructor
     *
     * @param _inventory - Inventory
     */
    BaseInventory(Inventory _inventory) {
        inventory = _inventory;
    }

    protected void setInventory(Inventory _inventory) {
        inventory = _inventory;
    }

    /**
     * Update inventory
     *
     * @param operation - See above for supported operations
     * @param amount    - Inventory amount to be adjusted
     */
    public void update(String operation, int amount) throws Exception {
        switch (operation) {
            case ADD:
                add(amount);
                break;
            case SUBTRACT:
                subtract(amount);
                break;
            default:
                throw new Exception("Unrecognized operation: " + operation);
        }
    }

    @JsonIgnore
    public Inventory getInventory() {
        return inventory;
    }

    abstract public void add(long amount)  throws Exception;
    abstract public void subtract(long amount)  throws Exception;
}
