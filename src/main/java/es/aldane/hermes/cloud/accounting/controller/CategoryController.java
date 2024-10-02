package es.aldane.hermes.cloud.accounting.controller;

import es.aldane.hermes.cloud.accounting.service.CategoryService;
import es.aldane.hermes.cloud.accounting_api_server_java.api.CategoryApi;
import es.aldane.hermes.cloud.accounting_api_server_java.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<Category> createCategory(@Valid Category category) {
        var newCategory = categoryService.createCategory(category);
        return newCategory != null ? ResponseEntity.ok(newCategory) : ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Void> deleteCategory(Long categoryId) {
        var category = categoryService.deleteCategory(categoryId);
        return category ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Category> getCategoryById(Long categoryId) {
        var category = categoryService.getCategoryById(categoryId);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Category>> getCategories() {
        var states = categoryService.getCategories(new ArrayList<>());
        return ResponseEntity.ok(states);
    }

    @Override
    public ResponseEntity<Category> updateCategory(@Valid Category category) {
        return categoryService.updateCategory(category) != null ? ResponseEntity.ok(category) : ResponseEntity.badRequest().build();
    }
}
