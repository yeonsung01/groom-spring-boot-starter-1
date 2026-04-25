package com.study.profile_stack_api.domain.profile.service;

import com.study.profile_stack_api.domain.profile.dto.request.ProfileCreateRequest;
import com.study.profile_stack_api.domain.profile.dto.request.ProfileUpdateRequest;
import com.study.profile_stack_api.domain.profile.dto.response.ProfileDeleteResponse;
import com.study.profile_stack_api.domain.profile.dto.response.ProfileResponse;
import com.study.profile_stack_api.global.common.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    public Page<ProfileResponse> getProfileWithPaging(Integer page, Integer size) {
        return null; // 임시
    }

    public ProfileResponse getProfileById(Long id) {
        return null; // 임시
    }

    public ProfileResponse createProfile(ProfileCreateRequest request) {
        return null; // 임시
    }

    public List<ProfileResponse> getProfilesByPosition(String position) {
        return List.of(); // 임시
    }

    public ProfileResponse updateProfileById(Long id, ProfileUpdateRequest request) {
        return null; // 임시
    }

    public ProfileDeleteResponse deleteProfileById(Long id) {
        return ProfileDeleteResponse.of(id); // 임시
    }
}
