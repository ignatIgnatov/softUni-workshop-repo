package bg.softuni.cloudinary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cloudinary")
public class CloudinaryConfiguration {

  @Value("${cloudinary.cloud-name}")
  private String cloudName;
  @Value("${cloudinary.api-key}")
  private String apiKey;
  @Value("${cloudinary.api-secret}")
  private String apiSecret;

  public String getCloudName() {
    return cloudName;
  }

  /**
   * Sets the cloud name associated with the cloudinary account
   * @param cloudName
   * @return this
   */

  public CloudinaryConfiguration setCloudName(String cloudName) {
    this.cloudName = cloudName;
    return this;
  }

  public String getApiKey() {
    return apiKey;
  }

  public CloudinaryConfiguration setApiKey(String apiKey) {
    this.apiKey = apiKey;
    return this;
  }

  public String getApiSecret() {
    return apiSecret;
  }

  public CloudinaryConfiguration setApiSecret(String apiSecret) {
    this.apiSecret = apiSecret;
    return this;
  }
}
