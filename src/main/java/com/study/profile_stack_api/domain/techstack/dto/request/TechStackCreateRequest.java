package com.study.profile_stack_api.domain.techstack.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TechStackCreateRequest {

    private String name;             // 기술명
    private String category;         // 기술 카테고리: LANGUAGE, FRAMEWORK 등
    private String proficiency;      // 숙련도: BEGINNER, INTERMEDIATE 등
    private Integer yearsOfExp; // 사용 경험 연수

}
