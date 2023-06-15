package com.example.kafkaspring.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleKafkaController {

    private final SampleKafkaProducer kafkaProducer;

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