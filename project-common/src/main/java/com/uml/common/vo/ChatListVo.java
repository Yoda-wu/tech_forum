package com.uml.common.vo;

import com.uml.common.po.Chat;

import java.io.Serializable;
import java.util.List;

/**
 * @author wuyuda
 * @date 2022-05-19 21:36
 */
public class ChatListVo implements Serializable {
    private Integer current;
    private Integer size;
    private Long total;
    private List<ChatVo> chats;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<ChatVo> getChats() {
        return chats;
    }

    public void setChats(List<ChatVo> chats) {
        this.chats = chats;
    }
}
