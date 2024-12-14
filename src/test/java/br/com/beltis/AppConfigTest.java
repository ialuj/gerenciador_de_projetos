package br.com.beltis;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotEquals;

@Ignore
public class AppConfigTest {

    @Test
    public void contextLoads() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        assertNotEquals(context.getBean(DataSource.class), "DataSource should not be null");
    }
}

