package com.mysite.sbb;

import static org.assertj.core.api.Assertions.assertThat;

import com.mysite.sbb.answer.model.Answer;
import com.mysite.sbb.answer.repository.AnswerRepository;
import com.mysite.sbb.question.model.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import com.mysite.sbb.question.service.QuestionService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class SbbApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionService questionService;


    @Test
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = "테스트 데이터입니다 : %03d".formatted(i);
            String content = "내용무";
            questionService.create(subject, content);
        }
    }

    @DisplayName("[답변 등록] 답변등록 시 db에는 동일한 데이터가 저장되어야 한다.")
    @Test
    @Rollback
    void answerCreateTest() {
        Optional<Question> questionFindOne = questionRepository.findById(2);

        assertThat(questionFindOne)
                .isPresent();

        Question question = questionFindOne.get();

        Answer answer = new Answer();
        answer.setContent("네 자동으로 생성됩니다.");
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());
        Answer saveAnswer = answerRepository.save(answer);

        assertThat(answer)
                .isEqualTo(saveAnswer);
    }

    @Test
    @DisplayName("[답변 확인] 1번 answer의 question id는 2다")
    void answerInQuestionIdTest() {
        Optional<Answer> answerFindOne = answerRepository.findById(1);
        assertThat(answerFindOne)
                .isPresent();

        Answer answer = answerFindOne.get();

        assertThat(answer.getQuestion().getId())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("[답변 확인] 3번 질문에 있는 답변 수가 0이 맞는가?")
    @Transactional
    void questionInAnswersFindTest() {
        // Transactional 어노테이션을 사용하지 않을 경우 첫번째 question 을 조회한 뒤 DB세션이 종료되어 뒤에 코드에서 오류가 발생한다.

        Optional<Question> questionFindOne = questionRepository.findById(3);
        assertThat(questionFindOne)
                .isPresent();

        Question question = questionFindOne.get();

        List<Answer> answers = question.getAnswers();

        assertThat(answers.size())
                .isEqualTo(0);
    }
}
