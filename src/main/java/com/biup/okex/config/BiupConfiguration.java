package com.biup.okex.config;

import com.biup.okex.core.base.config.AbstractDruidDBConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 核心配置，配置数据源 事物 sqlsession
 */
@Configuration
@EnableTransactionManagement
public class BiupConfiguration extends AbstractDruidDBConfig {

    @Value("${db_exchange_jdbc_url}")
    private String url;

    @Value("${db_exchange_jdbc_username}")
    private String username;

    @Value("${db_exchange_jdbc_password}")
    private String password;


    // 注册dataSource
    @Bean(name = "biupDatasource", initMethod = "init", destroyMethod = "close")
    @Primary
    public DataSource dataSource() {
        return super.createDataSource(url, username, password);
    }

    @Override
    @Bean(name = "biupSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource biupDatasource) throws Exception {
        SqlSessionFactory sqlSessionFactory = super.sqlSessionFactory(biupDatasource);
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory;
    }

    @Bean(name = "biupTransactional")
    @Primary
    public PlatformTransactionManager transactionManager(DataSource biupDatasource) throws SQLException {
        return new DataSourceTransactionManager(biupDatasource);
    }

}
