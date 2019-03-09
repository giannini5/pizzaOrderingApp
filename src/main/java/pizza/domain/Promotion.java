package pizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pizza.orm.EnumPromotionTypeOrm;
import pizza.orm.PromotionOrm;

import java.sql.Date;

public class Promotion {

    private PromotionOrm         promotionOrm;
    private EnumPromotionTypeOrm promotionTypeOrm;
    private PizzaStore           pizzaStore;

    /**
     * Create constructor
     *
     * @param _pizzaStore   - Pizza Store
     * @param _name         - Name of promotion
     * @param _startDate    - Promotion start date
     * @param _endDate      - Promotion end date
     * @param _type         - Promotion type
     * @param _value        - Promotion value
     */
    public Promotion(PizzaStore _pizzaStore, String _name, Date _startDate, Date _endDate, String _type,
                     int _value) throws Exception {
        pizzaStore = _pizzaStore;

        // Get the type of promotion
        promotionTypeOrm = new EnumPromotionTypeOrm(_type);

        // Create the promotion
        promotionOrm = new PromotionOrm(pizzaStore.getId(), _name, _startDate, _endDate, promotionTypeOrm.getId(),
                _value, false);
    }

    /**
     * Lookup by id constructor
     *
     * @param _id   - Promotion's unique identifier
     */
    public Promotion(long _id) throws Exception {
        promotionOrm        = new PromotionOrm(_id);
        promotionTypeOrm    = new EnumPromotionTypeOrm(promotionOrm.getEnumPromotionTypeId());
        pizzaStore          = new PizzaStore(promotionOrm.getPizzaStoreId());
    }

    /**
     * Lookup by name constructor
     *
     * @param _pizzaStore   - Pizza Store
     * @param _name         - Name of promotion
     */
    public Promotion(PizzaStore _pizzaStore, String _name) throws Exception {
        pizzaStore          = _pizzaStore;
        promotionOrm        = new PromotionOrm(_name);
        promotionTypeOrm    = new EnumPromotionTypeOrm(promotionOrm.getEnumPromotionTypeId());
    }

    public long getId() {
        return promotionOrm.getId();
    }

    public String getName() {
        return promotionOrm.getName();
    }

    public String getType() {
        return promotionTypeOrm.getType();
    }

    public Date getStartDate() {
        return promotionOrm.getStartDate();
    }

    public Date getEndDate() {
        return promotionOrm.getEndDate();
    }

    public int getValue() {
        return promotionOrm.getValue();
    }

    @JsonIgnore
    public PizzaStore getPizzaStore() {
        return pizzaStore;
    }
}
