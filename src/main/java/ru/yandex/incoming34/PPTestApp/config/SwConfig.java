package ru.yandex.incoming34.PPTestApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.assertj.core.internal.Predicates;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootConfiguration
@ComponentScan("ru.yandex.incoming34.PPTestApp.**")
public class SwConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfo("PalPay test application",
                "Educational project",
                "1.0",
                null,
                "incoming34@yandex.ru",
                null,
                null)
                ;
    }

    @Bean
    public OpenAPI customOpenApi(){
       OpenAPI openAPI = new OpenAPI();
       openAPI.setInfo(info());
       return openAPI;
    }

    @Bean
    Info info(){
        return  new Info().title("PalPay test application");
    }

 /*
    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

  */



}
