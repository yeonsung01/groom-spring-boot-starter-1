package com.study.profile_stack_api.domain.profile.dao;

import com.study.profile_stack_api.domain.profile.entity.Position;
import com.study.profile_stack_api.domain.profile.entity.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfileDaoImpl implements ProfileDao {

    private final JdbcTemplate jdbcTemplate;

    // 생성자 주입: Spring이 JdbcTemplate 객체를 넣어줌
    public ProfileDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 프로필 생성
    @Override
    public Profile save(Profile profile) {

        String sql = """
                INSERT INTO profile
                (name, email, bio, position, career_years, github_url, blog_url, created_at, updated_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        // DB 저장 시간과 응답 시간을 맞추기 위해 한 번만 생성
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update(connection -> {
            // id 컬럼만 생성 키로 반환받음
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});

            ps.setString(1, profile.getName());
            ps.setString(2, profile.getEmail());
            ps.setString(3, profile.getBio());
            ps.setString(4, profile.getPosition().name()); // enum → 문자열
            ps.setObject(5, profile.getCareerYears());
            ps.setString(6, profile.getGithubUrl());
            ps.setString(7, profile.getBlogUrl());
            ps.setObject(8, now);
            ps.setObject(9, now);

            return ps;
        }, keyHolder);

        // DB에서 자동 생성된 id를 객체에 반영
        Long generatedId = keyHolder.getKey().longValue();
        profile.setId(generatedId);

        // 응답 DTO 변환 시 시간이 나오도록 객체에도 반영
        profile.setCreatedAt(now);
        profile.setUpdatedAt(now);

        return profile;
    }

    // 프로필 단건 조회
    @Override
    public Optional<Profile> findById(Long id) {

        String sql = """
                SELECT *
                FROM profile
                WHERE id = ?
                """;

        List<Profile> result = jdbcTemplate.query(sql,
                (rs, rowNum) -> new Profile(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("bio"),
                        Position.valueOf(rs.getString("position")),
                        rs.getInt("career_years"),
                        rs.getString("github_url"),
                        rs.getString("blog_url"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                ),
                id
        );

        return result.stream().findFirst();
    }

    // 프로필 목록 조회
    @Override
    public List<Profile> findAll(int offset, int limit) {

        String sql = """
                SELECT *
                FROM profile
                ORDER BY id DESC
                LIMIT ? OFFSET ?
                """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Profile(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("bio"),
                        Position.valueOf(rs.getString("position")),
                        rs.getInt("career_years"),
                        rs.getString("github_url"),
                        rs.getString("blog_url"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                ),
                limit, offset
        );
    }

    // 전체 프로필 개수 조회
    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM profile";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    // 이메일 중복 확인
    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM profile WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    // 프로필 수정 - 다음 단계에서 구현
    @Override
    public Profile update(Profile profile) {
        String sql = """
                UPDATE profile
                SET name = ?,
                    email = ?,
                    bio = ?,
                    position = ?,
                    career_years = ?,
                    github_url = ?,
                    blog_url = ?,
                    updated_at = ?
                WHERE id = ?
                """;

        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update(sql,
                profile.getName(),
                profile.getEmail(),
                profile.getBio(),
                profile.getPosition().name(),
                profile.getCareerYears(),
                profile.getGithubUrl(),
                profile.getBlogUrl(),
                now,
                profile.getId()
        );

        profile.setUpdatedAt(now);

        return profile;
    }

    // 프로필 삭제 - 다음 단계에서 구현
    @Override
    public boolean deleteById(Long id) {
        String sql = """
                DELETE FROM profile
                WHERE id = ?
                """;
        int affectedRows = jdbcTemplate.update(sql,id);

        return affectedRows >0;
    }
}