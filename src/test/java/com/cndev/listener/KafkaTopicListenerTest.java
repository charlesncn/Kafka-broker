package com.cndev.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaTopicListenerTest {
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;
    @Mock
    private Acknowledgment acknowledgment;

    @Test
    void testListener() {
         String testData = "J-unit test";
        ConsumerRecord<String, String> consumerRecord = new ConsumerRecord<>("dev", 0, 0L, null, testData);
        KafkaTopicListener kafkaTopicListener = new KafkaTopicListener(kafkaTemplate);
        kafkaTopicListener.listener(consumerRecord, acknowledgment);

        verify(acknowledgment).acknowledge();

    }

    record KafkaTopicListener(KafkaTemplate<String, String> kafkaTemplate) {
        @KafkaListener(topics = "dev", groupId = "dev1")
            void listener(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
                String data = consumerRecord.value();
                System.out.println(data);
                acknowledgment.acknowledge();
            }
        }


}