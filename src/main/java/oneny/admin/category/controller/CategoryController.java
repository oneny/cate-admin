package oneny.admin.category.controller;

import oneny.admin.category.domain.Category;
import oneny.admin.category.service.CategoryService;
import oneny.admin.dto.CategoryFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/new")
  public String newForm() {
    return "new-form";
  }

  @PostMapping(value = "/new")
  public String create(CategoryFormDTO categoryForm) {
    Category category = new Category();
    category.setName(categoryForm.getName());

    categoryService.addCategory(category);

    return "redirect:/";
  }

  @GetMapping(value = "/{categoryId}")
  public String findCategory(@PathVariable Long categoryId, Model model) {
    Category category = categoryService.findOneById(categoryId).orElse(null);
    model.addAttribute("category", category);

    return "category";
  }

  @GetMapping("/list")
  public String list(Model model) {
    List<Category> categories = categoryService.findCategories();
    model.addAttribute("categories", categories);

    return "categoryList";
  }
}
