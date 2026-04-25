package com.study.profile_stack_api.domain.techstack.service;

import com.study.profile_stack_api.domain.techstack.dto.request.TechStackCreateRequest;
import com.study.profile_stack_api.domain.techstack.dto.request.TechStackUpdateRequest;
import com.study.profile_stack_api.domain.techstack.dto.response.TechStackResponse;
import org.springframework.stereotype.Service;
import com.study.profile_stack_api.global.common.Page;

@Service
public class TechStackService {

    public TechStackResponse createTechStack(Long profileId, TechStackCreateRequest request) {
        return null; // 임시
    }

    public Page<TechStackResponse> getTechStacks(Long profileId, Integer page, Integer size) {
        return  null; // 임시
    }

    public TechStackResponse getTechStack(Long profileId, Long id) {
        return null; // 임시
    }

    public TechStackResponse updateTechStack(Long profileId, Long id, TechStackUpdateRequest request) {
        return  null; // 임시
    }

    public  void deleteTechStack(Long profileId, Long id) {

    }
}
