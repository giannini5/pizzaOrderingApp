package pizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pizza.orm.*;

import java.sql.Date;
import java.util.ArrayList;

public class Pizza {

    private PizzaOrm            pizzaOrm;
    private PizzaOrder          pizzaOrder;
    private EnumCrustTypeOrm    crustTypeOrm;
    private EnumSauceTypeOrm    sauceTypeOrm;
    private EnumCheeseTypeOrm   cheeseTypeOrm;

    /**
     * Create constructor
     *
     * @param _pizzaOrder       - Pizza Order
     * @param _name             - Name of the store
     * @param _amountInCents    - Base cost of pizza
     * @param _crustType        - Type of crust
     * @param _sauceType        - Type of sauce
     * @param _cheeseType       - Type of cheese
     */
    public Pizza(PizzaOrder _pizzaOrder, String _name, int _amountInCents,
                 String _crustType, String _sauceType, String _cheeseType) throws Exception {
        pizzaOrder      = _pizzaOrder;
        crustTypeOrm    = new EnumCrustTypeOrm(_crustType);
        sauceTypeOrm    = new EnumSauceTypeOrm(_sauceType);
        cheeseTypeOrm   = new EnumCheeseTypeOrm(_cheeseType);

        // Adjust Inventory
        Inventory inventory             = new Inventory(pizzaOrder.getPizzaStore().getId(), false);
        CrustInventory crustInventory   = new CrustInventory(inventory, _crustType);
        SauceInventory sauceInventory   = new SauceInventory(inventory, _sauceType);
        CheeseInventory cheeseInventory = new CheeseInventory(inventory, _cheeseType);

        crustInventory.subtract(1);
        sauceInventory.subtract(1);
        cheeseInventory.subtract(1);

        // Persist Pizza
        pizzaOrm = new PizzaOrm(_name, _amountInCents, pizzaOrder.getId(), crustTypeOrm.getId(), sauceTypeOrm.getId(), cheeseTypeOrm.getId(), false);
    }

    /**
     * Lookup constructor
     *
     * @param _id - Pizza's unique identifier
     */
    public Pizza(long _id) throws Exception {
        pizzaOrm        = new PizzaOrm(_id);
        pizzaOrder      = new PizzaOrder(pizzaOrm.getPizzaOrderId());
        crustTypeOrm    = new EnumCrustTypeOrm(pizzaOrm.getEnumCrustId());
        sauceTypeOrm    = new EnumSauceTypeOrm(pizzaOrm.getEnumSauceId());
        cheeseTypeOrm   = new EnumCheeseTypeOrm(pizzaOrm.getEnumCheeseId());
    }

    /**
     * PizzaOrm constructor
     *
     * @param _pizzaOrm - Pizza ORM
     */
    private Pizza(PizzaOrm _pizzaOrm) throws Exception {
        pizzaOrm        = _pizzaOrm;
        pizzaOrder      = new PizzaOrder(pizzaOrm.getPizzaOrderId());
        crustTypeOrm    = new EnumCrustTypeOrm(pizzaOrm.getEnumCrustId());
        sauceTypeOrm    = new EnumSauceTypeOrm(pizzaOrm.getEnumSauceId());
        cheeseTypeOrm   = new EnumCheeseTypeOrm(pizzaOrm.getEnumCheeseId());
    }

    /**
     * Get list of pizzas for order
     *
     * @param _pizzaOrder - Pizza order
     */
    public static ArrayList<Pizza> LookupByOrder(PizzaOrder _pizzaOrder) throws Exception {
        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();

        ArrayList<PizzaOrm> pizzaOrms = PizzaOrm.LookupByPizzaOrderId(_pizzaOrder.getId());

        for (PizzaOrm pizzaOrm : pizzaOrms) {
            pizzas.add(new Pizza(pizzaOrm));
        }

        return pizzas;
    }

    /**
     * Delete this pizza and all of it's toppings.  Update inventory.
     */
    public void delete() throws Exception {
        // Adjust Inventory
        Inventory inventory             = new Inventory(pizzaOrder.getPizzaStore().getId(), false);
        CrustInventory crustInventory   = new CrustInventory(inventory, crustTypeOrm.getType());
        SauceInventory sauceInventory   = new SauceInventory(inventory, sauceTypeOrm.getType());
        CheeseInventory cheeseInventory = new CheeseInventory(inventory, cheeseTypeOrm.getType());

        crustInventory.add(1);
        sauceInventory.add(1);
        cheeseInventory.add(1);

        ArrayList<Topping> toppings = Topping.LookupByPizza(this);
        for (Topping topping : toppings) {
            topping.delete();
        }

        // Delete
        pizzaOrm.delete(getId());
    }

    public long getId() {
        return pizzaOrm.getId();
    }

    @JsonIgnore
    public PizzaOrder getPizzaOrder() {
        return pizzaOrder;
    }

    public String getName() {
        return pizzaOrm.getName();
    }

    public int getAmountInCents() {
        return pizzaOrm.getAmountInCents();
    }

    public String getCrustType() {
        return crustTypeOrm.getType();
    }

    public String getSauceType() {
        return sauceTypeOrm.getType();
    }

    public String getCheeseType() {
        return cheeseTypeOrm.getType();
    }

    public int getCost() throws Exception {
        int cost = getAmountInCents();

        cost += crustTypeOrm.getAmountInCents();
        cost += sauceTypeOrm.getAmountInCents();
        cost += cheeseTypeOrm.getAmountInCents();

        ArrayList<Topping> toppings = Topping.LookupByPizza(this);

        for (Topping topping : toppings) {
            cost += topping.getAmountInCents();
        }

        return cost;
    }

    public String getToppings() throws Exception {
        ArrayList<Topping> toppings = Topping.LookupByPizza(this);

        boolean addComma = false;
        StringBuilder toppingsString = new StringBuilder();
        for (Topping topping : toppings) {
            if (addComma) {
                toppingsString.append(",");
            }
            addComma = true;
            toppingsString.append(topping.getToppingType());
        }

        return toppingsString.toString();
    }
}
