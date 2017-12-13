package com.ftvalue.customer.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据库配置
 */
@Configuration
public class IsmpDruidConfig extends BaseDruidConfig {
    @Value("${spring.datasources.ismp.url}")
    private String url;

    @Value("${spring.datasources.ismp.password}")
    private String password;

    @Value("${spring.datasources.ismp.username}")
    private String username;

    @Value("${spring.datasources.ismp.druid.initialSize}")
    private int initialSize;

    @Value("${spring.datasources.ismp.druid.maxActive}")
    private int maxActive;

    @Value("${spring.datasources.ismp.druid.minIdle}")
    private int minIdle;

    @Value("${spring.datasources.ismp.druid.maxWait}")
    private int maxWait;

    @Value("${spring.datasources.ismp.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasources.ismp.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasources.ismp.druid.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasources.ismp.druid.testWhileIdle:true}")
    private boolean testWhileIdle;

    @Value("${spring.datasources.ismp.druid.testOnBorrow:false}")
    private boolean testOnBorrow;

    @Value("${spring.datasources.ismp.druid.testOnReturn:false}")
    private boolean testOnReturn;

    @Value("${spring.datasources.ismp.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasources.ismp.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasources.ismp.druid.filters}")
    private String filters;

    @Value("${spring.datasources.ismp.druid.connectionProperties:}")
    private String connectionProperties;

    @Value("${spring.datasources.ismp.druid.useGlobalDataSourceStat:false}")
    private boolean useGlobalDataSourceStat;

    /**
     * Druid数据源配置
     *
     * @return
     * @throws SQLException
     * @see <a href="https://github.com/alibaba/druid/wiki/配置_DruidDataSource参考配置">DruidDataSource参考配置</a>
     */
    @Bean(name = "ismpDataSource")
    @Qualifier("ismpDataSource")
    @Primary
    @Override
    public DruidDataSource druidDataSource() throws Exception {
        return super.druidDataSource();
    }

    @Bean
    @Override
    public ServletRegistrationBean druidServlet() {
        return super.druidServlet();
    }

    @Bean
    @Override
    public FilterRegistrationBean filterRegistrationBean() {
        return super.filterRegistrationBean();
    }


	@Bean(name = "iTransactionManager")
    @Override
	public DataSourceTransactionManager createTransactionManager(@Qualifier("ismpDataSource") DataSource dataSource) {
        return super.createTransactionManager(dataSource);
	}


    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getInitialSize() {
        return initialSize;
    }

    @Override
    public int getMaxActive() {
        return maxActive;
    }

    @Override
    public int getMinIdle() {
        return minIdle;
    }

    @Override
    public int getMaxWait() {
        return maxWait;
    }

    @Override
    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    @Override
    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    @Override
    public String getValidationQuery() {
        return validationQuery;
    }

    @Override
    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    @Override
    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    @Override
    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    @Override
    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    @Override
    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    @Override
    public String getFilters() {
        return filters;
    }

    @Override
    public String getConnectionProperties() {
        return connectionProperties;
    }

    @Override
    public boolean isUseGlobalDataSourceStat() {
        return useGlobalDataSourceStat;
    }
}