package oneny.admin.category.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import oneny.admin.category.domain.Category;

import java.util.List;
import java.util.Optional;

public class JpaCategoryRepository implements CategoryRepository {

  private final EntityManager em;

  public JpaCategoryRepository(EntityManager em) {
    this.em = em;
  }

  @Override
  public Category save(Category category) {
    em.persist(category);

    return category;
  }

  @Override
  public Optional<Category> findById(Long id) {
    Category category = em.find(Category.class, id);

    return Optional.ofNullable(category);
  }

  @Override
  public Optional<Category> findByName(String name) {
    List<Category> result = em.createQuery("select c from Category c where c.name = :name", Category.class)
            .setParameter("name", name)
            .getResultList();

    return result.stream().findAny();
  }

  @Override
  public List<Category> findAll() {
    return em.createQuery("select c from category c", Category.class)
            .getResultList();
  }
}
