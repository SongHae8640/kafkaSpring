package com.example.kafkaspring.alarmSubscription;

import com.example.kafkaspring.board.BoardCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alarm-subscriptions")
public class AlarmSubscriptionController {

    private final AlarmSubscriptionService alarmSubscriptionService;

    @GetMapping
    public List<Integer> findAlarmSubscriberByCategory(String category) {
        return alarmSubscriptionService.findAlarmSubscriberByCategory(BoardCategory.of(category));
    }
}