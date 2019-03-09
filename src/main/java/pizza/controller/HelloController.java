package pizza.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "<html><body><strong>Pizza Store Greetings</strong></body></html>";
    }

    /*
    Create Store
        http://localhost:8080/pizza_store?name=Linos&address=2880%20springvale%20ct
            {"name":"Linos","id":2,"address":"2880 springvale ct"}

    Add inventory
        http://localhost:8080/inventory?pizza_store_id=2&inventory_type=crust&inventory_sub_type=thin&operation=add&amount=50
            {"id":3,"type":"thin","count":50}
        http://localhost:8080/inventory?pizza_store_id=2&inventory_type=crust&inventory_sub_type=thick&operation=add&amount=50
            {"id":4,"type":"thick","count":50}
        http://localhost:8080/inventory?pizza_store_id=2&inventory_type=sauce&inventory_sub_type=marinara&operation=add&amount=50
            {"id":2,"type":"marinara","count":50}
        http://localhost:8080/inventory?pizza_store_id=2&inventory_type=cheese&inventory_sub_type=mozzarella&operation=add&amount=50
            {"id":2,"type":"mozzarella","count":50}
        http://localhost:8080/inventory?pizza_store_id=2&inventory_type=topping&inventory_sub_type=pepperoni&operation=add&amount=50
            {"id":3,"type":"pepperoni","count":50}
        http://localhost:8080/inventory?pizza_store_id=2&inventory_type=topping&inventory_sub_type=mushroom&operation=add&amount=50
            {"id":4,"type":"mushroom","count":50}

    Order Pizza
        http://localhost:8080/pizza_order?pizza_store_id=2&promotion_name=2_DOLLARS_OFF&order_date=2019-03-04&name=Dave&address=123&phone=456
            {"name":"Dave","id":2,"address":"123","orderDate":"2019-03-04","phone":"456","cost":0}
        http://localhost:8080/pizza?pizza_order_id=2&name=Cheese&crust_type=thin&sauce_type=marinara&cheese_type=mozzarella&toppings=pepperoni,mushroom
            {"name":"Cheese","id":2,"amountInCents":500,"crustType":"thin","sauceType":"marinara","cheeseType":"mozzarella","cost":800,"toppings":"pepperoni,mushroom"}

    Verify pizza and order look good
        http://localhost:8080/pizza_order/2
            {"name":"Dave","id":2,"address":"123","orderDate":"2019-03-04","phone":"456","cost":798}
        http://localhost:8080/pizza/2
            {"name":"Cheese","id":2,"cost":800,"amountInCents":500,"crustType":"thin","sauceType":"marinara","cheeseType":"mozzarella"}

    Check Inventory
        http://localhost:8080/inventory/2/crust/thin
            {"id":3,"type":"thin","count":49}
        http://localhost:8080/inventory/2/crust/thick
            {"id":4,"type":"thick","count":50}
        http://localhost:8080/inventory/2/sauce/marinara
            {"id":2,"type":"marinara","count":49}
        http://localhost:8080/inventory/2/cheese/mozzarella
            {"id":2,"type":"mozzarella","count":49}
        http://localhost:8080/inventory/2/topping/pepperoni
            {"id":3,"type":"pepperoni","count":49}
        http://localhost:8080/inventory/2/topping/mushroom
            {"id":4,"type":"mushroom","count":49}

    Delete the pizza from the order and verify inventory updates
        http://localhost:8080/pizza/delete/2

    Re-run above inventory checks

    Add a topping
        http://localhost:8080/inventory/add_topping?pizza_store_id=2&topping_type=jalapenos&amount_in_cents=25&quantity=100

    Create a new pizza with the new toppling
        http://localhost:8080/pizza?pizza_order_id=2&name=Cheese&crust_type=thin&sauce_type=marinara&cheese_type=mozzarella&toppings=pepperoni,mushroom,jalapenos
    */

}