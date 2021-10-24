package az.springframework.springwebfluxrest.controller;

import az.springframework.springwebfluxrest.domain.Category;
import az.springframework.springwebfluxrest.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("api/v1/categories")
    Mono<Void> create(@RequestBody Publisher<Category> category) {
        return categoryRepository.saveAll(category).then();
    }

    @PutMapping("api/v1/categories/{id}")
    Mono<Category> update(@PathVariable String id,
                          @RequestBody Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    @DeleteMapping("api/v1/categories/{id}")
    Mono<Void> delete(@PathVariable String id) {
        return categoryRepository.deleteById(id);
    }
}
