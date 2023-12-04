package com.mysite.sbb.question.model;

import com.mysite.sbb.answer.model.Answer;
import com.mysite.sbb.user.model.SiteUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private SiteUser author;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    List<Answer> answers;

    public Question() {
    }

    /**
     * Subject, Content other Auto
     *
     * @param subject
     * @param content
     */
    @Builder
    public Question(String subject, String content,SiteUser author) {
        this.subject = subject;
        this.content = content;
        this.author = author;
        this.createDate = LocalDateTime.now();
    }

    public void updateSubject(String updateSubject) {
        this.subject = updateSubject;
    }
}
