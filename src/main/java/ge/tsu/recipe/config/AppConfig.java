package ge.tsu.recipe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-${spring.profiles.active}.properties")
@PropertySource(value = "file:${user.home}/myconfig.properties", ignoreResourceNotFound = true)
public class AppConfig {
}