package usst.web.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Swagger Configuration for Singularity Application
 * Provides API documentation for public and private endpoints.
 *
 * @author jyzxc
 * @since 2024-07-20
 */
@Configuration
@EnableWebMvc
@EnableKnife4j
public class SwaggerConfig {

    @Bean
    public OpenAPI customSwagger() {
        return new OpenAPI()
                .info(new Info()
                        .title("Advertisement API")
                        .version("1.0.0")
                        .description("广告投放系统的接口文档."));

    }
}
