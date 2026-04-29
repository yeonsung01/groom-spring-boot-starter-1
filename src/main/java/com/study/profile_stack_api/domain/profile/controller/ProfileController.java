package com.study.profile_stack_api.domain.profile.controller;


import com.study.profile_stack_api.domain.profile.dto.request.ProfileCreateRequest;
import com.study.profile_stack_api.domain.profile.dto.request.ProfileUpdateRequest;
import com.study.profile_stack_api.domain.profile.dto.response.ProfileDeleteResponse;
import com.study.profile_stack_api.domain.profile.dto.response.ProfileResponse;
import com.study.profile_stack_api.domain.profile.service.ProfileService;
import com.study.profile_stack_api.global.common.ApiResponse;
import com.study.profile_stack_api.global.common.Page;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService; // 생성자 주입을 통한 DI, 객체 생성/관리는 Spring IoC 컨테이너가 담당

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // POST /api/vi/profiles
    @PostMapping
    public ResponseEntity<ApiResponse<ProfileResponse>> createProfile(
            @Valid @RequestBody ProfileCreateRequest request
    ) {
        ProfileResponse response = profileService.createProfile(request);

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));

    }

    // GET /api/v1/profiles?page=0&size=10
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProfileResponse>>> getProfiles(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Page<ProfileResponse> response = profileService.getProfileWithPaging(page, size);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // GET /api/v1/profiles/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(
            @PathVariable Long id
    ) {
        ProfileResponse response = profileService.getProfileById(id);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // GET /api/v1/profiles/position/{position}
    @GetMapping("/position/{position}")
    public ResponseEntity<ApiResponse<List<ProfileResponse>>> getProfileByPosition(
            @PathVariable String position
    ) {
        return  ResponseEntity.ok(ApiResponse.success(
                profileService.getProfilesByPosition(position)
        ));
    }

    // PUT /api/v1/profiles/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(
            @PathVariable Long id,
            @RequestBody ProfileUpdateRequest request
    ) {
        ProfileResponse response = profileService.updateProfileById(id, request);

        return ResponseEntity.ok(
                ApiResponse.success(response));
    }

    // DELETE /api/v1/profiles/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileDeleteResponse>> deleteProfile(
            @PathVariable Long id
    ) {
        ProfileDeleteResponse response = profileService.deleteProfileById(id);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
