package com.andersen.library.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringfoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .ignoredParameterTypes(Authentication.class)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Pageable.class, SwaggerPageable.class);
    }

    @Data
    public static class SwaggerPageable {

        @ApiModelProperty(value = "Number of records per page", example = "20")
        private int size;

        @ApiModelProperty(value = "Results page you want to retrieve (0..N)", example = "0")
        private int page;

        @ApiModelProperty(value = "Sorting criteria in the format: property(,asc|desc)." +
                "Default sort order is ascending. Multiple sort criteria are supported.")
        private String sort;

    }

}
