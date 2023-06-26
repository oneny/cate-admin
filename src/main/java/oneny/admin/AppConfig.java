package oneny.admin;

import jakarta.persistence.EntityManager;
import oneny.admin.category.repository.CategoryRepository;
import oneny.admin.category.repository.JpaCategoryRepository;
import oneny.admin.category.service.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//  private final EntityManager em;
//
//  public AppConfig(EntityManager em) {
//    this.em = em;
//  }

  private final CategoryRepository categoryRepository;

  public AppConfig(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Bean
  public CategoryService categoryService() {
    return new CategoryService(categoryRepository);
  }

//  @Bean
//  public CategoryRepository categoryRepository() {
//    return new-form.html MemoryCategoryRepository();
//    return new-form.html JdbcCategoryRepository(dataSource);
//    return new JdbcTemplateRepository(dataSource);
//    return new JpaCategoryRepository(em);
//  }
}
