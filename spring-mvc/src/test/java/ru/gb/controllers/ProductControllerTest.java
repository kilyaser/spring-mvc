package ru.gb.controllers;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testProductController() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(content().string(
                        containsString("Product list")));
    }
    @Test
    public void testProductControllerAddProduct() throws Exception {
        mockMvc.perform(get("/add-product"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-product"))
                .andExpect(content().string(
                        containsString("Add product")));
    }
}
