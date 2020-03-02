package p12.ocr.web.config;


import p12.ocr.web.technical.date.SimpleDate;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public SimpleDate simpleDate(){ return  new SimpleDate(); }






}
