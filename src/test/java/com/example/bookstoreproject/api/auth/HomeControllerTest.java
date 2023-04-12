package com.example.bookstoreproject.api.auth;

import com.example.bookstoreproject.api.AbstractControllerTest;
import com.example.bookstoreproject.api.WithMockAdmin;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(HomeController.class)
class HomeControllerTest extends AbstractControllerTest {

    @Test
    @WithMockAdmin
    public void shouldLoginPage_OK() throws Exception {
        get("/google")
                .andExpect(status().isOk())
                .andExpect(view().name("index.html"));
    }
}