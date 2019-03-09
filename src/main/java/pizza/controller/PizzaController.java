package pizza.controller;

import org.springframework.web.bind.annotation.*;
import pizza.domain.*;
import pizza.service.database.Database;

@RestController
public class PizzaController {

    // TODO: Add authorization and authentication
    // TODO: Use UseCase pattern to reduce size of controllers

    // TODO: Should be a post, but easier for me to test in browser it it's a get ... I know, not fair!!!
    // @PostMapping("/pizza")
    @GetMapping("/pizza")
    public Pizza pizza(
            @RequestParam(value="pizza_order_id") long pizzaOrderId,
            @RequestParam(value="name") String name,
            @RequestParam(value="crust_type") String crustType,
            @RequestParam(value="sauce_type") String sauceType,
            @RequestParam(value="cheese_type") String cheeseType,
            @RequestParam(value="toppings") String toppings) throws Exception {
        try {
            // Create pizzas, all pizzas have a base price of $5 and one-size fits all
            // TODO: Add pizzaSize
            PizzaOrder pizzaOrder = new PizzaOrder(pizzaOrderId);
            Pizza pizza = new Pizza(pizzaOrder, name, 500, crustType, sauceType, cheeseType);

            // Add toppings
            String[] toppingTypes = toppings.split(",");
            for (String toppingType : toppingTypes) {
                Topping topping = new Topping(pizza, toppingType);
            }

            Database.getInstance().commmit();

            return pizza;
        } catch (Exception e) {
            Database.getInstance().rollback();
            throw e;
        }
    }

    // @PostMapping("/pizza/delete")
    @GetMapping("/pizza/delete/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        try {
            Pizza pizza = new Pizza(id);
            pizza.delete();

            Database.getInstance().commmit();

            return "Deleted";
        } catch (Exception e) {
            Database.getInstance().rollback();
            throw e;
        }
    }

    @GetMapping("/pizza/{id}")
    public Pizza pizza(@PathVariable Long id) throws Exception {
        return new Pizza(id);
    }
}