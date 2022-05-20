package com.uml.projectapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.uml.common.po.BaseEntity;
import com.uml.common.po.Chat;
import com.uml.common.utils.SensitiveFilter;
import com.uml.common.vo.ChatListVo;
import com.uml.common.vo.ChatVo;
import com.uml.projectapp.dao.ChatDao;
import com.uml.projectapp.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-05-20 10:34
 */
@Service
public class ChatServiceImpl implements ChatService {
    private final ChatDao chatDao;
    private final SensitiveFilter filter;
    public ChatServiceImpl(ChatDao chatDao,SensitiveFilter filter) {
        this.filter = filter;
        this.chatDao = chatDao;
    }

    @Override
    public ChatListVo selectConversation(Long uid, Integer current, Integer size) {
        List<ChatVo> chatVos = chatDao.selectConversation(uid, current, size);
        ChatListVo chatListVo = new ChatListVo();
        chatListVo.setSize(size);
        chatListVo.setCurrent(current);
        chatListVo.setChats(chatVos);
        return chatListVo;
    }

    @Override
    public Integer selectConversationCount(Long uid) {

        return chatDao.selectConversationCount(uid);
    }

    @Override
    public ChatListVo selectLetters(String conversationId, Integer current, Integer size) {
        List<ChatVo> chats = chatDao.selectLetters(conversationId, current, size);
        ChatListVo chatListVo = new ChatListVo();
        chatListVo.setSize(size);
        chatListVo.setCurrent(current);
        chatListVo.setChats(chats);
        return chatListVo;
    }

    @Override
    public Integer selectLettersCount(String conversationId) {

        return chatDao.selectLettersCount(conversationId);
    }

    @Override
    public Integer selectUnreadCount(Long uid, String conversationId) {
        return chatDao.selectUnreadCount(uid, conversationId);
    }

    @Override
    public Long insertChatMessage(Chat chat) {
        chat.setContent(filter.filter(chat.getContent()));
        return (long) chatDao.insert(chat);
    }

    @Override
    public Long updateStatus(List<Long> ids) {
        chatDao.update(null,new UpdateWrapper<Chat>().in(BaseEntity.ID,ids).set(Chat.STATUS,1));
        return null;
    }

    @Override
    public ChatVo selectLatestNotice(Long uid, String topic) {
        return chatDao.selectLatestNotice(uid,topic);
    }

    @Override
    public int selectNoticeCount(Long uid, String topic) {
        return chatDao.selectNoticeCount(uid,topic);
    }

    @Override
    public int selectUnreadNotice(Long uid, String topic) {
        if(topic == null){
            return chatDao.selectUnreadNotice(uid);
        }
        return chatDao.selectUnreadNotice(uid,topic);
    }
}
