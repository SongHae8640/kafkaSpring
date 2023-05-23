package com.example.kafkaspring.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class KafkaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext ctx;



    @Test
    void 카프카_message_produce() throws Exception {
        // Given
        String url ="/sample";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> params = new HashMap<>();
        params.put("message","hello");
        System.out.println(objectMapper.writeValueAsString(params));


        // When
        ResultActions actions = mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(params))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );


        // Then
        System.out.println("------------------");
        actions.andDo(print());
        actions.andExpect(status().isOk())
                .andExpect(result -> {
                    System.out.println("status :: " + result.getResponse().getStatus());
                    System.out.println("body :: " + result.getResponse().getContentAsString());
                });
    }


    @Test
    void api_check() throws Exception {
        // Given
        String url ="/sample";


        // When
        ResultActions actions = mockMvc.perform(get(url)
        );


        // Then
        System.out.println("------------------");
        actions.andDo(print());
        actions.andExpect(status().isOk())
                .andExpect(result -> {
                    System.out.println("status :: " + result.getResponse().getStatus());
                    System.out.println("body :: " + result.getResponse().getContentAsString());
                });
    }

}