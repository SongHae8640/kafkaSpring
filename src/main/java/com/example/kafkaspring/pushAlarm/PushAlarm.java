package com.example.kafkaspring.pushAlarm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
@Getter
public class PushAlarm {
    private Integer userId;
    private String content;
}