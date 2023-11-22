package com.mysite.sbb.question;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    // ========== Question 목록 ==========
    public List<Question> list() {
        // Question 전체 조회를 하여 model에 저장하여 front로 전달한다.
        return questionRepository.findAll();
    }
}
