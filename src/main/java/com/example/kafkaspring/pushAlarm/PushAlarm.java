package com.example.kafkaspring.pushAlarm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class PushAlarm {
    private Integer userId;
    private String content;
}