package com.wisemapping.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

@SpringBootApplication
@ImportResource("spring/wisemapping.xml")
@ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    HandlerExceptionResolver errorHandler() {
        final SimpleMappingExceptionResolver result = new SimpleMappingExceptionResolver();

        //mapping status code with view response.
        result.addStatusCode("reactInclude", 403);

        //setting default error view
        result.setDefaultErrorView("reactInclude");
        result.setDefaultStatusCode(500);
        return result;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.username("SA");
//        dataSourceBuilder.password("");
//        dataSourceBuilder.url("jdbc:hsqldb:file:/db/wisemapping");
//        dataSourceBuilder.driverClassName("org.hsqldb.jdbc.JDBCDriver");
//        return dataSourceBuilder.build();
//    }

}


