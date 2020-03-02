package p12.ocr.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("api.insee")
@Getter
@Setter
public class ApiInseeConfig {

    private String url;
    private String key;
}
