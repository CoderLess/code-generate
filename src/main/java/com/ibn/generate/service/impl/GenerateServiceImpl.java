package com.ibn.generate.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ibn.generate.constant.FileConsts;
import com.ibn.generate.constant.ParamConsts;
import com.ibn.generate.dao.MysqlQueryDao;
import com.ibn.generate.domain.GenrateConfigDTO;
import com.ibn.generate.entity.ColumnDO;
import com.ibn.generate.entity.TableDO;
import com.ibn.generate.service.CommonService;
import com.ibn.generate.service.GenerateService;
import com.ibn.generate.service.TemplateService;
import com.ibn.generate.util.FileUtils;
import com.ibn.generate.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.service.impl
 * @author： RenBin
 * @createTime：2020/12/01 20:42
 */
@Service("generateService")
public class GenerateServiceImpl implements GenerateService {
    @Autowired
    private TemplateService templateService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private MysqlQueryDao mysqlQueryDao;

    @Override
    public Boolean generate(Long id) {
        GenrateConfigDTO genrateConfigDTO = commonService.queryConfig(id);
        if (null == genrateConfigDTO) {
            return false;
        }
        String projectName = genrateConfigDTO.getTemplateConfigDTO().getProjectName();
        // 生成目标文件目录
        String destProjectName = String.format("%s-%d",projectName.replace(FileConsts.TEMPLATE,""),System.currentTimeMillis());

        // 处理与表名相关的参数
        Map<String, Object> param = this.contractAllParam(genrateConfigDTO);
        // 遍历所有模板
        List<String> fileList = Lists.newArrayList();
        FileUtils.getFilePath(projectName,"", fileList);
        if (CollectionUtils.isEmpty(fileList)) {
            return false;
        }
        for (String curFile : fileList) {
            String destFilePath = curFile.replaceFirst(projectName, destProjectName);
            // 遍历所有表
            this.generateFile(curFile,destFilePath,genrateConfigDTO.getTableNameList(),
                    genrateConfigDTO,param);
        }

        return true;
    }


    /**
     * @description: 遍历所有表并生成文件
     * @author：RenBin
     * @createTime：2020/12/7 20:18
     */
    private Boolean generateFile(String templatePath,String destPath,List<String> tableNameList ,
                                 GenrateConfigDTO genrateConfigDTO, Map<String, Object> param) {
        if (templatePath.endsWith(FileConsts.ONLY)) {
            destPath = destPath.replace(FileConsts.ONLY, "");
            templateService.writer(templatePath, destPath, param);
            return true;
        }
        TableDO tableDO;
        for (String tableName : tableNameList) {
            tableDO=new TableDO();
            tableDO.setTableSchema(genrateConfigDTO.getSchemaName());
            tableDO.setTableName(tableName);
            String className = this.curTableNameParam(param, tableName);
            if (destPath.contains(FileConsts.CLASS_NAME)) {
                destPath = destPath.replace(FileConsts.CLASS_NAME, className);
            }
            if (destPath.contains(FileConsts.CLASS_NAME)) {
                destPath = destPath.replace(FileConsts.CLASS_NAME, className);
            }
            TableDO tableInfoDO = mysqlQueryDao.queryTableInfo(tableDO);
            this.tableInfoParam(param, tableInfoDO);
            List<ColumnDO> columnDOList = mysqlQueryDao.queryColumnInfo(tableDO);
            this.columnInfoParam(param, columnDOList);
            templateService.writer(templatePath, destPath, param);
        }
        return true;
    }
    /**
     * @description: 参数信息
     * @author：RenBin
     * @createTime：2020/12/7 21:31
     */
    private void columnInfoParam(Map<String, Object> param, List<ColumnDO> columnDOList) {
        String tempColumnName;
        for (ColumnDO columnDO : columnDOList) {
            tempColumnName = StringUtils.underscoreToUpcamelCase(columnDO.getColumnName());
            columnDO.setParamName(StringUtils.lowCaseFirstLatter(tempColumnName));
            switch (columnDO.getDataType()) {
                case "bigint":
                    columnDO.setType("Long");
                    break;
                case "varchar":
                    columnDO.setType("String");
                    break;
                case "int":
                    columnDO.setType("Integer");
                    break;
                case "tinyint":
                    columnDO.setType("Integer");
                    break;
                case "decimal":
                    columnDO.setType("BigDecimal");
                    break;
                default:
                    columnDO.setType("String");
            }
        }
        param.put(ParamConsts.COLUMNS, columnDOList);
    }
    /**
     * @description: 生成与当前表相关的参数
     * @author：RenBin
     * @createTime：2020/12/7 21:26
     */
    private void tableInfoParam(Map<String, Object> param, TableDO tableInfoDO) {
        param.put(ParamConsts.TABLE, tableInfoDO);
    }

    /**
     * @description: 生成与当前表名相关的参数
     * @author：RenBin
     * @createTime：2020/12/7 20:57
     */
    private String curTableNameParam(Map<String, Object> param, String tableName) {
        String tempName;
        String className;
        tempName = StringUtils.underscoreToUpcamelCase(tableName);
        className=StringUtils.upperCaseFirstLatter(tempName);
        param.put(ParamConsts.CLASS_NAME, className);
        param.put(ParamConsts.PARAM_NAME, StringUtils.lowCaseFirstLatter(tempName));
        param.put(ParamConsts.TABLE_NAME, tableName);
        return className;
    }

    /**
     * @description: 构造所有参数
     * @author：RenBin
     * @createTime：2020/12/7 20:17
     */
    private Map<String, Object> contractAllParam(GenrateConfigDTO genrateConfigDTO) {
        // 存放所有的参数
        Map<String, Object> param = Maps.newHashMap();
        // 与表名相关的参数
        Map<String, Object> tableNameParam = this.dealTableNameParam(genrateConfigDTO);
        param.putAll(tableNameParam);
        // 添加用户自定义参数
        this.dealConsumerParam(param, genrateConfigDTO);
        return param;
    }

    /**
     * @description: 添加用户自定义的参数
     * @author：RenBin
     * @createTime：2020/12/7 20:20
     */
    private void dealConsumerParam(Map<String, Object> param, GenrateConfigDTO genrateConfigDTO) {
        Map<String, String> params = genrateConfigDTO.getParams();
        if (params != null && genrateConfigDTO.getParams().size() > 0) {
            Map<String, Object> consumerParam = Maps.newHashMap();
            Set<Map.Entry<String, String>> entries = genrateConfigDTO.getParams().entrySet();
            for (Map.Entry<String, String> entry : entries) {
                consumerParam.put(entry.getKey(), entry.getValue());
            }
            param.put(ParamConsts.CONSUMER,consumerParam);
        }
    }

    /**
     * @description: 构造生成文件需要的表名参数
     * @author：RenBin
     * @createTime：2020/12/7 20:11
     */
    private Map<String, Object> dealTableNameParam(GenrateConfigDTO genrateConfigDTO) {
        Map<String, Object> param = Maps.newHashMap();
        List<String> tableNameList = genrateConfigDTO.getTableNameList();
        param.put(ParamConsts.TABLE_NAME_LIST, tableNameList);
        if (!CollectionUtils.isEmpty(tableNameList)) {
            // 类名
            List<String> classNameList = Lists.newArrayList();
            // 变量名
            List<String> paramNameList = Lists.newArrayList();
            for (String tableName : tableNameList) {
                tableName = StringUtils.underscoreToUpcamelCase(tableName);
                classNameList.add(StringUtils.upperCaseFirstLatter(tableName));
                paramNameList.add(StringUtils.lowCaseFirstLatter(tableName));
            }
            param.put(ParamConsts.CLASS_NAME_LIST, classNameList);
            param.put(ParamConsts.PARAM_NAME_LIST, paramNameList);
        }
        return param;
    }
}
