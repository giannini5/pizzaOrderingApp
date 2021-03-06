/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pizza.controller_tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PizzaControllerTests {
    @BeforeClass
    public static void classSetup() throws Exception {
        TestHelper.primeDatabase();
    }

    @AfterClass
    public static void classTeardown() throws Exception {
        Database.getInstance().dropAllObjects();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createPizza() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pizza?pizza_order_id=1&name=Cheese&crust_type=thin&sauce_type=marinara&cheese_type=mozzarella&toppings=pepperoni,mushroom");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals(2, jsonObject.getLong("id"));
        assertEquals(500, jsonObject.getInt("amountInCents"));
        assertEquals("Cheese", jsonObject.getString("name"));
        assertEquals("thin", jsonObject.getString("crustType"));
        assertEquals("marinara", jsonObject.getString("sauceType"));
        assertEquals("mozzarella", jsonObject.getString("cheeseType"));
    }

    @Test
    public void lookupPizza() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pizza/1");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals(1, jsonObject.getLong("id"));
        assertEquals(5150, jsonObject.getInt("cost"));
        assertEquals("DeepDish", jsonObject.getString("name"));
        assertEquals(5000, jsonObject.getInt("amountInCents"));
        assertEquals("thin", jsonObject.getString("crustType"));
        assertEquals("marinara", jsonObject.getString("sauceType"));
        assertEquals("mozzarella", jsonObject.getString("cheeseType"));
    }

}
