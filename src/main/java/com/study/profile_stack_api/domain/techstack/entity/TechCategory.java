package com.study.profile_stack_api.domain.techstack.entity;

public enum TechCategory {

    LANGUAGE("프로그래밍 언어", "📝"),
    FRAMEWORK("프레임워크", "🏗️"),
    DATABASE("데이터베이스", "💾"),
    DEVOPS("DevOps/인프라", "☁️"),
    TOOL("개발도구", "🔧"),
    ETC("기타", "📦");

    private final String description;
    private final String icon;

    TechCategory (String description, String icon) {
        this.description = description;
        this.icon = icon;
    }
    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
