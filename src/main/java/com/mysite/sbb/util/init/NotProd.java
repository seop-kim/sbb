package com.mysite.sbb.util.init;

import com.mysite.sbb.question.service.QuestionService;
import com.mysite.sbb.user.model.SiteUser;
import com.mysite.sbb.user.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod")
@Configuration
public class NotProd {
    @Bean
    public ApplicationRunner initNotProdData(QuestionService questionService, UserService userService) {
        return args -> {
            if (userService.count() == 0) {
                userService.create("admin", "admin@damin.com", "1111");
            }
            if (questionService.count() == 0) {
                for (int i = 1; i <= 300; i++) {
                    String subject = "테스트 데이터입니다 : %03d".formatted(i);
                    String content = "내용무";
                    SiteUser siteUser = userService.findById(1L);
                    questionService.create(subject, content, siteUser);
                }
            }
        };
    }
}
