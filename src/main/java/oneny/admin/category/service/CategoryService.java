package oneny.admin.category.service;

import oneny.admin.category.domain.Category;
import oneny.admin.category.exception.DuplicateException;
import oneny.admin.category.repository.CategoryRepository;
import oneny.admin.category.repository.MemoryCategoryRepository;

import java.util.List;
import java.util.Optional;

public class CategoryService {

  private static final String DUPLICATE_CATEGORY_MESSAGE = "이미 존재하는 카테고리입니다.";
  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Long addCategory(Category category) {
    validateDuplicateCategory(category);
    categoryRepository.save(category);
    return category.getId();
  }

  public List<Category> findCategories() {
    return categoryRepository.findAll();
  }

  public Optional<Category> findOneById(Long categoryId) {
    return categoryRepository.findById(categoryId);
  }

  private void validateDuplicateCategory(Category category) {
    categoryRepository.findByName(category.getName())
            .ifPresent(m -> {
              throw new DuplicateException(DUPLICATE_CATEGORY_MESSAGE);
            });
  }
}
