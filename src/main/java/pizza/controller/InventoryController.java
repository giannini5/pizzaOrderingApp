package pizza.controller;

import org.springframework.web.bind.annotation.*;
import pizza.domain.*;
import pizza.orm.EnumToppingTypeOrm;
import pizza.service.database.Database;

import java.sql.Date;

@RestController
public class InventoryController {

    // TODO: Add authorization and authentication
    // TODO: Use UseCase pattern to reduce size of controllers

    // TODO: Should be a post, but easier for me to test in browser it it's a get ... I know, not fair!!!
    // @PostMapping("/pizza_order")
    @GetMapping("/inventory")
    public BaseInventory inventory(
            @RequestParam(value="pizza_store_id") long pizzaStoreId,
            @RequestParam(value="inventory_type") String inventoryType,
            @RequestParam(value="inventory_sub_type") String inventorySubType,
            @RequestParam(value="operation") String operation,
            @RequestParam(value="amount") int amount) throws Exception {
        try {
            PizzaStore pizzaStore = new PizzaStore(pizzaStoreId);
            Inventory inventory = new Inventory(pizzaStore.getId(), false);

            // Construct type of inventory
            BaseInventory baseInventory = inventory.getSubInventory(inventoryType, inventorySubType);

            // Perform operation
            baseInventory.update(operation, amount);

            Database.getInstance().commmit();

            return baseInventory;
        } catch (Exception e) {
            Database.getInstance().rollback();
            throw e;
        }
    }

    // @PostMapping("/pizza_order")
    @GetMapping("/inventory/add_topping")
    public ToppingInventory addTopping(
            @RequestParam(value="pizza_store_id") long pizzaStoreId,
            @RequestParam(value="topping_type") String toppingType,
            @RequestParam(value="amount_in_cents") int amountInCents,
            @RequestParam(value="quantity") int quantity) throws Exception {
        try {
            PizzaStore pizzaStore = new PizzaStore(pizzaStoreId);
            Inventory inventory = new Inventory(pizzaStore.getId(), false);

            // Create new topping
            EnumToppingTypeOrm toppingTypeOrm = new EnumToppingTypeOrm(toppingType, amountInCents, false);

            // Create new topping inventory
            ToppingInventory toppingInventory = new ToppingInventory(inventory, toppingType, quantity);

            Database.getInstance().commmit();

            return toppingInventory;
        } catch (Exception e) {
            Database.getInstance().rollback();
            throw e;
        }
    }

    @GetMapping("/inventory/{pizzaStoreId}/{inventoryType}/{inventorySubType}")
    public BaseInventory inventory(@PathVariable long pizzaStoreId, @PathVariable String inventoryType,
                               @PathVariable String inventorySubType) throws Exception {
        PizzaStore pizzaStore = new PizzaStore(pizzaStoreId);
        Inventory inventory = new Inventory(pizzaStore.getId(), false);
        return inventory.getSubInventory(inventoryType, inventorySubType);
    }
}