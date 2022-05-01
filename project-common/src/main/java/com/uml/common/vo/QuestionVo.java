package com.uml.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author wuyuda
 * @date 2022-05-01 16:37
 */
public class QuestionVo implements Serializable {
    public String title;
    public List<String> answerList;
    public String answer;

    public QuestionVo() {
    }

    public QuestionVo(String title, List<String> answerList, String answer) {
        this.title = title;
        this.answerList = answerList;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
