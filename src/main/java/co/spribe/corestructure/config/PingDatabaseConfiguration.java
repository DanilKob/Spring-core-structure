package co.spribe.corestructure.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "co.spribe.corestructure.ping.repository",
        entityManagerFactoryRef = BeanNames.PING_TRANSACTION_MANAGER_FACTORY,
        transactionManagerRef = BeanNames.PING_TRANSACTION_MANAGER
)
public class PingDatabaseConfiguration {

    @Bean(BeanNames.PING_DATASOURCE_PROPERTIES)
    @Primary
    @ConfigurationProperties(prefix="ping-datasource")
    public DataSourceProperties pingDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = BeanNames.PING_DATASOURCE)
    public DataSource pingDataSource() {
        return pingDataSourceProperties()
                .initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }


//    @Bean(name = BeanNames.DATASOURCE)
//    @ConfigurationProperties(prefix="ping-datasource")
//    public DataSource dataSource(){
//        return DataSourceBuilder.create().build();
//    }


    @Primary
    @Bean(name = BeanNames.PING_TRANSACTION_MANAGER_FACTORY )
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier(BeanNames.PING_DATASOURCE) DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("co.spribe.corestructure.ping.model")
                .persistenceUnit("db1")
                .build();
    }

    @Primary
    @Bean(name = BeanNames.PING_TRANSACTION_MANAGER)
    public PlatformTransactionManager customerTransactionManager(
            @Qualifier(BeanNames.PING_TRANSACTION_MANAGER_FACTORY) EntityManagerFactory customerEntityManagerFactory
    ) {
        return new JpaTransactionManager(customerEntityManagerFactory);
    }

    @Bean
    @ConfigurationProperties(prefix = "ping-datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }
}
