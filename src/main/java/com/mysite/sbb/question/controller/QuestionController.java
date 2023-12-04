package com.mysite.sbb.question.controller;

import com.mysite.sbb.answer.util.AnswerForm;
import com.mysite.sbb.question.model.Question;
import com.mysite.sbb.question.util.QuestionForm;
import com.mysite.sbb.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    // ========== Question 목록 ==========
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    // ========== 상세 페이지 ==========
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable Integer id, AnswerForm answerForm) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    // ========== 질문 생성 Form ===========
    @GetMapping("/create")
    public String createForm(QuestionForm questionForm) {
        return "question_create_form";
    }

    // ========== 질문 생성 ===========
    @PostMapping("/create")
    public String create(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        // TODO : Question Save
        if (bindingResult.hasErrors()) {
            return "question_create_form";
        }
        questionService.create(questionForm.getSubject(), questionForm.getContent());

        return "redirect:/question/list";
    }
}
