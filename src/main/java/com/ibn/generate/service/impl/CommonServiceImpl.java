package com.ibn.generate.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ibn.generate.common.DynamicDataSource;
import com.ibn.generate.cons.DataSourceCons;
import com.ibn.generate.entity.GenrateConfigDO;
import com.ibn.generate.entity.TemplateConfigDO;
import com.ibn.generate.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.service.impl
 * @author： RenBin
 * @createTime：2020/11/17 10:57
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    private AtomicLong atomicLong = new AtomicLong(0L);

    private Map<Long, GenrateConfigDO> configMap = Maps.newConcurrentMap();

    @Override
    public Long generateId() {
        return atomicLong.addAndGet(1L);
    }

    @Override
    public Boolean saveDataSource(Long id, DataSource dataSource) {
        GenrateConfigDO genrateConfigDO = configMap.get(id);
        if (null == genrateConfigDO) {
            genrateConfigDO = new GenrateConfigDO();
        }
        genrateConfigDO.setDataSource(dataSource);
        configMap.put(id, genrateConfigDO);
        return true;
    }

    @Override
    public DataSource queryDataSource(Long id) {
        GenrateConfigDO genrateConfigDO = configMap.get(id);
        if (null == genrateConfigDO) {
            return null;
        }
        return genrateConfigDO.getDataSource();
    }

    @Override
    public Boolean setDataSource(Long id) {
        if (null == id) {
            logger.info("请先设置数据库连接方式");
            return false;
        }
        DruidDataSource dataSource = (DruidDataSource) this.queryDataSource(id);
        if (null == dataSource) {
            logger.info("获取数据库连接方式失败，请重新设置数据库连接方式");
            return false;
        }

        DynamicDataSource.dataSourcesMap.put(DataSourceCons.DATA_SOURCE_KEY, dataSource);
        DynamicDataSource.setDataSource(DataSourceCons.DATA_SOURCE_KEY);
        return true;
    }

    @Override
    public Boolean setTableNameList(Long id, String schemaName, List<String> tableNameList) {
        GenrateConfigDO genrateConfigDO = configMap.get(id);
        if (null == genrateConfigDO) {
            return false;
        }
        genrateConfigDO.setSchemaName(schemaName);
        genrateConfigDO.setTableNameList(tableNameList);
        return true;
    }

    @Override
    public List<String> queryProjectList() {
        File curFile = new File(".");
        File[] fileArray = curFile.listFiles(file -> file.isDirectory());
        if (null == fileArray && fileArray.length < 1) {
            return Lists.newArrayList();
        }
        List<String> projectName = Lists.newArrayList();
        for (File file : fileArray) {
            projectName.add(file.getName());
        }
        return projectName;
    }


    @Override
    public Boolean addProject(String projectName) {
        File file = new File(projectName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return true;
    }

    @Override
    public List<String> fileList(String projectName) {
        File file = new File(projectName);
        if (!file.exists()) {
            this.addProject(projectName);
            return Lists.newArrayList();
        }
        File[] fileArray = file.listFiles(curFile -> curFile.isFile());
        if (null == fileArray || fileArray.length < 1) {
            return Lists.newArrayList();
        }
        List<String> fileNameList = Lists.newArrayList();
        for (File curFile : fileArray) {
            fileNameList.add(curFile.getName());
        }
        return fileNameList;
    }

    @Override
    public List<TemplateConfigDO> generateTemplateConfig(List<String> templateNameList, String basePackage) {
        if (CollectionUtils.isEmpty(templateNameList)) {
            return Lists.newArrayList();
        }
        List<TemplateConfigDO> templateConfigDOList = Lists.newArrayList();
        String basePackagePath = basePackage.replace(".", "/");
        for (String templateName : templateNameList) {
            templateConfigDOList.add(new TemplateConfigDO(templateName, basePackagePath));
        }
        return templateConfigDOList;
    }
}
