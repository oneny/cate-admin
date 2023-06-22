package oneny.admin.category.service;

import oneny.admin.category.domain.Category;
import oneny.admin.category.exception.DuplicateException;
import oneny.admin.category.repository.CategoryRepository;
import oneny.admin.category.repository.MemoryCategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CategoryServiceTest {

  private CategoryService categoryService;
  private MemoryCategoryRepository categoryRepository;

  @BeforeEach
  public void setUp() {
    categoryService = new CategoryService();
    categoryRepository = new MemoryCategoryRepository();
  }

  @AfterEach
  void tearDown() {
    categoryRepository.clearStore();
  }

  @Test
  public void 카테고리_등록() {
    // given
    Category category = new Category("OUTER", null);

    // when
    Long savedId = categoryService.addCategory(category);

    // then
    Category findCategory = categoryService.findOneById(savedId).get();
    assertThat(findCategory).isEqualTo(category);
  }

  @Test
  public void 중복_카테고리_예외() {
    // given
    Category category1 = new Category("OUTER", null);
    Category category2 = new Category("OUTER", null);

    // when
    categoryService.addCategory(category1);

    // then
    assertThatThrownBy(() -> categoryService.addCategory(category2))
            .isInstanceOf(DuplicateException.class)
            .hasMessage("이미 존재하는 카테고리입니다.");
  }
}
