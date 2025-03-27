package eu.sternbauer.EtlGenerator.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * The implemented beans enables to use a customized prefix in the application properties
 * to provide the location for the knowledge base. This is done to provide a more precise syntax
 * and avoid having both Spring Data JDBC and custom prefix syntax to declare the database locations.
 */
@Configuration
class KnowledgeBaseConfig {
    private final DatabaseProperties properties;

    @Autowired
    KnowledgeBaseConfig(DatabaseProperties properties) {
        this.properties = properties;
    }

    /**
     * Returns the configuration for Spring Data JDBC to connect with to the KnowledgeBase database
     * @return A {@link DataSource} object with the required information set.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(properties.knowledgebase().url());
        dataSource.setUsername(properties.knowledgebase().username());
        dataSource.setPassword(properties.knowledgebase().password());
        dataSource.setDriverClassName(properties.knowledgebase().driver_class_name());
        return dataSource;
    }
}
