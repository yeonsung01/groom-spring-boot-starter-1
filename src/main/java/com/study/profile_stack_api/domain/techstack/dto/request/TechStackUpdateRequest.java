package com.study.profile_stack_api.domain.techstack.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TechStackUpdateRequest {

    private String name;
    private String category;
    private String proficiency;
    private Integer yearsOfExp;
}
