## Sample java reactive programming project with:

* ### Spring Webflux
* ### Reactive Mongo
* ### Junit4

---

### Category entity:
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Category {
    @Id
    private String id;
    private String description;
}
```

### __*ReactiveMongoRepository*__ :

```java 
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
```

### Sample get endpoint :

```java 
    @GetMapping("api/v1/categories/{id}")
    Mono<Category> findById(@PathVariable String id) {
        return categoryRepository.findById(id);
    }
```

### Junit4 test :

```java
public class CategoryControllerTest {

    WebTestClient webTestClient;
    CategoryRepository categoryRepository;
    CategoryController categoryController;

    @Before
    public void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryController = new CategoryController(categoryRepository);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }
    
    @Test
    public void findById(){
        BDDMockito.given(categoryRepository.findById("someid"))
        .willReturn(Mono.just(Category.builder().description("Cat").build()));

        webTestClient.get()
        .uri("/api/v1/categories/someid")
        .exchange()
        .expectBody(Category.class);
        } 
}
```

---
> This project does not contain service layer