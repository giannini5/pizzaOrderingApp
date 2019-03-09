package pizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pizza.orm.*;

import java.util.ArrayList;

public class Topping {

    private Pizza               pizza;
    private ToppingOrm          toppingOrm;
    private EnumToppingTypeOrm  toppingTypeOrm;

    /**
     * Create constructor
     *
     * @param _pizza       - Pizza
     * @param _toppingType - Type of topping
     */
    public Topping(Pizza _pizza, String _toppingType) throws Exception {
        pizza           = _pizza;
        toppingTypeOrm  = new EnumToppingTypeOrm(_toppingType);

        // Adjust Inventory
        Inventory inventory                 = new Inventory(pizza.getPizzaOrder().getPizzaStore().getId(), false);
        ToppingInventory toppingInventory   = new ToppingInventory(inventory, _toppingType);
        toppingInventory.subtract(1);

        // Create topping
        toppingOrm = new ToppingOrm(pizza.getId(), toppingTypeOrm.getId(), false);
    }

    /**
     * Lookup constructor
     *
     * @param _id - Topping's unique identifier
     */
    public Topping(long _id) throws Exception {
        toppingOrm      = new ToppingOrm(_id);
        pizza           = new Pizza(toppingOrm.getPizzaId());
        toppingTypeOrm  = new EnumToppingTypeOrm(toppingOrm.getEnumTopplingId());
    }

    /**
     * ToppingOrm constructor
     *
     * @param _toppingOrm - Topping ORM
     */
    private Topping(ToppingOrm _toppingOrm) throws Exception {
        toppingOrm      = _toppingOrm;
        pizza           = new Pizza(toppingOrm.getPizzaId());
        toppingTypeOrm  = new EnumToppingTypeOrm(toppingOrm.getEnumTopplingId());
    }

    /**
     * Get list of toppings for pizza
     *
     * @param _pizza - Pizza with zero or more toppings
     *
     * @return ArrayList
     */
    public static ArrayList<Topping> LookupByPizza(Pizza _pizza) throws Exception {
        ArrayList<Topping> toppings = new ArrayList<Topping>();

        ArrayList<ToppingOrm> toppingOrms = ToppingOrm.LookupByPizzaId(_pizza.getId());

        for (ToppingOrm toppingOrm : toppingOrms) {
            toppings.add(new Topping(toppingOrm));
        }

        return toppings;
    }

    /**
     * Delete this topping.  Update inventory.
     */
    public void delete() throws Exception {
        // Adjust Inventory
        Inventory inventory               = new Inventory(pizza.getPizzaOrder().getPizzaStore().getId(), false);
        ToppingInventory toppingInventory = new ToppingInventory(inventory, getToppingType());
        toppingInventory.add(1);

        // Delete
        toppingOrm.delete(getId());
    }


    public long getId() {
        return toppingOrm.getId();
    }

    @JsonIgnore
    public Pizza getPizza() {
        return pizza;
    }

    public String getToppingType() {
        return toppingTypeOrm.getType();
    }

    public int getAmountInCents() {
        return toppingTypeOrm.getAmountInCents();
    }
}
