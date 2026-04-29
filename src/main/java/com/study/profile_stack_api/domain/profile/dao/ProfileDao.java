package com.study.profile_stack_api.domain.profile.dao;

import com.study.profile_stack_api.domain.profile.entity.Profile;

import java.util.List;
import java.util.Optional;
public interface ProfileDao {



    // 프로필 생성
    Profile save (Profile profile);

    // id로 프로필 단건 조회
    Optional<Profile> findById(Long id);

    // 프로필 수정
    Profile update(Profile profile);

    // 프로필 삭제
    boolean deleteById(Long id);

    // 이메일 중복 확인
    boolean existsByEmail(String email);

    List<Profile> findAll(int offset, int limit);
    long count();

}
