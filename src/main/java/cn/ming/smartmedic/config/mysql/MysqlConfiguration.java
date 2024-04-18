package cn.ming.smartmedic.config.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = MysqlConfiguration.DAO, sqlSessionFactoryRef = "MysqlDefaultSqlSessionFactory")
public class MysqlConfiguration {

    /**
     * DAO 扫描包目录
     */
    static final String DAO = "cn.ming.smartmedic.dao";

    /**
     *  mapper.xml 配置目录
     */
    private final static String MAPPER = "classpath:mapper/*.xml";

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean(name = "MysqlDefaultDataSource")
    @Qualifier(value = "MysqlDefaultDataSource")
    public DataSource MysqlDefaultDataSource() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxWait(10*1000);//获取连接最大等待时间10s
        return dataSource;
    }

    @Bean(name = "MysqlDefaultTransactionManager")
    @Qualifier("MysqlDefaultTransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(MysqlDefaultDataSource());
    }

    @Bean(name = "MysqlDefaultSqlSessionFactory")
    @Qualifier("MysqlDefaultSqlSessionFactory")
    public SqlSessionFactory defaultSqlSessionFactory(@Qualifier("MysqlDefaultDataSource") DataSource defaultDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 设置数据源
        sessionFactory.setDataSource(defaultDataSource);
        // 设置扫描路径
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MysqlConfiguration.MAPPER));
        return sessionFactory.getObject();
    }

}
