package ge.tsu.recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RecipeApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(RecipeApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map uploads directory to static resource URL path
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:${app.upload.dir:/tmp/recipe-uploads}/");
    }
}