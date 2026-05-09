package com.study.profile_stack_api.domain.techstack.service;

import com.study.profile_stack_api.domain.techstack.dao.TechStackDao;
import com.study.profile_stack_api.domain.techstack.dto.request.TechStackCreateRequest;
import com.study.profile_stack_api.domain.techstack.dto.request.TechStackUpdateRequest;
import com.study.profile_stack_api.domain.techstack.dto.response.TechStackDeleteResponse;
import com.study.profile_stack_api.domain.techstack.dto.response.TechStackResponse;
import com.study.profile_stack_api.domain.techstack.entity.Proficiency;
import com.study.profile_stack_api.domain.techstack.entity.TechCategory;
import com.study.profile_stack_api.domain.techstack.entity.TechStack;
import com.study.profile_stack_api.global.common.Page;
import com.study.profile_stack_api.global.exception.TechStackNotFoundException;
import org.springframework.stereotype.Service;
import com.study.profile_stack_api.domain.techstack.dto.response.TechStackDeleteResponse;

import java.util.List;

@Service
public class TechStackService {

    private final TechStackDao techStackDao;

    public TechStackService(TechStackDao techStackDao) {
        this.techStackDao = techStackDao;
    }


    // 기술 스택 생성
    public TechStackResponse createTechStack(Long profileId, TechStackCreateRequest request) {

        // Request DTO(String) → Entity(Enum) 변환
        TechStack techStack = new TechStack(
                null,
                profileId,
                request.getName(),
                TechCategory.valueOf(request.getCategory()),
                Proficiency.valueOf(request.getProficiency()),
                request.getYearsOfExp(),
                null,
                null
        );

        // DB 저장
        TechStack saved = techStackDao.save(techStack);

        // Entity → Response DTO 변환
        return TechStackResponse.from(saved);
    }

    public Page<TechStackResponse> getTechStacks(
            Long profileId,
            Integer page,
            Integer size,
            String category,
            String proficiency
    ) {
        int offset = page * size;

        List<TechStack> techStacks =
                techStackDao.findAllByProfileId(profileId, offset, size, category, proficiency);

        List<TechStackResponse> content = techStacks.stream()
                .map(TechStackResponse::from)
                .toList();

        long totalElements =
                techStackDao.countByProfileId(profileId, category, proficiency);

        int totalPages = (int) Math.ceil((double) totalElements / size);

        return  new Page<>(
                content,
                page,
                size,
                totalElements,
                totalPages,
                page == 0,
                page >= totalPages -1,
                page > 0,
                page < totalPages -1

        );
    }

    public TechStackResponse getTechStack(Long profileId, Long id) {

        TechStack techStack = techStackDao.findByIdAndProfileId(profileId, id)
                .orElseThrow(() -> new TechStackNotFoundException(id));

        return TechStackResponse.from(techStack);
    }

    public TechStackResponse updateTechStack(Long profileId, Long id, TechStackUpdateRequest request) {

        TechStack techStack = techStackDao.findByIdAndProfileId(profileId, id)
                .orElseThrow(() -> new TechStackNotFoundException(id));

        techStack.update(
                request.getName(),
                request.getCategory() == null ? null : TechCategory.valueOf(request.getCategory()),
                request.getProficiency() == null ? null : Proficiency.valueOf(request.getProficiency()),
                request.getYearsOfExp()
        );

        TechStack updated = techStackDao.update(techStack);

        return TechStackResponse.from(updated);
    }

    public TechStackDeleteResponse deleteTechStack(Long profileId, Long id) {
        boolean deleted = techStackDao.deleteByIdAndProfileId(profileId, id);

        if (!deleted) {
            throw new RuntimeException("해당 기술 스택을 찾을 수 없습니다.(id: " + id + ")");
        }

        return TechStackDeleteResponse.of(id);
    }
}