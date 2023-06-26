package oneny.admin.category.repository;

import oneny.admin.category.domain.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateRepository implements CategoryRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcTemplateRepository(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public Category save(Category category) {
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    jdbcInsert.withTableName("category").usingGeneratedKeyColumns("id");

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("name", category.getName());
    if (category.getParent() != null) {
      parameters.put("parent_id", category.getParent().getId());
    } else {
      parameters.put("parent_id", null);
    }

    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    category.setId(key.longValue());
    return category;
  }

  @Override
  public Optional<Category> findById(Long id) {
    return jdbcTemplate.query("select id, name from category where id = ?", categoryRowMapper(), id)
            .stream()
            .findAny();

  }

  @Override
  public Optional<Category> findByName(String name) {
    return jdbcTemplate.query("select id, name from category where name = ?", categoryRowMapper(), name)
            .stream()
            .findAny();
  }

  @Override
  public List<Category> findAll() {
    return jdbcTemplate.query("select id, name from category", categoryRowMapper());
  }

  private RowMapper<Category> categoryRowMapper() {
    return (rs, rowNum) -> {
      Category category = new Category();
      category.setId(rs.getLong("id"));
      category.setName(rs.getString("name"));
      return category;
    };
  }
}
