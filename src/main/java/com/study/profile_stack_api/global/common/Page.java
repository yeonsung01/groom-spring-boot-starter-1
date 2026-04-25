package com.study.profile_stack_api.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Page<T> {

    private List<T> content;       // 데이터 목록
    private int page;              // 현재 페이지
    private int size;              // 페이지 크기
    private long totalElements;    // 전체 데이터 개수
    private int totalPages;        // 전체 페이지 수
    private boolean first;         // 첫 페이지 여부
    private boolean last;          // 마지막 페이지 여부
    private boolean hasPrevious;   // 이전 페이지 존재 여부
    private boolean hasNext;       // 다음 페이지 존재 여부
}
