package com.ibn.generate.common;

import com.ibn.generate.util.SpringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.common
 * @author： RenBin
 * @createTime：2020/11/17 16:08
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey = ThreadLocal.withInitial(() -> "defaultDataSource");

    public static Map<Object, Object> dataSourcesMap = new ConcurrentHashMap<>(10);

    static {
        dataSourcesMap.put("defaultDataSource", SpringUtils.getBean("defaultDataSource"));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        // 这个方法的作用就是返回一个key，该key对应当前你希望使用的DataSource。 跟前面设置的TargetDataSources呼应上了
        // 在AbstractRoutingDataSource的determineTargetDataSource方法中调用了determineCurrentLookupKey方法，切换了数据源
        return DynamicDataSource.dataSourceKey.get();
    }

    public static void setDataSource(String dataSource) {
        DynamicDataSource.dataSourceKey.set(dataSource);
        DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringUtils.getBean("dataSource");
        // AbstractRoutingDataSource实现了InitializingBean接口的afterPropertiesSet，
        // 会在实例化bean后调用此方法（AbstractAutowireCapableBeanFactory#invokeInitMethods方法调用的），
        // 所以resolvedDataSources和resolvedDefaultDataSource就有值了。
        dynamicDataSource.afterPropertiesSet();
    }

    public static String getDataSource() {
        return DynamicDataSource.dataSourceKey.get();
    }

    public static void clear() {
        DynamicDataSource.dataSourceKey.remove();
    }
}
