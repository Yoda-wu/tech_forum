package com.uml.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uml.common.constant.ArticleType;
import com.uml.common.constant.QuestionType;

import java.util.Date;

/**
 * @author wuyuda
 * @date 2022-05-01 14:39
 */
@TableName(value = "`question`")
public class Question extends BaseEntity {
    public static final String TITLE = "title";
    public static final String ANSWER_ID = "answer_id";
    public static final String QUES_TYPE = "ques_type";
    public static final String QUES_DOMAIN = "ques_domain";
    @TableField(TITLE)
    private String title;

    @TableField(ANSWER_ID)
    private Long answerId;
    @TableField(QUES_TYPE)
    private QuestionType quesType;
    @TableField(QUES_DOMAIN)
    private QuestionType quesDomain;

    public Question(Long id, int version, Date createTime, Date updateTime, boolean delete, String title, Long answerId, QuestionType quesType, QuestionType quesDomain) {
        super(id, version, createTime, updateTime, delete);
        this.title = title;
        this.answerId = answerId;
        this.quesType = quesType;
        this.quesDomain = quesDomain;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", answerId=" + answerId +
                ", quesType=" + quesType +
                ", quesDomain=" + quesDomain +
                '}';
    }

    public Question() {
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public QuestionType getQuesType() {
        return quesType;
    }

    public void setQuesType(QuestionType quesType) {
        this.quesType = quesType;
    }

    public QuestionType getQuesDomain() {
        return quesDomain;
    }

    public void setQuesDomain(QuestionType quesDomain) {
        this.quesDomain = quesDomain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAnswer_id() {
        return answerId;
    }

    public void setAnswer_id(Long answer_id) {
        this.answerId = answer_id;
    }


}
