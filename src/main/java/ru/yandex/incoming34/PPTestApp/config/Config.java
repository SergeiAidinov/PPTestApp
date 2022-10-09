package ru.yandex.incoming34.PPTestApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootConfiguration
//@PropertySource(value = "application.properties")
@ComponentScan("ru.yandex.incoming34.PPTestApp.**")
public class Config {

    @Value("${host}")
    String host;

    @Value("${port}")
    String port;

    @Value("${paypal.url.success}")
    String successUrl;

    @Value("${paypal.url.cancel}")
    String cancelUrl;

    @Bean
    public String hostAndPort(){
        return host + ":" + port;
    }

    @Bean(name = "successUrl")
    public String successlUrl(){
        return hostAndPort() + "/" + successUrl;
    }

    @Bean(name = "cancelUrl")
    public String cancelUrl(){
        return hostAndPort() + "/" + cancelUrl;
    }


}
