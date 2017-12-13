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
        entityManagerFactoryRef = "bossEntityManagerFactory",
        transactionManagerRef = "bossTransactionManager",
        basePackages = {"com.ftvalue.customer.dao.boss"}
)
public class BossDataSourceConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("bossDataSource")
    private DataSource bossDataSource;
    /**
     * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
     * @return
     */
    @Bean(name = "bossEntityManagerFactoryBean")
    //@Primary
    public LocalContainerEntityManagerFactoryBean bossEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(bossDataSource)
                .properties(getVendorProperties(bossDataSource))
                .packages("com.ftvalue.customer.domain.boss") //设置实体类所在位置
                .persistenceUnit("bossPersistenceUnit")
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
    @Bean(name = "bossEntityManagerFactory")
    public EntityManagerFactory bossEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.bossEntityManagerFactoryBean(builder).getObject();
    }
    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "bossTransactionManager")
    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(bossEntityManagerFactory(builder));
    }
}
