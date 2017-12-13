package com.ftvalue.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by 郝丹辉 on 2017/9/13.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "ismpEntityManagerFactory",
        transactionManagerRef = "ismpTransactionManager",
        basePackages = {"com.ftvalue.customer.dao.ismp"}
)
public class IsmpDataSourceConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("ismpDataSource")
    private DataSource ismpDataSource;
    /**
     * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
     * @return
     */
    @Bean(name = "ismpEntityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean ismpEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(ismpDataSource)
                .properties(getVendorProperties(ismpDataSource))
                .packages("com.ftvalue.customer.domain.ismp") //设置实体类所在位置
                .persistenceUnit("ismpPersistenceUnit")
                .build();
    }
    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }
    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @param builder
     * @return
     */
    @Bean(name = "ismpEntityManagerFactory")
    public EntityManagerFactory ismpEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.ismpEntityManagerFactoryBean(builder).getObject();
    }
    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "ismpTransactionManager")
    @Primary
    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(ismpEntityManagerFactory(builder));
    }
}
