package com.iesch.ad.ProductoRest.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MiConfiguracion {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer corsConfigure(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry){
                corsRegistry.addMapping("/api/productoDTO/**")
                        .allowedOrigins("http://localhost:9999","http://192.168.50.47:9999") //Las ips que te pueden atacar | si pones ** se traga todas
                        .allowedMethods("GET") //Post estos son los metodos que va poder atacar
                        .maxAge(3600);
            }
        };
    }

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(new Info().title("Api de Productos").version("1.0").description("Es una Api de productos y documentacion"));
    }

}
