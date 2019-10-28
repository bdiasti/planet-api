package br.com.b2w.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.b2w.domain.Planet;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author bdiasti
 *
 * Classe responsável pela documentação da API
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.b2w.v1.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Planet Api service",
                "Esta aplicação permite cadastrar planetas do Star Wars e verifica em quais filmes aparecem tais planetas",
                "1.0.0",
                "TERMS OF SERVICE URL",
                new Contact("Bernardo Dias", "https://www.linkedin.com/in/bernardo-dias-0807a873/", "bdias.ti@gmail.com"),
                "MIT License",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
