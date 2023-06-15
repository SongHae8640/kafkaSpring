package com.example.kafkaspring.alarmSubscription;

import com.example.kafkaspring.board.BoardCategory;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AlarmControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext ctx;

    @Autowired
    AlarmSubscriptionService alarmSubscriptionService;


    @Test
    void 카테고리별_구독자_조회() throws Exception {
        // Given
        List<Integer> userIdList = List.of(1,2,3,4,5);
        BoardCategory category = BoardCategory.JOIN;
        userIdList.forEach(userId -> alarmSubscriptionService.subscribeAlarm(userId, category));

        // prepare api request
        String url ="/api/alarm-subscriptions?category=";



        // When
        ResultActions actions = mockMvc.perform(get(url+category.name())
                .contentType(MediaType.APPLICATION_JSON)
        );


        // Then
        System.out.println("------------------");
        actions.andExpect(status().isOk())
                .andExpect(result -> {
                    String contentAsString = result.getResponse().getContentAsString();
                    System.out.println("contentAsString = " + contentAsString);
                })
                .andExpect(jsonPath("*").isArray())
                .andExpect(jsonPath("$.length()").value(userIdList.size()))
                .andExpect(jsonPath("$[*]").value(Matchers.containsInAnyOrder(userIdList.toArray())))
        ;

    }

}