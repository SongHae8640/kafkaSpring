package com.example.kafkaspring.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KafkaProducerTest {

    @Autowired
    private KafkaProducer kafkaProducer;

    @BeforeEach
    void startLog() {
        System.out.println("KafkaProducerTest :: startLog");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void endLog() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("KafkaProducerTest :: endLog");
    }


    @Test
    void sendMessage() {
        kafkaProducer.sendMessage("Hello World!1");
        kafkaProducer.sendMessage("Hello World!2");
        kafkaProducer.sendMessage("Hello World!3");
        kafkaProducer.sendMessage("Hello World!4");

    }

}