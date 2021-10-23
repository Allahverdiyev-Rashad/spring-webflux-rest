package az.springframework.springwebfluxrest.controller;

import az.springframework.springwebfluxrest.domain.Category;
import az.springframework.springwebfluxrest.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping("api/v1/categories")
    Flux<Category> list() {
        return categoryRepository.findAll();
    }

    @GetMapping("api/v1/categories/{id}")
    Mono<Category> findById(@PathVariable String id) {
        return categoryRepository.findById(id);
    }
}
