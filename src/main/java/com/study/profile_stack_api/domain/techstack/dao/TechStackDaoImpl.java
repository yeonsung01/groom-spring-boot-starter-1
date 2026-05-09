package com.study.profile_stack_api.domain.techstack.dao;
import com.study.profile_stack_api.domain.techstack.entity.Proficiency;
import com.study.profile_stack_api.domain.techstack.entity.TechCategory;
import com.study.profile_stack_api.domain.techstack.entity.TechStack;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TechStackDaoImpl implements TechStackDao {

    private  final JdbcTemplate jdbcTemplate;

    public TechStackDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    LocalDateTime now = LocalDateTime.now();

    @Override
    public List<TechStack> findAllByProfileId(
            Long profileId,
            int offset,
            int limit,
            String category,
            String proficiency
    ) {
        String sql = """
                SELECT *
                FROM tech_stack
                WHERE profile_id = ?
                  AND (? IS NULL OR category = ?)
                  AND (? IS NULL OR proficiency = ?)
                ORDER BY id ASC
                LIMIT ? OFFSET ?
                """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new TechStack(
                        rs.getLong("id"),
                        rs.getLong("profile_id"),
                        rs.getString("name"),
                        TechCategory.valueOf(rs.getString("category")),
                        Proficiency.valueOf(rs.getString("proficiency")),
                        rs.getInt("years_of_exp"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()

                ),
                profileId,
                category, category,
                proficiency, proficiency,
                limit, offset
                );
    }

    // 기술 스택 개수 조회
    @Override
    public long countByProfileId(
            Long profileId,
            String category,
            String proficiency
    ) {
        String sql = """
                SELECT COUNT(*)
                FROM tech_stack
                WHERE profile_id = ?
                  AND (? IS NULL OR category = ?)
                  AND (? IS NULL OR proficiency =?)
                """;
        return jdbcTemplate.queryForObject(
                sql,
                Long.class,
                profileId,
                category, category,
                proficiency, proficiency
        );
    }

    @Override
    public TechStack save(TechStack techStack) {

    String sql = """
        INSERT INTO tech_stack
        (profile_id, name, category, proficiency, years_of_exp, created_at, updated_at)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});

        ps.setLong(1, techStack.getProfileId());
        ps.setString(2, techStack.getName());
        ps.setString(3, techStack.getCategory().name());
        ps.setString(4, techStack.getProficiency().name());
        ps.setInt(5, techStack.getYearsOfExp());
        ps.setObject(6, now);
        ps.setObject(7, now);

        return ps;
    }, keyHolder);


    Long id = keyHolder.getKey().longValue();
    techStack.setId(id);
    techStack.setCreatedAt(now);
    techStack.setUpdatedAt(now);

    return techStack; // 🔥 반드시 반환
  }

  @Override
    public Optional<TechStack> findByIdAndProfileId(Long profileId, Long id) {

        String sql = """
                SELECT * 
                FROM tech_stack
                WHERE profile_id = ? AND id = ? 
                """;

        List<TechStack> result = jdbcTemplate.query(sql,
                (rs, rowNum) -> new TechStack(
                        rs.getLong("id"),
                        rs.getLong("profile_id"),
                        rs.getString("name"),
                        TechCategory.valueOf(rs.getString("category")),
                        Proficiency.valueOf(rs.getString("proficiency")),
                        rs.getInt("years_of_exp"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                ),
                                profileId, id
        );
      // 결과 없으면 empty, 있으면 첫 번째 반환
      return result.stream().findFirst();
  }

  @Override
    public TechStack update(TechStack techStack) {
        String sql = """
                UPDATE tech_stack
                SET name = ?,
                    category = ?,
                    proficiency = ?,
                    years_of_exp = ?,
                    updated_at = ?
                WHERE id = ? AND profile_id = ?
                """;
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update(sql,
                techStack.getName(),
                techStack.getCategory().name(),
                techStack.getProficiency().name(),
                techStack.getYearsOfExp(),
                now,
                techStack.getId(),
                techStack.getProfileId()
        );

        techStack.setUpdatedAt(now);

        return techStack; }

        @Override
                public boolean deleteByIdAndProfileId(Long profileId, Long id) {
            String sql = """
                    DELETE FROM tech_stack
                    WHERE profile_id = ? AND id = ?
                    """;

            int affectedRows = jdbcTemplate.update(sql, profileId, id);

            return affectedRows > 0;
      }
  }
