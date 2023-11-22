package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    // ========== 답변 저장 ==========
    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable Integer id, @RequestParam String content) {
        Question question = questionService.getQuestion(id);

        // TODO : Answer save
        answerService.create(question, content);

        return "redirect:/question/detail/%s".formatted(id);
    }
}
