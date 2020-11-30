package spring.app.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;


@Configuration
@ComponentScan("spring.app")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
    
	
	
	  @Bean(name = "dataSource") public javax.sql.DataSource getDataSource() {
		  BasicDataSource dataSource = new BasicDataSource();
		  dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		  dataSource.setUrl("jdbc:mysql://localhost:3306/my_spring_app_db?&serverTimezone=UTC");
		  dataSource.setUsername("root"); dataSource.setPassword("123"); return
		  dataSource; }
	 
	  
	  @Bean(name = "hibernateProperties")
	  public Properties getHibernateProperties() { 
		  Properties properties = new
		  Properties(); 
		  properties.put("hibernate.show_sql", "true");
		  properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		  properties.put("hibernate.hbm2ddl.auto", "update"); 
		  properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
		  return properties; 
		  }
	  
	/*
	 * @Autowired
	 * 
	 * @Bean(name = "sessionFactory") public SessionFactory
	 * getSessionFactory(javax.sql.DataSource dataSource) {
	 * LocalSessionFactoryBuilder sessionBuilder = new
	 * LocalSessionFactoryBuilder(dataSource);
	 * sessionBuilder.scanPackages("spring.app");
	 * sessionBuilder.addProperties(getHibernateProperties()); return
	 * sessionBuilder.buildSessionFactory(); }
	 * 
	 * 
	 * 
	 * @Autowired
	 * 
	 * @Bean(name = "transactionManager") public HibernateTransactionManager
	 * getTransactionManager( SessionFactory sessionFactory) {
	 * HibernateTransactionManager transactionManager = new
	 * HibernateTransactionManager( sessionFactory);
	 * 
	 * return transactionManager; }
	 */
	 
	 
}

