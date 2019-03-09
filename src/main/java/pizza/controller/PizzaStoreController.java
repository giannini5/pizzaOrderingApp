package pizza.controller;

import org.springframework.web.bind.annotation.*;
import pizza.domain.*;
import pizza.service.database.Database;

@RestController
public class PizzaStoreController {

    // TODO: Add authorization and authentication
    // TODO: Use UseCase pattern to reduce size of controllers

    // TODO: Should be a post, but easier for me to test in browser it it's a get ... I know, not fair!!!
    // @PostMapping("/pizzaStore")
    @GetMapping("/pizza_store")
    public PizzaStore pizzaStore(@RequestParam(value="name") String name, @RequestParam(value="address") String address) throws Exception {
        try {
            PizzaStore pizzaStore = new PizzaStore(name, address);

            // Create default Inventory and Promotion for store
            pizzaStore.createDefaultInventory();
            pizzaStore.createDefaultPromotion();

            Database.getInstance().commmit();

            return pizzaStore;
        } catch (Exception e) {
            Database.getInstance().rollback();
            throw e;
        }
    }

    @GetMapping("/pizza_store/{id}")
    public PizzaStore pizzaStore(@PathVariable Long id) throws Exception {
        return new PizzaStore(id);
    }
}