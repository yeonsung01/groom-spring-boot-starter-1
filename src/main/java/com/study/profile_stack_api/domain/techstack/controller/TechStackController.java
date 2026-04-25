package com.study.profile_stack_api.domain.techstack.controller;

import com.study.profile_stack_api.domain.techstack.dto.request.TechStackCreateRequest;
import com.study.profile_stack_api.domain.techstack.dto.request.TechStackUpdateRequest;
import com.study.profile_stack_api.domain.techstack.dto.response.TechStackResponse;
import com.study.profile_stack_api.domain.techstack.service.TechStackService;
import com.study.profile_stack_api.global.common.ApiResponse;
import com.study.profile_stack_api.global.common.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles/{profileId}/tech-stacks")
public class TechStackController {

    private final TechStackService techStackService;

    public TechStackController(TechStackService techStackService) {
        this.techStackService = techStackService;
    }

    // POST /api/v1/profiles/{profileId}/tech-stacks
    @PostMapping
    public ResponseEntity<ApiResponse<TechStackResponse>> createTechStack(
            @PathVariable Long profileId,
            @RequestBody TechStackCreateRequest request
    ) {
        TechStackResponse response = techStackService.createTechStack(profileId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    // GET /api/v1/profiles/{profileId}/tech-stacks?page=0&size=10
    @GetMapping
    public ResponseEntity<ApiResponse<Page<TechStackResponse>>> getTechStacks(
            @PathVariable Long profileId,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Page<TechStackResponse> response = techStackService.getTechStacks(profileId, page, size);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // GET /api/v1/profiles/{profileId}/tech-stacks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TechStackResponse>> getTechStack(
            @PathVariable Long profileId,
            @PathVariable Long id
    ) {
        TechStackResponse response = techStackService.getTechStack(profileId, id);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // PUT /api/v1/profiles/{profileId}/tech-stacks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TechStackResponse>> updateTechStack(
            @PathVariable Long profileId,
            @PathVariable Long id,
            @RequestBody TechStackUpdateRequest request
    ) {
        TechStackResponse response = techStackService.updateTechStack(profileId, id, request);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // DELETE /api/v1/profiles/{profileId}/tech-stacks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTechStack(
            @PathVariable Long profileId,
            @PathVariable Long id
    ) {
        techStackService.deleteTechStack(profileId, id);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}