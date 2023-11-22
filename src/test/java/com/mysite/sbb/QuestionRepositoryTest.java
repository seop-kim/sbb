package com.mysite.sbb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
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
    void saveClear() {
        questionRepository.deleteAll();
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
        if (question1.isPresent()) {
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

    @Test
    void findBySubjectAndContentTest() {
        Question question =
                this.questionRepository.findBySubjectAndContent(
                        "sbb가 무엇인가요?",
                        "sbb에 대해 알고 싶습니다.");

        assertThat(question.getId())
                .isEqualTo(1);
    }

    @Test
    void findBySubjectLikeTest() {
        List<Question> questions = this.questionRepository.findBySubjectLike("sbb%");
        Question question = questions.get(0);

        assertThat(question.getSubject())
                .isEqualTo("sbb가 무엇인가요?");
    }

    @Test
    void updateTest() {
        Optional<Question> opFindOne = this.questionRepository.findById(1);
        assertTrue(opFindOne.isPresent());

        Question question = opFindOne.get();
        question.setSubject("수정된 제목");
        this.questionRepository.save(question);
    }

    @Test
    void deleteTest() {
        // 최초에 2개의 데이터
        assertThat(questionRepository.count())
                .isEqualTo(2);

        // 1번 아이디를 가진 데이터를 가져오다
        Optional<Question> findOne = questionRepository.findById(1);
        assertThat(findOne)
                .isPresent();

        Question question = findOne.get();

        // 1번 아이디 데이터 삭제
        questionRepository.delete(question);

        // 2개 중 1개의 삭제로 결과는 1
        assertThat(questionRepository.count())
                .isEqualTo(1);
    }





}