package com.example.kafkaspring.alarmSubscription;

import com.example.kafkaspring.board.BoardCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlarmSubscriptionService {

    private final Map<String,List<Integer>> alarmSubscriberRepository = new HashMap<>();

    public List<Integer> findAlarmSubscriberByCategory(BoardCategory category) {
        intiValueByCategory(category);
        return alarmSubscriberRepository.get(category.getCategory());
    }

    public void subscribeAlarm(Integer userId, BoardCategory category) {
        intiValueByCategory(category);
        alarmSubscriberRepository.get(category.getCategory()).add(userId);
    }

    private void intiValueByCategory(BoardCategory category) {
        if(alarmSubscriberRepository.get(category.getCategory()) == null){
            alarmSubscriberRepository.put(category.getCategory(), new ArrayList<>());
        }
    }
}
