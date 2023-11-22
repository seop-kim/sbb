package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
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
