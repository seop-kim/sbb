package com.mysite.sbb.question;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    // ========== Question 목록 ==========
    @GetMapping("/question/list")
    public String list(Model model) {
        // Question 전체 조회를 하여 model에 저장하여 front로 전달한다.
        List<Question> questions = questionService.list();
        model.addAttribute("questions", questions);

        return "question_list";
    }
}
