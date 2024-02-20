package id.co.telkomsigma.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "monitorantasenaEntityManagerFactory",
        transactionManagerRef = "monitorantasenaTransactionManager",
        basePackages = "id.co.telkomsigma.monitorantasena.repository",
        repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class
)
public class MonitorAntasenaConfig {

    @Bean(name = "monitorantasenaDataSource")
    @ConfigurationProperties(prefix = "monitorantasena.datasource")
    public DataSource monitorantasenaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "monitorantasenaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean monitorantasenaEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("monitorantasenaDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("id.co.telkomsigma.monitorantasena.model")
                .persistenceUnit("monitorantasena")
                .build();
    }

    @Bean(name = "monitorantasenaTransactionManager")
    public PlatformTransactionManager monitorantasenaTransactionManager(
            @Qualifier("monitorantasenaEntityManagerFactory") EntityManagerFactory monitorantasenaEntityManagerFactory
    ) {
        return new JpaTransactionManager(monitorantasenaEntityManagerFactory);
    }


}
