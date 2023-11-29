package com.example.labb3;

import org.geolatte.geom.json.GeolatteGeomModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    GeolatteGeomModule geolatteModule(){
        return new GeolatteGeomModule();
    }
}

