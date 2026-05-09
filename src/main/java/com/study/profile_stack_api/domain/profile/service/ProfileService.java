package com.study.profile_stack_api.domain.profile.service;

import com.study.profile_stack_api.domain.profile.dao.ProfileDao;
import com.study.profile_stack_api.domain.profile.dto.request.ProfileCreateRequest;
import com.study.profile_stack_api.domain.profile.dto.request.ProfileUpdateRequest;
import com.study.profile_stack_api.domain.profile.dto.response.ProfileDeleteResponse;
import com.study.profile_stack_api.domain.profile.dto.response.ProfileResponse;
import com.study.profile_stack_api.domain.profile.entity.Position;
import com.study.profile_stack_api.domain.profile.entity.Profile;
import com.study.profile_stack_api.global.common.Page;
import com.study.profile_stack_api.global.exception.DuplicateEmailException;
import com.study.profile_stack_api.global.exception.ProfileNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileService {

    private final ProfileDao profileDao;

    public ProfileService(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public Page<ProfileResponse> getProfileWithPaging(Integer page, Integer size) {
        int offset = page * size;

        List<Profile> profiles = profileDao.findAll(offset, size);

        List<ProfileResponse> content = profiles.stream()
                .map(ProfileResponse::from)
                .toList();

        long totalElements = profileDao.count();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        boolean first = page == 0;
        boolean last = page >= totalPages - 1;
        boolean hasPrevious = page > 0;
        boolean hasNext = page < totalPages - 1;

        return new Page<>(
                content,
                page,
                size,
                totalElements,
                totalPages,
                first,
                last,
                hasPrevious,
                hasNext
        );
    }

    public ProfileResponse getProfileById(Long id) {
        Profile profile = profileDao.findById(id)
                .orElseThrow(() -> new RuntimeException("프로필을 찾을 수 없습니다."));

        return ProfileResponse.from(profile);
    }

    public List<ProfileResponse> getProfilesByPosition(String position) {
        return List.of(); // 다음에 구현
    }

    public ProfileResponse updateProfileById(Long id, ProfileUpdateRequest request) {
        return null; // 다음에 구현
    }

    public ProfileDeleteResponse deleteProfileById(Long id) {
        boolean deleted = profileDao.deleteById(id);

        if (!deleted) {
            throw new ProfileNotFoundException(id);
        }

        return ProfileDeleteResponse.of(id);
    }

    public ProfileResponse createProfile(ProfileCreateRequest request) {
        if (profileDao.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(request.getEmail());
        }

        LocalDateTime now = LocalDateTime.now();

        Profile profile = new Profile(
                null,
                request.getName(),
                request.getEmail(),
                request.getBio(),
                Position.valueOf(request.getPosition()),
                request.getCareerYears(),
                request.getGithubUrl(),
                request.getBlogUrl(),
                now,
                now
        );

        Profile savedProfile = profileDao.save(profile);

        return ProfileResponse.from(savedProfile);
    }
}