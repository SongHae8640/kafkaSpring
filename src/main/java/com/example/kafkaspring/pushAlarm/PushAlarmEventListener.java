package com.example.kafkaspring.pushAlarm;

import com.example.kafkaspring.alarmSubscription.AlarmSubscriptionService;
import com.example.kafkaspring.board.BoardCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PushAlarmEventListener {

    private final AlarmSubscriptionService alarmService;
    private final PushAlarmService pushAlarmService;

    @Async
    @EventListener
    public void producePushAlarmByCategorySubscriber(BoardCreateEvent boardCreateEvent) {
        List<PushAlarm> pushAlarmList = alarmService.findAlarmSubscriberByCategory(boardCreateEvent.getCategory())
                .stream()
                .map(userId -> PushAlarm.builder()
                        .userId(userId)
                        .content(boardCreateEvent.getCategory().getCategory() + "에 새로운 글이 등록되었습니다.")
                        .build())
                .collect(Collectors.toList());

        pushAlarmService.saveList(pushAlarmList);
    }
}