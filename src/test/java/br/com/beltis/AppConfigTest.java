package br.com.beltis;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("br.com.beltis")
@EnableTransactionManagement
@ImportResource("classpath:applicationContext-test.xml")
@Profile("test")
public class AppConfigTest {

    public AppConfigTest() {
        System.out.println("AppConfigTest carregado com sucesso!");
    }

}

