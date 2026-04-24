package com.study.profile_stack_api.domain.profile.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
// 생성자를 자동으로 만들어주는 Lombok 어노테이션
@NoArgsConstructor // 빈 객체 생성용
@AllArgsConstructor // 값 채워서 바로 생성
public class Profile {

    private Long id;                       // id
    private String name;                   // 이름
    private String email;                  // 이메일
    private String bio;                    // 자기소개
    private Position position;             // 직무 -> Position enum
    private Integer careerYears;           // 경력 -> null / 숫자 가능
    private String githubUrl;              // 깃허브 주소
    private String blogUrl;                // 블로그 주소
    private LocalDateTime createdAt;       // 생성 일시
    private LocalDateTime updatedAt;       // 수정 일시

    public  void update(String name, String email, String bio, Position position, Integer careerYears,
                        String githubUrl, String blogUrl) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }   // 사용자 입력의 앞뒤 공백 제거 및 공백 문자열 입력 방지

        if (email != null && !email.trim().isEmpty()) {
            this.email = email.trim().toLowerCase();
        } //toLowerCase() 이메일은 대소문자를 구분하지 않으므로, 중복 방지를 위해 소문자로 통일

        if (bio != null && !bio.trim().isEmpty()) {
            this.bio  = bio.trim();
        }
        if (position != null) this.position = position; // enum 타입으로 공백 들어오면 변환 단계에서 에러
        if (careerYears != null) this.careerYears = careerYears;
        if (githubUrl != null && !githubUrl.trim().isEmpty()) {
            this.githubUrl = githubUrl.trim();
        }
        if (blogUrl != null && !blogUrl.trim().isEmpty()) {
            this.blogUrl = blogUrl.trim();
        }

        this.updatedAt = LocalDateTime.now();
    }

}
