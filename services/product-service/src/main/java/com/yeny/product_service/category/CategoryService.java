package com.yeny.product_service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<CategoryResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(c -> new CategoryResponse(c.getId(), c.getName(), c.getDescription()))
                .collect(Collectors.toList());
    }

    public CategoryResponse create(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        Category saved = repository.save(category);
        return new CategoryResponse(saved.getId(), saved.getName(), saved.getDescription());
    }

    public CategoryResponse update(Integer id, CategoryRequest request) {
        Category category = repository.findById(id).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        category.setName(request.name());
        category.setDescription(request.description());
        Category saved = repository.save(category);
        return new CategoryResponse(saved.getId(), saved.getName(), saved.getDescription());
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
