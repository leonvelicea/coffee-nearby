package org.dev.test.coffeenearby.config;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GeometryFactory geometryFactory() {
      return new GeometryFactory(new PrecisionModel(), 4326);
    }
}
