package com.uml.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author wuyuda
 * @date 2022-05-01 16:37
 */
public class QuestionListVo implements Serializable {
    private Integer current;
    private Integer size;
    private Long total;
    private List<QuestionVo> questionVos;

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

    public List<QuestionVo> getQuestionVos() {
        return questionVos;
    }

    public void setQuestionVos(List<QuestionVo> questionVos) {
        this.questionVos = questionVos;
    }
}
