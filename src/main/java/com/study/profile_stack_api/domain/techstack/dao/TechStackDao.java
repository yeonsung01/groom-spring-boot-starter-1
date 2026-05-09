package com.study.profile_stack_api.domain.techstack.dao;

import com.study.profile_stack_api.domain.techstack.entity.TechStack;

import java.util.List;
import java.util.Optional;

public interface TechStackDao {

    TechStack save(TechStack techStack);
    TechStack update(TechStack techStack);
    boolean deleteByIdAndProfileId(Long profileId, Long id);

    // 단건 조회 메서드 추가
    Optional<TechStack> findByIdAndProfileId(Long profileId, Long id);

    // 특정 프포필의 기술 스택 목록 조회
    List<TechStack> findAllByProfileId(
            Long profileId,
            int offset,
            int limit,
            String category,
            String proficiency
    );

    // 특정 프로필의 기술 스택 개수 조회
    long countByProfileId(
            Long profileId,
            String category,
            String proficiency
    );
}

