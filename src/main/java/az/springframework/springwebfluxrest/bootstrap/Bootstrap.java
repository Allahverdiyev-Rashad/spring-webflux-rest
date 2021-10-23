package az.springframework.springwebfluxrest.bootstrap;

import az.springframework.springwebfluxrest.domain.Category;
import az.springframework.springwebfluxrest.domain.Vendor;
import az.springframework.springwebfluxrest.repository.CategoryRepository;
import az.springframework.springwebfluxrest.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;


    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count().block() == 0) {
            //load data
            System.out.println("#### LOADING DATA ON BOOTSTRAP ####");
            System.out.println("#### Loading Categories ####");
            categoryRepository.save(Category.builder()
                    .description("Fruits").build()).block();
            categoryRepository.save(Category.builder()
                    .description("Nuts").build()).block();
            categoryRepository.save(Category.builder()
                    .description("Breads").build()).block();
            categoryRepository.save(Category.builder()
                    .description("Meats").build()).block();
            categoryRepository.save(Category.builder()
                    .description("Eggs").build()).block();

            System.out.println("Loaded Categories " + categoryRepository.count().block());
        }

        if (vendorRepository.count().block() == 0) {

            System.out.println("#### Loading Vendors ####");
            vendorRepository.save(Vendor.builder()
                    .firstname("Joe")
                    .lastName("Buck")
                    .build()).block();
            vendorRepository.save(Vendor.builder()
                    .firstname("Micheal")
                    .lastName("Weston")
                    .build()).block();
            vendorRepository.save(Vendor.builder()
                    .firstname("Jessie")
                    .lastName("Waiters")
                    .build()).block();
            vendorRepository.save(Vendor.builder()
                    .firstname("Bill")
                    .lastName("Nershi")
                    .build()).block();
            vendorRepository.save(Vendor.builder()
                    .firstname("Jimmy")
                    .lastName("Buffet")
                    .build()).block();

            System.out.println("Loaded Vendors " + vendorRepository.count().block());
        }
    }
}
