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
public class InventoryControllerTests {
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
    public void createInventory() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inventory?pizza_store_id=1&inventory_type=cheese&inventory_sub_type=mozzarella&operation=add&amount=10");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"id\":1,\"type\":\"mozzarella\",\"count\":15}";

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals(1, jsonObject.getLong("id"));
        assertEquals("mozzarella", jsonObject.getString("type"));
        assertEquals(15, jsonObject.getInt("count"));
    }

    @Test
    public void lookupInventory() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inventory/1/crust/thin");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"inventory\":{\"id\":1},\"id\":1,\"type\":\"thin\",\"count\":5}";

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals(1, jsonObject.getLong("id"));
        assertEquals("thin", jsonObject.getString("type"));
        assertEquals(5, jsonObject.getInt("count"));
    }
}
