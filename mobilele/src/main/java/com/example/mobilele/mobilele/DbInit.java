package com.example.mobilele.mobilele;

import com.example.mobilele.mobilele.model.entities.*;
import com.example.mobilele.mobilele.model.entities.enums.EngineEnum;
import com.example.mobilele.mobilele.model.entities.enums.ModelCategoryEnum;
import com.example.mobilele.mobilele.model.entities.enums.TransmissionEnum;
import com.example.mobilele.mobilele.model.entities.enums.UserRoleEnum;
import com.example.mobilele.mobilele.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DbInit implements CommandLineRunner {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final OfferRepository offerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public DbInit(ModelRepository modelRepository, BrandRepository brandRepository, OfferRepository offerRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.offerRepository = offerRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        BrandEntity fordBrand = new BrandEntity();
        fordBrand.setName("Ford");

        BrandEntity hondaBrand = new BrandEntity();
        hondaBrand.setName("Honda");

        brandRepository.saveAll(List.of(fordBrand, hondaBrand));

        ModelEntity fiestaModel = initFiesta(fordBrand);
        ModelEntity escortModel = initEscort(fordBrand);
        ModelEntity nc750sModel = initNC750S(hondaBrand);
        createFiestaOffer(fiestaModel);

        initUsers();

    }

    private void initUsers() {

        UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

        userRoleRepository.saveAll(List.of(adminRole, userRole));

        UserEntity admin = new UserEntity();
        admin
                .setFirstName("Ignat")
                .setLastName("Ignatov")
                .setUsername("admin")
                .setPassword(passwordEncoder.encode("1423"))
                .setUserRoles(List.of(userRole, adminRole));

        UserEntity pesho = new UserEntity();
        pesho
                .setFirstName("Petar")
                .setLastName("Ivanov")
                .setUsername("pesho")
                .setPassword(passwordEncoder.encode("1423"))
                .setUserRoles(List.of(userRole));

        userRepository.saveAll(List.of(admin, pesho));
    }

    private void createFiestaOffer(ModelEntity modelEntity) {
        OfferEntity fiestaOffer = new OfferEntity();

        fiestaOffer
                .setEngine(EngineEnum.GASOLINE)
                .setImageUrl("https://www.motopfohe.bg/files/news/archive/2017/08/blob-server.jpg")
                .setMileage(80000)
                .setPrice(BigDecimal.valueOf(10000))
                .setYear(2019)
                .setDescription("Karana e ot nemska baba. Zimata v garaj.")
                .setTransmission(TransmissionEnum.MANUAL)
                .setModel(modelEntity);

        offerRepository.save(fiestaOffer);
    }

    private ModelEntity initNC750S(BrandEntity hondaBrand) {
        ModelEntity nc750s = new ModelEntity();

        nc750s
                .setName("NC750S")
                .setCategory(ModelCategoryEnum.MOTORCYCLE)
                .setImageUrl("https://motorcycles.honda.bg/wp-content/uploads/sites/4/2017/11/overview_01-9.jpg")
                .setStartYear(2014)
                .setBrand(hondaBrand);

        return modelRepository.save(nc750s);
    }

    private ModelEntity initEscort(BrandEntity fordBrand) {
        ModelEntity escort = new ModelEntity();

        escort
                .setName("Escort")
                .setCategory(ModelCategoryEnum.CAR)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/2/2c/97-02_Ford_Escort_sedan.jpg")
                .setStartYear(1968)
                .setEndYear(2002)
                .setBrand(fordBrand);

        return modelRepository.save(escort);
    }

    private ModelEntity initFiesta(BrandEntity fordBrand) {
        ModelEntity fiesta = new ModelEntity();

        fiesta
                .setName("Fiesta")
                .setCategory(ModelCategoryEnum.CAR)
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/a/a7/Ford_Fiesta_ST-Line_%28VII%2C_Facelift%29_–_f_30012023.jpg")
                .setStartYear(1976)
                .setBrand(fordBrand);

        return modelRepository.save(fiesta);
    }
}
