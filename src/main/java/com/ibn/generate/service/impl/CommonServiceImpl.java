package com.ibn.generate.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ibn.generate.common.DynamicDataSource;
import com.ibn.generate.cons.DataSourceCons;
import com.ibn.generate.domain.GenrateConfigDTO;
import com.ibn.generate.domain.TemplateConfigDTO;
import com.ibn.generate.service.CommonService;
import com.ibn.generate.util.FileUtils;
import com.ibn.generate.util.StringUtils;
import com.ibn.generate.vo.TemplateCofnigVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    private Map<Long, GenrateConfigDTO> configMap = Maps.newConcurrentMap();

    @Override
    public Long generateId() {
        return atomicLong.addAndGet(1L);
    }

    @Override
    public Boolean saveDataSource(Long id, DataSource dataSource) {
        GenrateConfigDTO genrateConfigDTO = configMap.get(id);
        if (null == genrateConfigDTO) {
            genrateConfigDTO = new GenrateConfigDTO();
        }
        genrateConfigDTO.setDataSource(dataSource);
        configMap.put(id, genrateConfigDTO);
        return true;
    }

    @Override
    public DataSource queryDataSource(Long id) {
        GenrateConfigDTO genrateConfigDTO = configMap.get(id);
        if (null == genrateConfigDTO) {
            return null;
        }
        return genrateConfigDTO.getDataSource();
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
        GenrateConfigDTO genrateConfigDTO = configMap.get(id);
        if (null == genrateConfigDTO) {
            return false;
        }
        genrateConfigDTO.setSchemaName(schemaName);
        genrateConfigDTO.setTableNameList(tableNameList);
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
    public Boolean setTemplateConfig(TemplateCofnigVO templateCofnigVO) {
        Long id = templateCofnigVO.getId();
        GenrateConfigDTO genrateConfigDTO = configMap.get(id);
        if (null == genrateConfigDTO) {
            return false;
        }
        TemplateConfigDTO templateConfigDTO = new TemplateConfigDTO();
        BeanUtils.copyProperties(templateCofnigVO, templateConfigDTO);
        genrateConfigDTO.setTemplateConfigDTO(templateConfigDTO);
        return true;
    }

    @Override
    public Set<String> queryParamName(Long id) {
        if (null == configMap) {
            return Sets.newHashSet();
        }
        GenrateConfigDTO genrateConfigDTO = configMap.get(id);
        if (null == genrateConfigDTO) {
            return Sets.newHashSet();
        }
        String projectName = genrateConfigDTO.getTemplateConfigDTO().getProjectName();
        File file = new File(projectName);
        if (!file.exists()) {
            this.addProject(projectName);
            return Sets.newHashSet();
        }
        Set<String> paramList = Sets.newHashSet();
        List<File> fileList = Lists.newArrayList();
        FileUtils.getFile(file, fileList);
        if (!CollectionUtils.isEmpty(fileList)) {
            for (File curFile : fileList) {
                try {
                    byte[] bytes = Files.readAllBytes(Paths.get(curFile.getAbsolutePath()));
                    String content = new String(bytes);
                    List<String> regexpList = StringUtils.regexp(content, "\\$\\{custom\\.(.{1,})\\}");
                    if (CollectionUtils.isEmpty(regexpList)) {
                        continue;
                    }
                    paramList.addAll(regexpList);
                } catch (IOException e) {
                    String msg = String.format("读取文件信息失败：%s", curFile.getAbsolutePath());
                    logger.error(msg, e);
                }
            }
        }
        return paramList;
    }

    @Override
    public void setConfig(Long id, Map<String, String> params) {
        GenrateConfigDTO genrateConfigDTO = configMap.get(id);
        if (null == genrateConfigDTO) {
            return;
        }
        genrateConfigDTO.setParams(params);
    }

    @Override
    public GenrateConfigDTO queryConfig(Long id) {
        return configMap.get(id);
    }
}
