package com.study.profile_stack_api.domain.techstack.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechStack {

    private Long id;                  // 기술 스택 고유 ID (PK)
    private Long profileId;           // 프로필 ID(FK)
    private String name;              // 기술명
    private TechCategory category;    // 기술 카테고리
    private Proficiency proficiency;  // 숙련도
    private Integer yearsOfExp;       // 사용 경험 (년)
    private LocalDateTime createdAt;  // 생성 일시
    private LocalDateTime updatedAt;  // 수정 일시

    public void update(String name, TechCategory category, Proficiency proficiency, Integer yearsOfExp) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
        if (category != null) {
            this.category = category;
        }

        if (proficiency != null) {
            this.proficiency = proficiency;
        }

        if (yearsOfExp != null) {
            this.yearsOfExp = yearsOfExp;
        }

        this.updatedAt = LocalDateTime.now();

    }


 }
