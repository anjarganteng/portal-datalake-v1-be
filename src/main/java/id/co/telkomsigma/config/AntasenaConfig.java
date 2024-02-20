//package id.co.telkomsigma.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
///**
// * @author satiya
// */
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "antasenaEntityManagerFactory",
//        transactionManagerRef = "antasenaTransactionManager",
//        basePackages = "id.co.telkomsigma.portalapps.repository",
//        repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class
//)
//public class AntasenaConfig {
//    @Primary
//    @Bean(name = "antasenaDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource antasenaDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = "antasenaEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean antasenaEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("antasenaDataSource") DataSource dataSource
//    ) {
//        return builder
//                .dataSource(dataSource)
//                .packages("id.co.telkomsigma.portalapps.model")
//                .persistenceUnit("antasena")
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "antasenaTransactionManager")
//    public PlatformTransactionManager antasenaTransactionManager(
//            @Qualifier("antasenaEntityManagerFactory") EntityManagerFactory antasenaEntityManagerFactory
//    ) {
//        return new JpaTransactionManager(antasenaEntityManagerFactory);
//    }
//
//}
