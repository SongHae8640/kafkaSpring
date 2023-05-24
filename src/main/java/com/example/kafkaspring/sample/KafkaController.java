package com.example.kafkaspring.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    @PostMapping
    public String sample(@RequestBody SampleDto sampleDto) {
        kafkaProducer.sendMessage(sampleDto.getMessage());
        return "success";
    }

    @GetMapping
    public String apiCheck() {
        return "success";
    }
}