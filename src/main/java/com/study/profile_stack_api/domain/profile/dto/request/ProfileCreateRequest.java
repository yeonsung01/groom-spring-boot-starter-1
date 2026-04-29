package com.study.profile_stack_api.domain.profile.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ProfileCreateRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    private String bio;
    private String position;
    private Integer careerYears;
    private String githubUrl;
    private String blogUrl;
}