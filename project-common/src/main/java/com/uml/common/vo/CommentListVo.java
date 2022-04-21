package com.uml.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author wuyuda
 * @date 2022-04-21 19:17
 */
public class CommentListVo implements Serializable {
    private int current;
    private int size;
    private Long total;
    private List<CommentVo> comments;

    public CommentListVo() {
    }

    public CommentListVo(int current, int size, Long total, List<CommentVo> comments) {
        this.current = current;
        this.size = size;
        this.total = total;
        this.comments = comments;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<CommentVo> getComments() {
        return comments;
    }

    public void setComments(List<CommentVo> comments) {
        this.comments = comments;
    }
}
