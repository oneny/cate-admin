package oneny.admin.category.repository;

import oneny.admin.category.domain.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemoryCategoryRepositoryTest {

  MemoryCategoryRepository repository = new MemoryCategoryRepository();
  private Category parentCategory;
  private Category childCategory;

  @BeforeEach
  void setUp() {
    // given
    parentCategory = new Category("OUTER", null);
    childCategory = new Category("JACKET", parentCategory);
    repository.save(parentCategory);
    repository.save(childCategory);
  }

  @AfterEach
  void tearDown() {
    repository.clearStore();
  }

  @Test
  public void save() {
    // when
    Category findCategory = repository.findByName("JACKET").get();

    // then
    assertAll(
            () -> assertThat(findCategory).isEqualTo(childCategory),
            () -> assertThat(findCategory.getParent()).isEqualTo(parentCategory)
    );
  }

  @Test
  public void findAll() {
    // when
    List<Category> categories = repository.findAll();

    // then
    assertThat(categories).hasSize(2);
  }
}
