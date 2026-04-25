package com.study.profile_stack_api.domain.profile.dao;
import com.study.profile_stack_api.domain.profile.entity.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class ProfileDaoImpl implements ProfileDao {

    private final JdbcTemplate jdbcTemplate;

    // 생성자 주입 (Spring 이 JdbcTemplate 넣어줌)
    public ProfileDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 🔥CREATE (핵심)
    @Override
    public Profile save(Profile profile) {

        String sql = """
                INSERT INTO profile
                (name, email, bio, position, career_years, github_url, blog_url, created_at, updated_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, profile.getName());
            ps.setString(2, profile.getEmail());
            ps.setString(3, profile.getBio());
            ps.setString(4, profile.getPosition().name()); // enum -> String
            ps.setObject(5, profile.getCareerYears());
            ps.setString(6, profile.getGithubUrl());
            ps.setString(7, profile.getBlogUrl());
            ps.setObject(8, LocalDateTime.now());
            ps.setObject(9, LocalDateTime.now());

            return ps;
        },  keyHolder);

        Long generatedId = keyHolder.getKey().longValue();
        profile.setId(generatedId);

        return profile;
        }

        // 나머지는 나중에 구현

    @Override
    public Optional<Profile> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Profile update(Profile profile) {
        return null;
    }

    @Override
    public  boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
