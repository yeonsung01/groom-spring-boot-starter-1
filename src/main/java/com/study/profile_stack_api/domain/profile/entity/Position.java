package com.study.profile_stack_api.domain.profile.entity;

public enum Position {

    BACKEND("백엔드 개발자", "⚙️"),
    FRONTEND("프론트엔드 개발자", "🎨"),
    FULLSTACK("풀스택 개발자", "🔄"),
    MOBILE("모바일 개발자", "📱"),
    DEVOPS("DevOps 엔지니어", "🚀"),
    DATA("데이터 엔지니어", "📊"),
    AI("AI/ML 엔지니어", "🤖"),
    ETC("기타", "💻");

    private final String description;
    private final String icon;

    // === 생성자 ===
    Position (String description, String icon) {
        this.description = description;
        this.icon = icon;
    }

    // === Getter ===
    // enum 값에 저장된 데이터를 꺼낼 때 사용
    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
