package co.spribe.corestructure.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@PropertySource({ConfigFileNames.MULTI_DATA_SOURCE_CONFIG_FILE_IN_CLASSPATH})
@EnableJpaRepositories(
        basePackages = "co.spribe.corestructure.statistic.model",
        entityManagerFactoryRef = BeanNames.STATISTIC_TRANSACTION_MANAGER_FACTORY,
        transactionManagerRef = BeanNames.STATISTIC_TRANSACTION_MANAGER)
public class StatisticDatabaseConfiguration {

    /*
        DOES NOT WORK WITH HIKARI DATA SOURCE
     */

//    @Bean(name = BeanNames.STATISTIC_DATASOURCE)
//    @ConfigurationProperties(prefix="statistic-datasource")
//    public DataSource statisticDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean(BeanNames.STATISTIC_DATASOURCE_PROPERTIES)
    @ConfigurationProperties(prefix="statistic-datasource")
    public DataSourceProperties statisticDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = BeanNames.STATISTIC_DATASOURCE)
    public DataSource memberDataSource() {
        return statisticDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = BeanNames.STATISTIC_TRANSACTION_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier(BeanNames.STATISTIC_DATASOURCE) DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("co.spribe.corestructure.statistic.model")
                .persistenceUnit("db2")
                .build();
    }

    @Bean(name = BeanNames.STATISTIC_TRANSACTION_MANAGER)
    public PlatformTransactionManager customerTransactionManager(
            @Qualifier(BeanNames.STATISTIC_TRANSACTION_MANAGER_FACTORY) EntityManagerFactory customerEntityManagerFactory
    ) {
        return new JpaTransactionManager(customerEntityManagerFactory);
    }
}
