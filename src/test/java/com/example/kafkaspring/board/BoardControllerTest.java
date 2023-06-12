package com.example.kafkaspring.board;

import com.example.kafkaspring.alarmSubscription.AlarmSubscriptionService;
import com.example.kafkaspring.board.dto.BoardDto;
import com.example.kafkaspring.pushAlarm.PushAlarmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext ctx;

    @Autowired
    AlarmSubscriptionService alarmSubscriptionService;

    @Autowired
    PushAlarmService pushAlarmService;

    @Autowired


    private final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }



    @Test
    void 게시판_글쓰기() throws Exception {
        // Given
        String url ="/api/boards";
        BoardDto boardDto = BoardDto.builder()
                .category("JOIN")
                .title("오늘 날씨 좋네요")
                .content("골프치기 좋은 날씨네요. 조인하실 분?")
                .build();

        // When
        ResultActions actions = mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(boardDto))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // Then
        System.out.println("------------------");
        actions.andExpect(status().isOk())
                .andExpect(result -> {
                    String contentAsString = result.getResponse().getContentAsString();
                    System.out.println("contentAsString = " + contentAsString);
                })
                .andExpect(jsonPath("$.category").value(boardDto.getCategory()))
                .andExpect(jsonPath("$.title").value(boardDto.getTitle()))
                .andExpect(jsonPath("$.content").value(boardDto.getContent()))
                .andExpect(jsonPath("$.id").exists());
    }


    @Test
    void 글쓰기_후_푸쉬알림_확인() throws Exception {
        /**
         * Given
         */
        // check PushAlarmList
        assertEquals(0, pushAlarmService.findAll().size());

        // subscribeAlarm
        List<Integer> userIdList = List.of(1,2,3,4,5);
        BoardCategory category = BoardCategory.JOIN;
        userIdList.forEach(userId -> alarmSubscriptionService.subscribeAlarm(userId, category));

        // prepare api request
        String url ="/api/boards";
        BoardDto boardDto = BoardDto.builder()
                .category("JOIN")
                .title("오늘 날씨 좋네요")
                .content("골프치기 좋은 날씨네요. 조인하실 분?")
                .build();



        /**
         * When
         */
        ResultActions actions = mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(boardDto))
                .contentType(MediaType.APPLICATION_JSON)
        );


        /**
         * Then
         */
        System.out.println("------------------");

        // check PushAlarmList
        assertEquals(userIdList.size(), pushAlarmService.findAll().size());

        // check api response
        actions.andExpect(status().isOk())
                .andExpect(result -> {
                    String contentAsString = result.getResponse().getContentAsString();
                    System.out.println("contentAsString = " + contentAsString);
                })
                .andExpect(jsonPath("$.category").value(boardDto.getCategory()))
                .andExpect(jsonPath("$.title").value(boardDto.getTitle()))
                .andExpect(jsonPath("$.content").value(boardDto.getContent()))
                .andExpect(jsonPath("$.id").exists());


    }

}