package com.mysite.sbb.question.service;

import com.mysite.sbb.exception.DataNotFoundException;
import com.mysite.sbb.question.model.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import com.mysite.sbb.user.model.SiteUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    // ========== Paging Question 목록 ==========
    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate")); // question reverse
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return questionRepository.findAll(pageable);
    }


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

    // ========== 질문 생성 ===========
    public void create(String subject, String content, SiteUser user) {
        Question question = Question.builder()
                .subject(subject)
                .content(content)
                .author(user)
                .build();
        questionRepository.save(question);
    }

    public long count() {
        return questionRepository.count();
    }
}
