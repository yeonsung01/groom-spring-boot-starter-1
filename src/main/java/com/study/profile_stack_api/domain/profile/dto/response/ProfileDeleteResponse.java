package com.study.profile_stack_api.domain.profile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileDeleteResponse {

    private Long id;

    public static ProfileDeleteResponse of(Long id) {
        return new ProfileDeleteResponse(id);
    }

}
