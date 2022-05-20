package com.uml.projectapp.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uml.common.constant.Constant;
import com.uml.common.po.Chat;
import com.uml.common.po.Event;
import com.uml.common.vo.ChatVo;
import com.uml.projectapp.service.ChatService;
import org.apache.tomcat.util.bcel.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-05-19 21:14
 */
@Component
public class EventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    private final ChatService chatService;
    private final ObjectMapper objectMapper;

    public EventConsumer(ChatService chatService, ObjectMapper objectMapper) {
        this.chatService = chatService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = {Constant.LIKE, Constant.COMMENT, Constant.FOLLOW})
    public void handleCommentMessage(String eventValue) throws JsonProcessingException {
        if (eventValue == null) {
            logger.info("消息内容为空");
            return;
        }
        Event event = objectMapper.readValue(eventValue, Event.class);
        if (event == null) {
            logger.info("消息格式不对，获取到的为" + event);
            return;
        }
        Chat chat = new Chat();
        chat.setSenderId(Constant.SYSTEM_USER_ID);
        chat.setReceiverId(event.getEntityUserId());
        chat.setConservationId(event.getTopic());
        chat.setCreateTime(new Date());
        Map<String, Object> data = event.getData();
        data.put("uid", event.getUserId());
        data.put("entityType", event.getEntityType());
        data.put("entityId", event.getEntityId());
        String content = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        chat.setContent(content);
        chatService.insertChatMessage(chat);
    }
}
