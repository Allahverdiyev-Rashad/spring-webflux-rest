package az.springframework.springwebfluxrest.controller;

import az.springframework.springwebfluxrest.domain.Vendor;
import az.springframework.springwebfluxrest.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class VendorController {

    private final VendorRepository vendorRepository;

    @GetMapping("api/v1/vendors")
    Flux<Vendor> list() {
        return vendorRepository.findAll();
    }

    @GetMapping("api/v1/vendors/{id}")
    Mono<Vendor> findById(@PathVariable String id) {
        return vendorRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("api/v1/vendors")
    Mono<Void> create(@RequestBody Publisher<Vendor> vendor) {
        return vendorRepository.saveAll(vendor).then();
    }

    @PutMapping("api/v1/vendors/{id}")
    Mono<Vendor> update(@PathVariable String id,
                        @RequestBody Vendor vendor) {
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }

    @DeleteMapping("api/v1/vendors/{id}")
    Mono<Void> delete(@PathVariable String id) {
        return vendorRepository.deleteById(id);
    }
}
