package com.ftvalue.customer.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by 郝丹辉 on 2017/9/13.
 */
@Configuration
public class DruidDataSourceConfig {


    @Bean(name = "ismpDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasources.ismp")
    public DataSource ismpDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "bossDataSource")
    @ConfigurationProperties(prefix = "spring.datasources.boss")
    public DataSource bossDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "settleDataSource")
    @ConfigurationProperties(prefix = "spring.datasources.settle")
    public DataSource settleDataSource(){
        return new DruidDataSource();
    }

}
