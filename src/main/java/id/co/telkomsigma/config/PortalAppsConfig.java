package id.co.telkomsigma.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author satiya
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "portalappsEntityManagerFactory",
        transactionManagerRef = "portalappsTransactionManager",
        basePackages = "id.co.telkomsigma.portalapps.repository",
        repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class
)
public class PortalAppsConfig {

    @Primary
    @Bean(name = "portalappsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource portalappsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "portalappsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean portalappsEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("portalappsDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("id.co.telkomsigma.portalapps.model")
                .persistenceUnit("portalapps")
                .build();
    }

    @Primary
    @Bean(name = "portalappsTransactionManager")
    public PlatformTransactionManager portalappsTransactionManager(
            @Qualifier("portalappsEntityManagerFactory") EntityManagerFactory portalappsEntityManagerFactory
    ) {
        return new JpaTransactionManager(portalappsEntityManagerFactory);
    }

}
