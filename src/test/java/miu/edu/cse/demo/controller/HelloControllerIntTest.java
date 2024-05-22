package miu.edu.cse.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(HelloController.class)
class HelloControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void hello() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("Hello, World!", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void helloWithName() throws Exception {
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello?name=Bright");
//        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
//        assertEquals("Hello, Bright!", mvcResult.getResponse().getContentAsString());
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name=Tom"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string("Hello, Tom!"));
    }
}