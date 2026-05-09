package com.study.profile_stack_api.domain.techstack.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TechStackDeleteResponse {

    private String message;          // 삭제 성공 메시지
    private Long deleteId;         // 삭제된 기술 스택 ID

    public static TechStackDeleteResponse of(Long id) {
        return new TechStackDeleteResponse(
                "기술 스택이 성공적으로 삭제되었습니다.",
                id
        );
    }

}
