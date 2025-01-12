package dev.ash.core.lib.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration class for setting up JPA transaction management. This class provides the necessary beans for handling database transactions in a JPA-based application.
 *
 * <p>This configuration is automatically picked up by Spring Boot's
 * auto-configuration mechanism when included in the component scan path.</p>
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig {

  /**
   * Default constructor for TransactionConfig. Creates a new instance of the transaction configuration.
   */
  public TransactionConfig() {
    // Default constructor required by Spring Framework
  }

  /**
   * Creates and configures a JpaTransactionManager bean. This transaction manager handles the transaction lifecycle for JPA operations.
   *
   * @param em the EntityManagerFactory to be used for creating EntityManager instances
   * @return configured JpaTransactionManager instance
   */
  @Bean
  @Primary
  public JpaTransactionManager transactionManager(final EntityManagerFactory em) {
    return new JpaTransactionManager(em);
  }

}
