package com.mysite.sbb.answer.model;

import com.mysite.sbb.question.model.Question;
import com.mysite.sbb.user.model.SiteUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private SiteUser author;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Question question;

    public Answer() {
    }

    /**
     * 답변내용과 질문 객체만 넣으면 나머지 값은 자동 할당된다.
     *
     * @param question
     * @param content
     */
    public Answer(Question question, String content) {
        this.content = content;
        this.question = question;
        this.createDate = LocalDateTime.now();
    }
}
