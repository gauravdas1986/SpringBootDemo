//package com.cogni.apartment.config;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//  entityManagerFactoryRef = "mysqlEntityManagerFactory",
//  transactionManagerRef = "mysqlTransactionManager",
//  basePackages = { "com.cogni.apartment.repository" }
//)
//public class MySqlDbConfig {
// 
//  @Bean(name = "mysqlDataSource")
//  @ConfigurationProperties(prefix = "mysql.datasource")
//  public DataSource dataSource() {
//    return DataSourceBuilder.create().build();
//  }
//  
//  @Bean(name = "mysqlEntityManagerFactory")
//  public LocalContainerEntityManagerFactoryBean 
//  barEntityManagerFactory(
//    EntityManagerFactoryBuilder builder,
//    @Qualifier("mysqlDataSource") DataSource dataSource
//  ) {
//    return
//      builder
//        .dataSource(dataSource)
//        .packages("com.cogni.mysql.domain")
//        .persistenceUnit("mysql")
//        .build();
//  }
//  @Bean(name = "mysqlTransactionManager")
//  public PlatformTransactionManager barTransactionManager(
//    @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory
//    barEntityManagerFactory
//  ) {
//    return new JpaTransactionManager(barEntityManagerFactory);
//  }
//}