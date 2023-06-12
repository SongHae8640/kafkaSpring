package com.example.kafkaspring.alarmSubscription;

import com.example.kafkaspring.board.BoardCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = AlarmSubscriptionService.class)
class AlarmServiceTest {

    @Autowired
    private AlarmSubscriptionService alarmSubscriptionService;


    @Test
    void 알람구독_및_확인(){
        //given
        List<Integer> userIdList = List.of(1,2,3,4,5);
        BoardCategory category = BoardCategory.JOIN;

        //when
        userIdList.forEach(userId -> alarmSubscriptionService.subscribeAlarm(userId, category));

        //then
        alarmSubscriptionService.findAlarmSubscriberByCategory(category)
                .forEach(userId -> {
                    System.out.println(userId);
                    assertTrue(userIdList.contains(userId));
                });
    }

}