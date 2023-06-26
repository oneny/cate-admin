package oneny.admin.category.repository;

import oneny.admin.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaCategoryRepository extends JpaRepository<Category, Long>, CategoryRepository {

  @Override
  Optional<Category> findByName(String name);
}
