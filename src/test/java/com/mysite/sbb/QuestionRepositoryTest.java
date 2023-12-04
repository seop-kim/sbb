package com.mysite.sbb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mysite.sbb.question.model.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import com.mysite.sbb.user.service.UserService;
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

    @Autowired
    private UserService userService;

    @Test
    void saveTest() {
        Question question1 = Question.builder()
                .subject("sbb가 무엇인가요?")
                .content("sbb에 대해 알고 싶습니다.")
                .author(userService.findById(1L))
                .build();

        this.questionRepository.save(question1);

        Question question2 = Question.builder()
                .subject("스프링부트 모델 질문입니다.")
                .content("id는 자동으로 생성되나요?")
                .author(userService.findById(1L))
                .build();

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
        question.updateSubject("수정된 제목");
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