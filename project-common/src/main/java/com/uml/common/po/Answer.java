package com.uml.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author wuyuda
 * @date 2022-05-01 15:19
 */
@TableName(value = "`answer`")
public class Answer extends BaseEntity{
    public static final String CONTENT = "content";
    public static final String QUESTION_ID = "question_id";
    @TableField(CONTENT)
    private String content;

    @TableField(QUESTION_ID)
    private Long questionId;

    public Answer() {
    }

    public Answer(Long id, int version, Date createTime, Date updateTime, boolean delete, String content, Long questionId) {
        super(id, version, createTime, updateTime, delete);
        this.content = content;
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "content='" + content + '\'' +
                ", questionId=" + questionId +
                ", id=" + id +
                '}';
    }
}
