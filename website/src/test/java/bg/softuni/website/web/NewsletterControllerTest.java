package bg.softuni.website.web;

import bg.softuni.website.models.dtos.NewsletterDto;
import bg.softuni.website.services.NewsletterService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class NewsletterControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;
    
    @MockBean
    private NewsletterService newsletterService;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    void newsletterTest() throws Exception {
        NewsletterDto newsletterDto = new NewsletterDto();
        newsletterDto.setNewsletterEmail("validEmail@gmail.com");
        
        mockMvc.perform(MockMvcRequestBuilders.post("/newsletter")
                        .flashAttr("newsletterDto", newsletterDto))
                .andExpect(flash().attributeExists("successNewsletter"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        
        verify(newsletterService, times(1)).persistEmail(anyString());
        
        newsletterService.deleteNewsletterEmail(newsletterDto.getNewsletterEmail());
        
        verify(newsletterService, times(1)).deleteNewsletterEmail(anyString());
    }
    
    @Test
    void newsletterInvalidEmailTest() throws Exception {
        NewsletterDto newsletterDto = new NewsletterDto();
        newsletterDto.setNewsletterEmail("invalid");
        
        mockMvc.perform(MockMvcRequestBuilders.post("/newsletter")
                        .flashAttr("newsletterDto", newsletterDto))
                .andExpect(flash().attributeExists("newsletterDto"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.newsletterDto"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
    
    
}