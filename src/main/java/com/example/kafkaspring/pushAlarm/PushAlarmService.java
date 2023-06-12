package com.example.kafkaspring.pushAlarm;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PushAlarmService {

    List<PushAlarm> pushAlarmRepository = new ArrayList<>();

    public void saveList(List<PushAlarm> pushAlarmList) {
        pushAlarmRepository.addAll(pushAlarmList);
        pushAlarmList.forEach(pushAlarm -> System.out.println("savePushAlarm :: pushAlarm = " + pushAlarm));
    }

    public List<PushAlarm> findAll() {
        return pushAlarmRepository;
    }
}