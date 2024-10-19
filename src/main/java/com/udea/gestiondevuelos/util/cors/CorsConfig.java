package com.udea.gestiondevuelos.util.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Habilita CORS para todas las rutas
                        .allowedOrigins("*") // Permite cualquier origen
                        .allowedMethods("*") // Permite todos los métodos HTTP (GET, POST, PUT, DELETE, etc.)
                        .allowedHeaders("*"); // Permite cualquier cabecera
            }
        };
    }
}
