package oneny.admin;

import oneny.admin.category.repository.CategoryRepository;
import oneny.admin.category.repository.JdbcCategoryRepository;
import oneny.admin.category.service.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

  private final DataSource dataSource;

  public AppConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  public CategoryService categoryService() {
    return new CategoryService(categoryRepository());
  }

  @Bean
  public CategoryRepository categoryRepository() {
//    return new MemoryCategoryRepository();
    return new JdbcCategoryRepository(dataSource);
  }
}
