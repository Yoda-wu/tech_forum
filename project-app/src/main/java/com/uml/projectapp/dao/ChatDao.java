package com.uml.projectapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uml.common.po.Chat;
import com.uml.common.vo.ChatVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-03-19 16:08
 */
@Component
public interface ChatDao extends BaseMapper<Chat> {

    /**
     * 查询当前用户的会话列表
     * 针对每个会话值返回一条最新的私信
     *
     * @param uid     当前用户id
     * @param current 当前页
     * @param size    页面大小
     * @return 会话列表
     */
    @Select("select c.*, u.name, u.avatar from chat as c, `user` as u " +
            "where c.id in ( " +
            "select max(id) from chat " +
            "where `status` != 2 " +
            "and sender_id != 0 " +
            "and ( sender_id = #{uid} or  receiver_id = #{uid} ) " +
            "group by conversation_id " +
            ") " +
            "and u.id = #{uid} " +
            "order by id desc " +
            "limit #{current}, #{size} ")
    List<ChatVo> selectConversation(Long uid, Integer current, Integer size);


    /**
     * 查询当前用户的会话数量
     *
     * @param uid 当前用户id
     * @return 会话数量
     */
    @Select("select count(m.max_id) from " +
            "( select max(id) as max_id from chat " +
            "  where `status` != 2 and sender_id != 0  and (sender_id = #{uid} or receiver_id = #{uid} " +
            "  group by conversation_id " +
            ") as m ")
    Integer selectConversationCount(Long uid);

    /**
     * 查询某个会话所包含的私信列表
     *
     * @param conversationId 会话id
     * @param current        当前页
     * @param size           页大小
     * @return 私信列表
     */
    @Select("select * from chat  " +
            "where `status` != 2 and sender_id != 1 and conversation_id = #{conversationId} " +
            "order by id desc " +
            "limit #{current}, #{size} ")
    List<ChatVo> selectLetters(String conversationId, Integer current, Integer size);

    /**
     * 某个会话所包含的私信数量
     *
     * @param conversationId 会话id
     * @return 私信数量
     */
    @Select("select count(id) from chat  " +
            "where `status` != 2 and sender_id != 1 and conversation_id = #{conversationId} ")
    Integer selectLettersCount(String conversationId);

    /**
     * 查询未读私信数量
     *
     * @param uid            用户id
     * @param conversationId 会话id
     * @return 数量
     */
    @Select("select count(id) from chat  " +
            "where `status` == 0 and sender_id != 1 and conversation_id = #{conversationId} " +
            "and receiver_id = #{uid} ")
    Integer selectUnreadCount(Long uid, String conversationId);


    /**
     * 查询某个主题下最新的通知
     *
     * @param uid   用户id
     * @param topic 主题
     * @return 最新的通知
     */
    @Select("select * from chat " +
            "where id in ( " +
            "select max(id) from chat " +
            "where `status` != 2 and sender_id = 0 and receiver_id = #{uid} and conversation_id = #{topic} " +
            ")")
    ChatVo selectLatestNotice(Long uid, String topic);

    /**
     * 查询某个主题所包含的通知数量
     *
     * @param uid   用户id
     * @param topic 主题
     * @return 通知数量
     */
    @Select("select count(id) from chat " +
            "where `status` != 2 and sender_id = 0 and receiver_id = #{uid} and conversation_id = #{topic} ")
    int selectNoticeCount(Long uid, String topic);

    /**
     * 查询未读系统消息的数量
     *
     * @param uid   用户id
     * @param topic 主题
     * @return 通知数量
     */
    @Select("select count(id) from chat " +
            "where `status` = 0 and sender_id = 0 and receiver_id = #{uid} and conversation_id = #{topic} ")
    int selectUnreadNotice(Long uid, String topic);

    /**
     * 查询未读系统消息的数量
     *
     * @param uid 用户id
     * @return 通知数量
     */
    @Select("select count(id) from chat " +
            "where `status` = 0 and sender_id = 0 and receiver_id = #{uid} ")
    int selectUnreadNotice(Long uid);
}
