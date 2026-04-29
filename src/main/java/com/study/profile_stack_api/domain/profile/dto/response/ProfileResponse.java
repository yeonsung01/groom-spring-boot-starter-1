package com.study.profile_stack_api.domain.profile.dto.response;

import com.study.profile_stack_api.domain.profile.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProfileResponse {

    private Long id;                 // DB에서 생성된 ID
    private String name;             // 이름
    private String email;            // 이메일
    private String bio;              // 자기소개
    private String position;         // 직무 (enum -> 문자열)
    private String getPositionIcon;  // 직무 아이콘 (enum에서 꺼냄)
    private Integer careerYears;     // 경력
    private String githubUrl;        // 깃허브 주소
    private String blogUrl;          // 블로그 주소
    private LocalDateTime createdAt; // 생성 시간
    private LocalDateTime updatedAt; // 수정 시간

    // Entity -> Response 변환 메서드 (핵심)
    public static ProfileResponse from(Profile profile) {
        return new ProfileResponse(
                profile.getId(),                      // DB에서 생성된 id
                profile.getName(),
                profile.getEmail(),
                profile.getBio(),
                profile.getPosition().name(),         // enum -> 문자열 (BACKEND)
                profile.getPosition().getIcon(),      // enum 내부 icon 꺼냄
                profile.getCareerYears(),
                profile.getGithubUrl(),
                profile.getBlogUrl(),
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}
