package com.cndev.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicListener {

    @KafkaListener(topics = "dev", groupId = "dev1")
    public void  listener(String data){
        System.out.println(data);
    }

}
