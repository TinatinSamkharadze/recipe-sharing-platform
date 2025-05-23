package ge.tsu.recipe;

import ge.tsu.recipe.config.i18n.I18nConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Import(I18nConfig.class)
public class RecipeSharingPlatformApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(RecipeSharingPlatformApplication.class, args);
    }
}
