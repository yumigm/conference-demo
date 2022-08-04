
package com.pluralsight.conferencedemo.config;


//used to costumize changes or create changes to our class

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
//     @Bean
//     public DataSource dataSource(){
//         DataSourceBuilder builder = DataSourceBuilder.create();
//         builder.url("jdbc:postgresql://localhost:5432/conference_app");
//         System.out.println("Custom DataSource Set");
//         return builder.build();
//     }


}
