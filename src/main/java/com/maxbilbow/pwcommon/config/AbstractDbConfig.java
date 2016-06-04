package com.maxbilbow.pwcommon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Max on 21/05/2016.
 */
public abstract class AbstractDbConfig
{
  @Bean(destroyMethod = "close")
  public abstract DataSource dataSource(Environment env);

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                     Environment env)
  {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource);
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//    entityManagerFactoryBean.setPackagesToScan("uk.co.utilisoft.bfp.domain");

    Properties jpaProperties = new Properties();

    //Configures the used database dialect. This allows Hibernate to create SQL
    //that is optimized for the used database.
    jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));

    //Specifies the action that is invoked to the database when the Hibernate
    //SessionFactory is created or closed.
    jpaProperties.put("hibernate.hbm2ddl.auto",
                      env.getRequiredProperty("hibernate.hbm2ddl.auto")
    );

    //Configures the naming strategy that is used when Hibernate creates
    //new database objects and schema elements
    jpaProperties.put("hibernate.ejb.naming_strategy",
                      env.getRequiredProperty("hibernate.ejb.naming_strategy")
    );

    //If the value of this property is true, Hibernate writes all SQL
    //statements to the console.
    jpaProperties.put("hibernate.show_sql",
                      env.getRequiredProperty("hibernate.show_sql")
    );

    //If the value of this property is true, Hibernate will format the SQL
    //that is written to the console.
    jpaProperties.put("hibernate.format_sql",
                      env.getRequiredProperty("hibernate.format_sql")
    );

    entityManagerFactoryBean.setJpaProperties(jpaProperties);

    return entityManagerFactoryBean;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
  {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }
}
