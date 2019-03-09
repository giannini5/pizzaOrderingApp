package pizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pizza.orm.EnumPromotionTypeOrm;
import pizza.orm.PizzaOrderOrm;

import java.sql.Date;
import java.util.ArrayList;

public class PizzaOrder {

    private PizzaOrderOrm   pizzaOrderOrm;
    private PizzaStore      pizzaStore;
    private Promotion       promotion;

    /**
     * Create constructor
     *
     * @param _pizzaStore   - Pizza Store
     * @param _promotion    - Promotion
     * @param _orderDate    - Order date
     * @param _name         - Name of the store
     * @param _address      - Address of the store
     * @param _phone        - Phone
     */
    public PizzaOrder(PizzaStore _pizzaStore, Promotion _promotion, Date _orderDate,
                      String _name, String _address, String _phone) throws Exception {
        pizzaStore      = _pizzaStore;
        promotion       = _promotion;
        pizzaOrderOrm   = new PizzaOrderOrm(pizzaStore.getId(), promotion.getId(), _orderDate,
                _name, _address, _phone, false);
        // TODO: only allow promotion if it is current
    }

    /**
     * Lookup constructor
     *
     * @param _id   - Store's unique identifier
     */
    public PizzaOrder(long _id) throws Exception {
        pizzaOrderOrm   = new PizzaOrderOrm(_id);
        pizzaStore      = new PizzaStore(pizzaOrderOrm.getPizzaStoreId());
        promotion       = new Promotion(pizzaOrderOrm.getPromotionId());
    }

    public long getId() {
        return pizzaOrderOrm.getId();
    }

    @JsonIgnore
    public PizzaStore getPizzaStore() {
        return pizzaStore;
    }

    @JsonIgnore
    public Promotion getPromotion() {
        return promotion;
    }

    public Date getOrderDate() {
        return pizzaOrderOrm.getOrderDate();
    }

    public String getName() {
        return pizzaOrderOrm.getName();
    }

    public String getAddress() {
        return pizzaOrderOrm.getAddress();
    }

    public String getPhone() {
        return pizzaOrderOrm.getPhone();
    }

    public int getCost() throws Exception {
        int cost = 0;

        // Get cost of pizzas
        ArrayList<Pizza> pizzas = Pizza.LookupByOrder(this);
        for (Pizza pizza : pizzas) {
            cost += pizza.getCost();
        }

        // Apply promotion
        cost -= getDiscount(cost);

        return cost > 0 ? cost : 0;
    }

    private int getDiscount(int cost) {
        switch (promotion.getType()) {
            case EnumPromotionTypeOrm.DOLLARS_OFF:
                return promotion.getValue();
            case EnumPromotionTypeOrm.PERCENT_OFF:
                return cost * promotion.getValue();
        }

        // TODO: log error here for unrecognized promotion type
        System.out.println("Unrecognized promition type: " + promotion.getType());
        return 0;
    }
}
