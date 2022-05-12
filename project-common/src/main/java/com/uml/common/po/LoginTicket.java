package com.uml.common.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wuyuda
 * @date 2022-05-10 20:37
 */
public class LoginTicket implements Serializable {
    private Long uid;
    private String ticket;
    private Date date;
    private int state;

    public LoginTicket() {
    }

    public LoginTicket(Long uid, String ticket, Date date, int state) {
        this.uid = uid;
        this.ticket = ticket;
        this.date = date;
        this.state = state;
    }

    @Override
    public String toString() {
        return "LoginTicket{" +
                "uid=" + uid +
                ", ticket='" + ticket + '\'' +
                ", date=" + date +
                ", state=" + state +
                '}';
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
