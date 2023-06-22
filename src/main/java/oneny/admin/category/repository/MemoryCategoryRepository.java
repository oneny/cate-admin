package oneny.admin.category.repository;

import oneny.admin.category.domain.Category;

import java.util.*;

public class MemoryCategoryRepository implements CategoryRepository {

  private static Map<Long, Category> store = new HashMap<>();
  private static long sequence = 1L;


  @Override
  public Category save(Category category) {
    category.setId(++sequence);
    store.put(category.getId(), category);
    return category;
  }

  @Override
  public Optional<Category> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Category> findByName(String name) {
    return store.values().stream()
            .filter(category -> category.matchName(name))
            .findAny();
  }

  @Override
  public List<Category> findAll() {
    return new ArrayList<>(store.values());
  }

  public void clearStore() {
    store.clear();
  }
}
