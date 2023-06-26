package oneny.admin.category.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private List<Category> children = new ArrayList<>();

  public Category() {
  }

  public Category(String name, Category parent) {
    this(null, name, parent);
  }

  public Category(Long id, String name, Category parent) {
    this.id = id;
    this.name = name;
    this.parent = parent;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean matchName(String name) {
    return this.name.equals(name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return Objects.equals(getId(), category.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
