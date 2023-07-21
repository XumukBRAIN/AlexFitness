package com.example.crossFit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String CLIENT_TAG = "Контроллер для работы с клиентами";
    public static final String COACH_TAG = "Контроллер для работы с тренерами";
    public static final String MANAGER_TAG = "Контроллер для работы с менеджерами";
    public static final String REQUEST_FIT_TAG = "Контроллер для работы с заявками";
    public static final String SUBSCRIPTION_TAG = "Контроллер для работы с абонементами";
    public static final String UTIL_TAG = "Контроллер для утилит";
    public static final String ORDERS_TAG = "Контроллер для заказов интернет-магазина";
    public static final String ITEM_TAG = "Контроллер для товаров для интернет-магазина";
    public static final String AUTH_TAG = "Контроллер для аутентификации и авторизации";
    public static final String TURN_TAG = "Контроллер для работы с услугами зала";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfo("CrossFit Api",
                "Some custom description of API",
                "Spring Boot - 2.7.7",
                "https://github.com/XumukBRAIN/CrossFit#readme",
                new Contact("Ivan Kudryashov", "live:.cid.f25a1ae711d2391a", "kudryashov.id24@gmail.com"),
                "License of API", "https://swagger.io/docs/",
                Collections.emptyList());
    }

}

