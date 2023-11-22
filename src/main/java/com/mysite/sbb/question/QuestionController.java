package com.mysite.sbb.question;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    // ========== Question 목록 ==========
    @GetMapping("/list")
    public String list(Model model) {
        // Question service에 question 목록을 요청한다.
        List<Question> questions = questionService.list();
        model.addAttribute("questions", questions);

        return "question_list";
    }

    // ========== 상세 페이지 ==========
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable Integer id) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
}
