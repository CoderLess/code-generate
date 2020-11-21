package com.ibn.generate.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ibn.generate.common.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @version 1.0
 * @description: 动态数据源设置的原理
 *  1. 设置默认数据源即我们只有的所有目标数据源
 *  2. AbstractRoutingDataSource实现这个接口，这个接口中的determineCurrentLookupKey方法会返回一个key用来到目标数据源中查找对应的链接方式
 *  3. 切换数据源时只需要修改determineCurrentLookupKey中返回的key即可
 *  4. mybatis执行sql获取链接时会从datasource中获取connection
 * @projectName：code-generate
 * @see: com.ibn.generate.config
 * @author： RenBin
 * @createTime：2020/11/17 15:49
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource defaultDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    @DependsOn({"springUtils", "defaultDataSource"})
    public DynamicDataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // targetDataSources：持有我们多数据源的所有数据源，key为不重复的对象用于唯一标识一个数据源，value为数据源DataSource实例
        dynamicDataSource.setTargetDataSources(DynamicDataSource.dataSourcesMap);
        return dynamicDataSource;
    }
}
