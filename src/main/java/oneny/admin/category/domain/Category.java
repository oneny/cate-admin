package oneny.admin.category.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Category {

  private Long id;
  private String name;
  private Category parent;
  private List<Category> children = new ArrayList<>();

  public Category() {
  }

  public Category(String name, Category parent) {
    this.name = name.toUpperCase();
    this.parent = parent;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean matchName(String name) {
    return this.name.equals(name);
  }
}
