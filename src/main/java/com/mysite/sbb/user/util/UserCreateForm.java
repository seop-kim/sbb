package com.mysite.sbb.user.util;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    @Size(min = 3, max = 25)
    @NotEmpty
    private String username;

    @NotEmpty
    private String password1;

    @NotEmpty
    private String password2;

    @NotEmpty
    @Email
    private String email;
}
