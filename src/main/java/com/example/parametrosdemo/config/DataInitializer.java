package com.example.parametrosdemo.config;

import com.example.parametrosdemo.entity.Parametro1Entity;
import com.example.parametrosdemo.entity.Parametro2Entity;
import com.example.parametrosdemo.entity.Parametro3Entity;
import com.example.parametrosdemo.repository.Parametro1Repository;
import com.example.parametrosdemo.repository.Parametro2Repository;
import com.example.parametrosdemo.repository.Parametro3Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadSampleData(
            Parametro1Repository parametro1Repository,
            Parametro2Repository parametro2Repository,
            Parametro3Repository parametro3Repository) {
        return args -> {
            Parametro1Entity parametro1 = new Parametro1Entity();
            parametro1.setNombre("Moneda");
            parametro1.setValor("ARS");
            parametro1Repository.save(parametro1);

            Parametro2Entity parametro2 = new Parametro2Entity();
            parametro2.setNombre("Canal de venta");
            parametro2.setValor("WEB");
            parametro2.setCampo1b("Activo");
            parametro2.setCampo2b("Nacional");
            parametro2Repository.save(parametro2);

            Parametro3Entity parametro3 = new Parametro3Entity();
            parametro3.setNombre("Configuracion avanzada");
            parametro3.setCampo1c("Nivel 1");
            parametro3.setCampo2c("Grupo A");
            parametro3.setCampo3c("Visible");
            parametro3Repository.save(parametro3);
        };
    }
}
