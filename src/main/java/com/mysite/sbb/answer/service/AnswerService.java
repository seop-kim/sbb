package com.mysite.sbb.answer.service;

import com.mysite.sbb.answer.model.Answer;
import com.mysite.sbb.answer.repository.AnswerRepository;
import com.mysite.sbb.question.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    // ========== 답변 저장 ==========
    public void create(Question question, String content) {
        Answer answer = new Answer(question, content);
        answerRepository.save(answer);
    }

}
