package pizza.controller;

import org.springframework.web.bind.annotation.*;
import pizza.domain.*;
import pizza.service.database.Database;

import java.sql.Date;

@RestController
public class PizzaOrderController {

    // TODO: Add authorization and authentication
    // TODO: Use UseCase pattern to reduce size of controllers

    // TODO: Should be a post, but easier for me to test in browser it it's a get ... I know, not fair!!!
    // @PostMapping("/pizza_order")
    @GetMapping("/pizza_order")
    public PizzaOrder pizzaOrder(
            @RequestParam(value="pizza_store_id") long pizzaStoreId,
            @RequestParam(value="promotion_name") String promotionName,
            @RequestParam(value="order_date") String orderDateString,
            @RequestParam(value="name") String name,
            @RequestParam(value="address") String address,
            @RequestParam(value="phone") String phone) throws Exception {
        try {
            PizzaStore pizzaStore = new PizzaStore(pizzaStoreId);
            Promotion promotion = new Promotion(pizzaStore, promotionName);
            Date orderDate = Date.valueOf(orderDateString);
            PizzaOrder pizzaOrder = new PizzaOrder(pizzaStore, promotion, orderDate, name, address, phone);

            Database.getInstance().commmit();

            return pizzaOrder;
        } catch (Exception e) {
            Database.getInstance().rollback();
            throw e;
        }
    }

    @GetMapping("/pizza_order/{id}")
    public PizzaOrder pizzaOrder(@PathVariable Long id) throws Exception {
        return new PizzaOrder(id);
    }
}