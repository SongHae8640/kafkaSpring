package com.example.kafkaspring.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleKafkaProducerTest {

    @Autowired
    private SampleKafkaProducer kafkaProducer;


    @Test
     void 단건_보낼때() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        kafkaProducer.sendMessage("Hello World!");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    void 여러건_보낼때() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 1000; i++) {
            kafkaProducer.sendMessage("Hello World! " + i);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}