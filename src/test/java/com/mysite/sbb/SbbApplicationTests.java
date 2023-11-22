package com.mysite.sbb;

import static org.assertj.core.api.Assertions.assertThat;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void answerCreateTest() {
        Optional<Question> questionFindOne = questionRepository.findById(2);
        assertThat(questionFindOne)
                .isPresent();

        Question question = questionFindOne.get();

        Answer answer = new Answer();
        answer.setContent("네 자동으로 생성됩니다.");
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());
        answerRepository.save(answer);
    }

    @Test
    @DisplayName("1번 answer의 question id는 2다")
    void answerInQuestionIdTest() {
        Optional<Answer> answerFindOne = answerRepository.findById(1);
        assertThat(answerFindOne)
                .isPresent();

        Answer answer = answerFindOne.get();

        assertThat(answer.getQuestion().getId())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("1번 질문에 있는 답변 수가 1이 맞는가? / 답변의 내용이 네 '자동으로 생성됩니다.'와 일치하는가?")
    @Transactional
    void questionInAnswersFindTest() {
        // Transactional 어노테이션을 사용하지 않을 경우 첫번째 question 을 조회한 뒤 DB세션이 종료되어 뒤에 코드에서 오류가 발생한다.

        Optional<Question> questionFindOne = questionRepository.findById(2);
        assertThat(questionFindOne)
                .isPresent();

        Question question = questionFindOne.get();

        List<Answer> answers = question.getAnswers();

        assertThat(answers.size())
                .isEqualTo(1);

        assertThat(answers.get(0).getContent())
                .isEqualTo("네 자동으로 생성됩니다.");
    }
}
