package az.springframework.springwebfluxrest.controller;

import az.springframework.springwebfluxrest.domain.Vendor;
import az.springframework.springwebfluxrest.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

public class VendorControllerTest {

    WebTestClient webTestClient;
    VendorRepository vendorRepository;
    VendorController vendorController;

    @Before
    public void setUp() {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void list() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(Vendor.builder()
                                .firstname("Rashad")
                                .lastName("Allahverdiyev").build(),
                        Vendor.builder()
                                .firstname("Rovshan")
                                .lastName("Allahverdiyev").build()));

        webTestClient.get().uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    public void findById() {
        BDDMockito.given(vendorRepository.findById("someid"))
                .willReturn(Mono.just(Vendor.builder()
                        .firstname("Warren")
                        .lastName("Buffet").build()));

        webTestClient.get()
                .uri("/api/v1/vendors/someid")
                .exchange()
                .expectBody(Vendor.class);
    }

    @Test
    public void create() {
        BDDMockito.given(vendorRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Vendor.builder().build()));

        Mono<Vendor> categoryMono = Mono.just(Vendor.builder()
                .firstname("Rashad")
                .lastName("Allahverdiyev").build());

        webTestClient.post()
                .uri("/api/v1/vendors")
                .body(categoryMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void update() {
        BDDMockito.given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> categoryMono = Mono.just(Vendor.builder()
                .firstname("Rashad")
                .lastName("Allahverdiyev").build());

        webTestClient.put()
                .uri("/api/v1/vendors/someid")
                .body(categoryMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void delete() {
        BDDMockito.given(vendorRepository.deleteById(any(String.class)))
                .willReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/v1/vendors/someid")
                .exchange()
                .expectStatus()
                .isOk();
    }
}