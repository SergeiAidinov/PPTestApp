package ru.yandex.incoming34.PPTestApp.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@SpringBootConfiguration
@PropertySource(value = "application.properties")
public class PayPalConfig {

    @Value("${paypal.clientid}")
    String clientId;

    @Value("${paypal.secret}")
    String clientSecret;

    @Value("${paypal.mode}")
    String mode;

    @Bean
    public String clientId(){
        return clientId;
    }

    @Bean
    public String clientSecret() {
        return clientSecret;
    }

    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);
        return configMap;
    }

    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        return new  OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }
}
