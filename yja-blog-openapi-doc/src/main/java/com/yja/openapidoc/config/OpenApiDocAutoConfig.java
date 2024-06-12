package com.yja.openapidoc.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yang Jian
 * @date 2024/6/12 15:37
 * @description
 * url:ip:port/swagger-ui/index.html
 */
@Configuration
@EnableAutoConfiguration
@ConditionalOnProperty(prefix = "springdoc.api-docs", name = "enabled", havingValue = "true", matchIfMissing = true)
public class OpenApiDocAutoConfig {

    @Bean
    @ConditionalOnMissingBean(OpenAPI.class)
    OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().title("API文档")
                        .description("API文档")
                        .version("v1.0.0")
                        .contact(new Contact().name("yja").url("https://github.com/yja").email("2628168756@qq.com"))
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation().description("yjablog").url("https://springshop.wiki.github.org/docs"));

    }
}
