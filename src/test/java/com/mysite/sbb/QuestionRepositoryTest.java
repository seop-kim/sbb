package com.mysite.sbb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void saveTest() {
        Question question1 = new Question();
        question1.setSubject("sbb가 무엇인가요?");
        question1.setContent("sbb에 대해 알고 싶습니다.");
        question1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question1);

        Question question2 = new Question();
        question2.setSubject("스프링부트 모델 질문입니다.");
        question2.setContent("id는 자동으로 생성되나요?");
        question2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question2);
    }

    @Test
    void findAllTest() {
        List<Question> questions = this.questionRepository.findAll();
        assertThat(questions).hasSize(2);

        Question question1 = questions.get(0);
        assertThat(question1.getSubject())
                .isEqualTo("sbb가 무엇인가요?");
    }

    @Test
    void findByIdTest() {
        Optional<Question> question1 = this.questionRepository.findById(2);
        if (question1.isPresent()){
            Question question = question1.get();
            assertThat(question.getSubject())
                    .isEqualTo("스프링부트 모델 질문입니다.");
        }
    }

    @Test
    void findBySubject() {
        Question question = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertThat(question.getId())
                .isEqualTo(1);
    }

}