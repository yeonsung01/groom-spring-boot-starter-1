package com.study.profile_stack_api.domain.profile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileDeleteResponse {

    private String message;
    private Long deletedId;

    public static ProfileDeleteResponse of(Long id) {
        return new ProfileDeleteResponse(
                "프로필이 성공적으로 삭제되었습니다.",
                id
        );
    }

}
