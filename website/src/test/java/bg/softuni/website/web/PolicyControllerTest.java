package bg.softuni.website.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class PolicyControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    WebApplicationContext context;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    void info() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/policies"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(view().name("policies-page"));
    }
    
    @Test
    void shippingInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/policies/shipping"))
                .andExpect(view().name("shipping-policies-page"));
    }
    
    @Test
    void returnsAndExchangePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/policies/returnsAndExchanges"))
                .andExpect(view().name("returns-and-exchanges-page"));
    }
    
    @Test
    void payment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/policies/payments"))
                .andExpect(view().name("payments-page"));
    }
}