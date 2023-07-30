package bg.softuni.cloudinary.config;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationBeanConfiguration {

    private final CloudinaryConfiguration configuration;

    public ApplicationBeanConfiguration(CloudinaryConfiguration configuration) {
        this.configuration = configuration;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinary() {
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", configuration.getCloudName());
        config.put("api_key", configuration.getApiKey());
        config.put("api_secret", configuration.getApiSecret());
        return new Cloudinary(config);
    }

}
