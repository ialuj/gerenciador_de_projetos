package br.com.beltis;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("br.com.beltis")
@EnableTransactionManagement
@ImportResource("classpath:applicationContext.xml")
public class AppConfig {

    public AppConfig() {
        System.out.println("AppConfig carregado com sucesso!");
    }
}

