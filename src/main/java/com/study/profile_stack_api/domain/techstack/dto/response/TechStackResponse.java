package com.study.profile_stack_api.domain.techstack.dto.response;

import com.study.profile_stack_api.domain.techstack.entity.TechStack;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TechStackResponse {

    private Long id;
    private Long profileId;
    private String name;
    private String category;         // 소문자 시작 추천
    private String categoryIcon;     // 숙련도 아이콘
    private String proficiency;      // enum 말고 String
    private String proficiencyIcon;  // 카테고리 아이콘
    private Integer yearsOfExp;
    private LocalDateTime createdAt; // 생성 시간
    private LocalDateTime updatedAt; // 수정 시간


    public static TechStackResponse from(TechStack techStack) {
        return new TechStackResponse(
                techStack.getId(),
                techStack.getProfileId(),
                techStack.getName(),
                techStack.getCategory().name(),
                techStack.getCategory().getIcon(),
                techStack.getProficiency().name(),
                techStack.getProficiency().getIcon(),
                techStack.getYearsOfExp(),
                techStack.getCreatedAt(),
                techStack.getUpdatedAt()

        );
    }
}