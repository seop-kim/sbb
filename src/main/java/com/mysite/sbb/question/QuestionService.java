package com.mysite.sbb.question;

import com.mysite.sbb.exception.DataNotFoundException;
import java.util.List;
import java.util.Optional;
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

    // ========== 상세 페이지 ==========
    public Question getQuestion(Integer id) {
        Optional<Question> findQuestion = questionRepository.findById(id);
        if (findQuestion.isPresent()) {
            return findQuestion.get();
        }
        throw new DataNotFoundException("question not found");
    }

}
