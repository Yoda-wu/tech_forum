package com.uml.projectapp.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uml.common.po.Event;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wuyuda
 * @date 2022-05-19 21:11
 */
@Component
public class EventProducer {

    private final KafkaTemplate kafkaTemplate;

    private final ObjectMapper objectMapper;

    public EventProducer(KafkaTemplate kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * 处理事件
     * @param event 事件
     */
    public void fireEvent(Event event) throws JsonProcessingException {
        String eventValue = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(event);
        kafkaTemplate.send(event.getTopic(),eventValue);
    }
}
