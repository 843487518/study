package com.zxk.study.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhouxx
 * @create 2022-04-14 18:17
 */
public class MyBatisGeneratorUtils {

    private final static Pattern linePattern = Pattern.compile("_(\\w)");
    private final static Pattern humpPattern = Pattern.compile("[A-Z]");
    static String author = "zhouxx";
    static Map<String,String> dateMap=new HashMap<>();
//    static String MySQLUrl = "jdbc:mysql://localhost:3306/chat?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=UTF-8&allowMultiQueries=true";
    static String MySQLUrl = "jdbc:mysql://localhost:3306/jdemo?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=UTF-8&allowMultiQueries=true";

    static String username = "root";
    static String password = "root";
    static String SingleTableStructSQL = "    select table_name tableName,column_name columnName,data_type dataType,column_comment columnComment,column_key columnKey,extra ,\n" +
            "    is_nullable as isNullable,column_type as columnType\n" +
            "    from information_schema.columns\n" +
            "    where table_name = ? and table_schema = ? \n" +
            "    order by ordinal_position";
    static String AllTableSQL = "select TABLE_SCHEMA dbName,TABLE_NAME tableName,TABLE_COMMENT tableComment from information_schema.tables where TABLE_SCHEMA=?";
    static String voInputPackage="com.example.module.vo.input";
    static String voOutputPackage="com.example.module.vo.output";
    static String boPackage="com.example.module.bo";
    static String dtoPackage="com.example.module.dto";
    static String mapperPackage="com.example.mapper";
    static String servicePackage="com.example.service";
    static String serviceImplPackage="com.example.service.impl";
    static String controllerPackage="com.example.controller";

    public static void main(String[] args) throws Exception {


        System.out.println(MySQLTableStruct.class.getName());

        // request表单或者body的请求日期类型参数是字符串类型 "dataType":"timestamp" DATE TIME DATETIME TIMESTAMP
        dateMap.put("TIMESTAMP","TIMESTAMP");
        dateMap.put("DATE","DATE");
        dateMap.put("TIME","TIME");
        dateMap.put("DATETIME","DATETIME");


        // 数据库 -- 获取库中全部表，循环调用
        String dataBase = "jdemo";
        // 表
        String tableName = "tmp_sys_user";
        String tableComment="系统用户表";
        boolean forOneTable=false;
        //boolean forOneTable=true;
        if (forOneTable){
            // 单表生成
            table(dataBase,tableName,tableComment);
        }else {
            // 获取数据库下的表名，全部生成
            List<MySQLTableName> tables = getMultiInstance(MySQLTableName.class, AllTableSQL, dataBase);
            System.out.println(tables.size());
            System.out.println(tables.toString());
            for(MySQLTableName temp:tables){
                table(temp.getDbName(),temp.getTableName(),temp.getTableComment());
            }
        }

    }

    // 单表生成模板
    public static void table(String dataBase, String tableName,String tableComment)throws Exception{
        // 类名
        String tempName=upperFirstChar(lineToHump(dropTablePrefix(tableName)));
        String voInputName=tempName+"Input";
        String voOutputName=tempName+"Output";
        String BoName=tempName+"BO";
        String tempDtoName=upperFirstChar(lineToHump(tableName));
        String DtoName=tempDtoName+"DTO";
        String ControllerName=tempName+"Controller";
        String ServiceName=tempName+"Service";
        String ServiceImplName=tempName+"ServiceImpl";
        String MapperName=tempDtoName+"Mapper";

        //类文件名
        String voInputNameFile=voInputName+".java";
        String voOutputNameFile=voOutputName+".java";
        String BoNameFile=BoName+".java";
        String DtoNameFile=DtoName+".java";
        String ControllerFile=ControllerName+".java";
        String ServiceNameFile=ServiceName+".java";
        String ServiceImplNameFile=ServiceImplName+".java";
        String MapperNameFile=MapperName+".java";
        String MapperNameXml=MapperName+".xml";

        String voInputPath="./generator2/"+voInputPackage.replaceAll("\\.","/");
        String voOutputPath="./generator2/"+voOutputPackage.replaceAll("\\.","/");
        String boPath="./generator2/"+boPackage.replaceAll("\\.","/");
        String dtoPath="./generator2/"+dtoPackage.replaceAll("\\.","/");
        String controllerPath="./generator2/"+controllerPackage.replaceAll("\\.","/");
        String servicePath="./generator2/"+servicePackage.replaceAll("\\.","/");
        String serviceImplPath="./generator2/"+serviceImplPackage.replaceAll("\\.","/");
        String mapperPath="./generator2/"+mapperPackage.replaceAll("\\.","/");

        System.out.printf("tableName={%s},BoName={%s} ,DtoName={%s},MapperName={%s}\n",tableName,BoName,DtoName,MapperName);
        System.out.printf("boPath={%s},dtoPath={%s} ,mapperPath={%s},MapperName={%s}\n",boPath,dtoPath,mapperPath,MapperName);

        // 创建目录
        FileUtils.makedir(voInputPath);
        FileUtils.makedir(voOutputPath);
        FileUtils.makedir(boPath);
        FileUtils.makedir(dtoPath);
        FileUtils.makedir(controllerPath);
        FileUtils.makedir(servicePath);
        FileUtils.makedir(serviceImplPath);
        FileUtils.makedir(mapperPath);
        // 创建文件
        FileUtils.createFile(voInputPath+"/"+voInputNameFile);
        FileUtils.createFile(voOutputPath+"/"+voOutputNameFile);
        FileUtils.createFile(boPath+"/"+BoNameFile);
        FileUtils.createFile(dtoPath+"/"+DtoNameFile);
        FileUtils.createFile(controllerPath+"/"+ControllerFile);
        FileUtils.createFile(servicePath+"/"+ServiceNameFile);
        FileUtils.createFile(serviceImplPath+"/"+ServiceImplNameFile);
        FileUtils.createFile(mapperPath+"/"+MapperNameFile);
        FileUtils.createFile(mapperPath+"/mXml/"+MapperNameXml);

        // 获取表结构
        List<MySQLTableStruct> tableStructs = getMultiInstance(MySQLTableStruct.class, SingleTableStructSQL, tableName, dataBase);
        System.out.println(tableStructs.size());
        System.out.println(tableStructs.toString());

        // 写文件
        // 创建 bean 文件
        FileUtils.WriteFile(voOutputPath+"/"+voOutputNameFile,getBeanString(voOutputName,voOutputPackage,tableStructs,tableName,tableComment));
        FileUtils.WriteFile(boPath+"/"+BoNameFile,getBeanString(BoName,boPackage,tableStructs,tableName,tableComment));
        FileUtils.WriteFile(dtoPath+"/"+DtoNameFile,getBeanString(DtoName,dtoPackage,tableStructs,tableName,tableComment));
        // 创建 controller文件
        FileUtils.WriteFile(controllerPath+"/"+ControllerFile,getControllerString(voInputName,voInputPackage,ServiceName,servicePackage,ControllerName,controllerPackage,DtoName,dtoPackage,tableStructs,tableName,tableComment));
        // 创建 service文件
        FileUtils.WriteFile(servicePath+"/"+ServiceNameFile,getServiceString(ServiceName,servicePackage,DtoName,dtoPackage,tableStructs,tableName,tableComment));
        FileUtils.WriteFile(serviceImplPath+"/"+ServiceImplNameFile,getServiceImplString(ServiceName,servicePackage,MapperName,mapperPackage,ServiceImplName,serviceImplPackage,DtoName,dtoPackage,tableStructs,tableName,tableComment));
        // 创建 mapper文件
        FileUtils.WriteFile(mapperPath+"/"+MapperNameFile,getMapperString(MapperName,mapperPackage,DtoName,dtoPackage,tableStructs,tableName,tableComment));
        // 创建 mapper.xml文件
        FileUtils.WriteFile(mapperPath+"/"+MapperNameXml,getMapperXmlString(MapperName,mapperPackage,DtoName,dtoPackage,tableStructs,tableName,tableComment));
    }

    // bean
    public static String getBeanString(String classname,String packageString,List<MySQLTableStruct> list, String tableName,String tableComment){
        StringBuilder stringBuilder=new StringBuilder("package").append("\t").append(packageString).append(";\r\n");
        stringBuilder.append("\r\n").append("\r\n");
        stringBuilder.append("import lombok.AllArgsConstructor;").append("\r\n");
        stringBuilder.append("import lombok.Data;").append("\r\n");
        stringBuilder.append("import lombok.NoArgsConstructor;").append("\r\n");
        stringBuilder.append("import java.io.Serializable;").append("\r\n");
        stringBuilder.append("\r\n").append("\r\n");

        /**
         * @author zhouxx
         * @create 2022-04-14 18:17
         */
        stringBuilder.append("/**").append("\r\n");
        stringBuilder.append("* Table:").append(tableName).append("  ").append(tableComment).append("\r\n");
        stringBuilder.append("* @author ").append(author).append("\r\n");
        stringBuilder.append("* @create\t").append(getDefaultTime()).append("\r\n");
        stringBuilder.append("*/").append("\r\n");

        stringBuilder.append("@Data").append("\r\n");
        stringBuilder.append("@AllArgsConstructor").append("\r\n");
        stringBuilder.append("@NoArgsConstructor").append("\r\n");
        stringBuilder.append("public class ").append(classname).append(" implements Serializable {").append("\r\n").append("\r\n");
        stringBuilder.append("\t\t private static final long serialVersionUID = 1L;").append("\r\n").append("\r\n");

        // private Long id;
        for (MySQLTableStruct temp: list) {
            // {"columnName":"list_id","dataType":"int","columnComment":"聊天列表主键","columnKey":"PRI","extra":"auto_increment","isNullable":"NO","columnType":"int(11)"}
            // {"columnName":"link_id","dataType":"varchar","columnComment":"聊天主表id","columnKey":"MUL","extra":"","isNullable":"NO","columnType":"varchar(255)"}
            // {"tableName":"tmp_sys_user","columnName":"update_time","dataType":"timestamp","columnComment":"最后修改时间","columnKey":"","extra":"on update CURRENT_TIMESTAMP","isNullable":"NO","columnType":"timestamp"}
            // 注释
            stringBuilder.append("\t\t /**").append(temp.columnComment).append("\t").append(temp.getColumnType()).append("\t").append(temp.getColumnKey()).append("*/").append("\r\n");
            // 字段
            // "dataType":"timestamp" DATE TIME DATETIME TIMESTAMP
            if (classname.endsWith("Input") &&dateMap.containsKey(temp.getDataType().toUpperCase())  ){
                // request表单或者body的请求日期类型参数是字符串类型 "dataType":"timestamp" DATE TIME DATETIME TIMESTAMP
                stringBuilder.append("\t\t private \t").append(MySQLType2JavaType.getKey("VARCHAR")).append("\t").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append(";\r\n");
            }else {
                stringBuilder.append("\t\t private \t").append(MySQLType2JavaType.getKey(temp.getDataType())).append("\t").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append(";\r\n");
            }
        }

        stringBuilder.append("\r\n").append("}").append("\r\n");
        return stringBuilder.toString();
    }
    // controller
    public static String getControllerString(String voInputName,String voInputPackage,String ServiceName,String servicePackage,String classname,String packageString,String DtoName,String dtoPackage,List<MySQLTableStruct> list, String tableName,String tableComment) {
        StringBuilder stringBuilder = new StringBuilder("package").append("\t").append(packageString).append(";\r\n");
        stringBuilder.append("\r\n").append("\r\n");

        stringBuilder.append("import ").append(voInputPackage).append(".").append(voInputName).append(";\r\n");
        stringBuilder.append("import ").append(servicePackage).append(".").append(ServiceName).append(";\r\n");
        stringBuilder.append("import ").append(dtoPackage).append(".").append(DtoName).append(";\r\n");
        stringBuilder.append("import com.zxx.study.web.util.PassToken;").append("\r\n");
        stringBuilder.append("import com.zxx.study.web.module.ApiResult;").append("\r\n");
        stringBuilder.append("import com.zxx.study.web.util.DateUtils;").append("\r\n");
        stringBuilder.append("import com.zxx.study.web.common.commenum.BaseResultError;").append("\r\n");
        stringBuilder.append("import java.util.List;").append("\r\n");
        stringBuilder.append("import lombok.extern.slf4j.Slf4j;").append("\r\n");
        stringBuilder.append("import org.springframework.beans.BeanUtils;").append("\r\n");
        stringBuilder.append("import org.springframework.beans.factory.annotation.Autowired;").append("\r\n");
        stringBuilder.append("import org.springframework.validation.annotation.Validated;").append("\r\n");
        stringBuilder.append("import org.springframework.web.bind.annotation.*;").append("\r\n");

        stringBuilder.append("\r\n").append("\r\n");
        stringBuilder.append("/**").append("\r\n");
        stringBuilder.append("* Table:").append(tableName).append("  ").append(tableComment).append("\r\n");
        stringBuilder.append("* @author ").append(author).append("\r\n");
        stringBuilder.append("* @create\t").append(getDefaultTime()).append("\r\n");
        stringBuilder.append("*/").append("\r\n");
        stringBuilder.append("@RestController").append("\r\n");
        stringBuilder.append("@Slf4j").append("\r\n");
        stringBuilder.append("@Validated").append("\r\n");
        stringBuilder.append("@RequestMapping(\"/api/v1/").append(lowerFirstChar(classname.replace("Controller",""))).append("\")").append("\r\n");
        stringBuilder.append("public class ").append(classname).append(" {").append("\r\n").append("\r\n");

        stringBuilder.append("\t\t @Autowired").append("\r\n");
        stringBuilder.append("\t\t ").append(ServiceName).append(" ").append(lowerFirstChar(ServiceName)).append(";\r\n").append("\r\n");

        stringBuilder.append("\t\t @GetMapping(\"/detail/{id}\")").append("\r\n");
        stringBuilder.append("\t\t public ApiResult getDetail(@PathVariable(\"id\") long id){\r\n");
        stringBuilder.append("\t\t        ").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append("=new ").append(DtoName).append("();\r\n");
        stringBuilder.append("\t\t        ").append(lowerFirstChar(DtoName)).append(".setId(id); ").append("\r\n");
        stringBuilder.append("\t\t        return ApiResult.success(").append(lowerFirstChar(ServiceName)).append(".query(").append(lowerFirstChar(DtoName)).append("));\r\n");
        stringBuilder.append("\t\t }").append("\r\n");

        stringBuilder.append("\t\t @PostMapping(\"/list\")").append("\r\n");
        stringBuilder.append("\t\t public ApiResult getList(@RequestBody ").append(voInputName).append(" ").append(lowerFirstChar(voInputName)).append("){\r\n");
        stringBuilder.append("\t\t        ").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append("=new ").append(DtoName).append("();\r\n");
        stringBuilder.append("\t\t        BeanUtils.copyProperties(").append(lowerFirstChar(voInputName)).append(", ").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t        //TODO 如果有日期类型，记得要转换一下！！！").append("\r\n");
        stringBuilder.append("\t\t        //").append(lowerFirstChar(DtoName)).append(".setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));").append("\r\n");
        stringBuilder.append("\t\t        return ApiResult.success(").append(lowerFirstChar(ServiceName)).append(".queryList(").append(lowerFirstChar(DtoName)).append("));\r\n");
        stringBuilder.append("\t\t }").append("\r\n");

        stringBuilder.append("\t\t @PostMapping(\"/add\")").append("\r\n");
        stringBuilder.append("\t\t public ApiResult add(@RequestBody ").append(voInputName).append(" ").append(lowerFirstChar(voInputName)).append("){\r\n");
        stringBuilder.append("\t\t        ").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append("=new ").append(DtoName).append("();\r\n");
        stringBuilder.append("\t\t        BeanUtils.copyProperties(").append(lowerFirstChar(voInputName)).append(", ").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t        //TODO 如果有日期类型，记得要转换一下！！！").append("\r\n");
        stringBuilder.append("\t\t        //").append(lowerFirstChar(DtoName)).append(".setCreateTime(DateUtils.getDateFromFormat(input.getCreateTime()));").append("\r\n");
        stringBuilder.append("\t\t        int cnt= ").append(lowerFirstChar(ServiceName)).append(".add(").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t        if(cnt==1){").append("\r\n");
        stringBuilder.append("\t\t            return ApiResult.success(cnt);").append("\r\n");
        stringBuilder.append("\t\t        }").append("\r\n");
        stringBuilder.append("\t\t        return ApiResult.fail(BaseResultError.API_DO_FAIL);").append("\r\n");
        stringBuilder.append("\t\t }").append("\r\n");

        stringBuilder.append("\t\t @PostMapping(\"/modify\")").append("\r\n");
        stringBuilder.append("\t\t public ApiResult modify(@RequestBody ").append(voInputName).append(" ").append(lowerFirstChar(voInputName)).append("){\r\n");
        stringBuilder.append("\t\t        ").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append("=new ").append(DtoName).append("();\r\n");
        stringBuilder.append("\t\t        BeanUtils.copyProperties(").append(lowerFirstChar(voInputName)).append(", ").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t        //TODO 如果有日期类型，记得要转换一下！！！").append("\r\n");
        stringBuilder.append("\t\t        ").append(lowerFirstChar(DtoName)).append(".setUpdateTime(DateUtils.getDateFromFormat(input.getUpdateTime()));").append("\r\n");
        stringBuilder.append("\t\t        int cnt= ").append(lowerFirstChar(ServiceName)).append(".modify(").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t        if(cnt==1){").append("\r\n");
        stringBuilder.append("\t\t            return ApiResult.success(cnt);").append("\r\n");
        stringBuilder.append("\t\t        }").append("\r\n");
        stringBuilder.append("\t\t        return ApiResult.fail(BaseResultError.API_DO_FAIL);").append("\r\n");
        stringBuilder.append("\t\t }").append("\r\n");

        stringBuilder.append("\t\t @GetMapping(\"/delete/{id}\")").append("\r\n");
        stringBuilder.append("\t\t public ApiResult delete(@PathVariable(\"id\") long id){\r\n");
        stringBuilder.append("\t\t        ").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append("=new ").append(DtoName).append("();\r\n");
        stringBuilder.append("\t\t        ").append(lowerFirstChar(DtoName)).append(".setId(id); ").append("\r\n");
        stringBuilder.append("\t\t        int cnt= ").append(lowerFirstChar(ServiceName)).append(".delete(").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t        if(cnt==1){").append("\r\n");
        stringBuilder.append("\t\t            return ApiResult.success(cnt);").append("\r\n");
        stringBuilder.append("\t\t        }").append("\r\n");
        stringBuilder.append("\t\t        return ApiResult.fail(BaseResultError.API_DO_FAIL);").append("\r\n");
        stringBuilder.append("\t\t }").append("\r\n");



        stringBuilder.append("\r\n").append("}").append("\r\n");
        return stringBuilder.toString();
    }
    // service 接口
    public static String getServiceString(String classname,String packageString,String DtoName,String dtoPackage,List<MySQLTableStruct> list, String tableName,String tableComment) {
        StringBuilder stringBuilder = new StringBuilder("package").append("\t").append(packageString).append(";\r\n");
        stringBuilder.append("\r\n").append("\r\n");


        stringBuilder.append("import ").append(dtoPackage).append(".").append(DtoName).append(";\r\n");
        stringBuilder.append("import java.util.List;").append("\r\n");

        stringBuilder.append("\r\n").append("\r\n");
        stringBuilder.append("/**").append("\r\n");
        stringBuilder.append("* Table:").append(tableName).append("  ").append(tableComment).append("\r\n");
        stringBuilder.append("* @author ").append(author).append("\r\n");
        stringBuilder.append("* @create\t").append(getDefaultTime()).append("\r\n");
        stringBuilder.append("*/").append("\r\n");
        stringBuilder.append("public interface ").append(classname).append(" {").append("\r\n").append("\r\n");


        stringBuilder.append("\t\t public ").append(DtoName).append(" query(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t public List<").append(DtoName).append(" > queryList(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t public int add(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t public int modify(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t public int delete(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append(");\r\n");


        stringBuilder.append("\r\n").append("}").append("\r\n");
        return stringBuilder.toString();
    }
    public static String getServiceImplString(String ServiceName,String servicePackage,String MapperName,String mapperPackage,String classname,String packageString,String DtoName,String dtoPackage,List<MySQLTableStruct> list, String tableName,String tableComment) {
        StringBuilder stringBuilder = new StringBuilder("package").append("\t").append(packageString).append(";\r\n");
        stringBuilder.append("\r\n").append("\r\n");

        stringBuilder.append("import ").append(servicePackage).append(".").append(ServiceName).append(";\r\n");
        stringBuilder.append("import ").append(mapperPackage).append(".").append(MapperName).append(";\r\n");
        stringBuilder.append("import ").append(dtoPackage).append(".").append(DtoName).append(";\r\n");
        stringBuilder.append("import java.util.List;").append("\r\n");
        stringBuilder.append("import org.springframework.beans.factory.annotation.Autowired;").append("\r\n");
        stringBuilder.append("import org.springframework.stereotype.Service;").append("\r\n");

        stringBuilder.append("\r\n").append("\r\n");
        stringBuilder.append("/**").append("\r\n");
        stringBuilder.append("* Table:").append(tableName).append("  ").append(tableComment).append("\r\n");
        stringBuilder.append("* @author ").append(author).append("\r\n");
        stringBuilder.append("* @create\t").append(getDefaultTime()).append("\r\n");
        stringBuilder.append("*/").append("\r\n");
        stringBuilder.append("@Service").append("\r\n");
        stringBuilder.append("public class ").append(classname).append(" implements ").append(ServiceName).append(" {").append("\r\n").append("\r\n");

        stringBuilder.append("\t\t @Autowired").append("\r\n");
        stringBuilder.append("\t\t ").append(MapperName).append(" ").append(lowerFirstChar(MapperName)).append(";\r\n").append("\r\n");

        stringBuilder.append("\t\t public ").append(DtoName).append(" query(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append("){\r\n");
        stringBuilder.append("\t\t        return ").append(lowerFirstChar(MapperName)).append(".selectOne(").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t }").append("\r\n");

        stringBuilder.append("\t\t public List<").append(DtoName).append(" > queryList(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append("){\r\n");
        stringBuilder.append("\t\t        return ").append(lowerFirstChar(MapperName)).append(".selectAll(").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t }").append("\r\n");

        stringBuilder.append("\t\t public int add(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append("){\r\n");
        stringBuilder.append("\t\t        return ").append(lowerFirstChar(MapperName)).append(".insert(").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t }").append("\r\n");

        stringBuilder.append("\t\t public int modify(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append("){\r\n");
        stringBuilder.append("\t\t        return ").append(lowerFirstChar(MapperName)).append(".update(").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t }").append("\r\n");

        stringBuilder.append("\t\t public int delete(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append("){\r\n");
        stringBuilder.append("\t\t        return ").append(lowerFirstChar(MapperName)).append(".delete(").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t }").append("\r\n");

        stringBuilder.append("\r\n").append("}").append("\r\n");
        return stringBuilder.toString();
    }
    // dao 接口
    public static String getMapperString(String classname,String packageString,String DtoName,String dtoPackage,List<MySQLTableStruct> list, String tableName,String tableComment) {
        StringBuilder stringBuilder = new StringBuilder("package").append("\t").append(packageString).append(";\r\n");
        stringBuilder.append("\r\n").append("\r\n");


        stringBuilder.append("import ").append(dtoPackage).append(".").append(DtoName).append(";\r\n");
        stringBuilder.append("import org.apache.ibatis.annotations.Mapper;").append("\r\n");
        stringBuilder.append("import org.springframework.stereotype.Repository;").append("\r\n");
        stringBuilder.append("import java.util.List;").append("\r\n");

        stringBuilder.append("\r\n").append("\r\n");
        stringBuilder.append("/**").append("\r\n");
        stringBuilder.append("* Table:").append(tableName).append("  ").append(tableComment).append("\r\n");
        stringBuilder.append("* @author ").append(author).append("\r\n");
        stringBuilder.append("* @create\t").append(getDefaultTime()).append("\r\n");
        stringBuilder.append("*/").append("\r\n");
        stringBuilder.append("@Mapper").append("\r\n");
        stringBuilder.append("@Repository").append("\r\n");
        stringBuilder.append("public interface ").append(classname).append(" {").append("\r\n").append("\r\n");


        stringBuilder.append("\t\t public ").append(DtoName).append(" selectOne(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t public List<").append(DtoName).append(" > selectAll(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t public int insert(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t public int update(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append(");\r\n");
        stringBuilder.append("\t\t public int delete(").append(DtoName).append(" ").append(lowerFirstChar(DtoName)).append(");\r\n");


        stringBuilder.append("\r\n").append("}").append("\r\n");
        return stringBuilder.toString();
    }
    // 创建 mapper.xml 文件
    public static String getMapperXmlString(String classname,String packageString,String DtoName,String dtoPackage,List<MySQLTableStruct> list,String tableName,String tableComment) {
        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append("\r\n");
        stringBuilder.append("<!DOCTYPE mapper \r\n")
                .append("       PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \n ")
                .append("     \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").append("\r\n");

        stringBuilder.append("<mapper namespace=\"").append(packageString).append(".").append(classname).append("\">").append("\n\r\n");

        // 单表查询，返回结果用的是resultType，没有用resultMap，因此要求select 查询语句的字段必须用别名，与DTOBean字段保持一致（表字段是下划线、DTOBean是驼峰）。
        // 单条查询
        stringBuilder.append(" <select id=\"selectOne\" parameterType=\"").append(dtoPackage).append(".").append(DtoName).append("\" resultType=\"").append(dtoPackage).append(".").append(DtoName).append("\">").append("\r\n");
        stringBuilder.append("            select ").append("\r\n");
        int i=1;
        for(MySQLTableStruct temp:list){
            stringBuilder.append("                          ").append(temp.getColumnName()).append(" AS ").append(lowerFirstChar(lineToHump(temp.getColumnName())));
            if(i<list.size()){
                stringBuilder.append(",");
            }
            stringBuilder.append("\r\n");
            i++;
        }
        stringBuilder.append("              from ").append(tableName).append("\r\n");
        stringBuilder.append("                        <where>").append("\r\n");
        for(MySQLTableStruct temp:list){
            stringBuilder.append("                          <if test=\"").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append(" != null\">").append("\r\n");
            stringBuilder.append("                              and ").append(temp.getColumnName()).append(" = #{").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append("}").append("\r\n");
            stringBuilder.append("                          </if>").append("\r\n");
        }
        stringBuilder.append("                        </where>").append("\r\n");
        stringBuilder.append("                        Limit 1").append("\r\n");
        stringBuilder.append(" </select>").append("\r\n\n");

        // 多条查询
        stringBuilder.append(" <select id=\"selectAll\" parameterType=\"").append(dtoPackage).append(".").append(DtoName).append("\" resultType=\"").append(dtoPackage).append(".").append(DtoName).append("\">").append("\r\n");
        stringBuilder.append("            select ").append("\r\n");
        i=1;
        for(MySQLTableStruct temp:list){
            stringBuilder.append("                          ").append(temp.getColumnName()).append(" AS ").append(lowerFirstChar(lineToHump(temp.getColumnName())));
            if(i<list.size()){
                stringBuilder.append(",");
            }
            stringBuilder.append("\r\n");
            i++;
        }
        stringBuilder.append("              from ").append(tableName).append("\r\n");
        stringBuilder.append("                        <where>").append("\r\n");
        for(MySQLTableStruct temp:list){
            stringBuilder.append("                          <if test=\"").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append(" != null\">").append("\r\n");
            stringBuilder.append("                              and ").append(temp.getColumnName()).append(" = #{").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append("}").append("\r\n");
            stringBuilder.append("                          </if>").append("\r\n");
        }
        stringBuilder.append("                        </where>").append("\r\n");
        stringBuilder.append(" </select>").append("\r\n\n");

        // 单条插入 insert
        // id 自增；create_time,update_time 数据库自行维护；所以这三个字段 插入和更新时不用带上。
        stringBuilder.append(" <insert id=\"insert\" parameterType=\"").append(dtoPackage).append(".").append(DtoName).append("\"   useGeneratedKeys=\"true\" keyProperty=\"id\" >").append("\r\n");
        stringBuilder.append("       insert into ").append(tableName).append(" (");
        i=1;
        for (MySQLTableStruct temp:list) {
            // {"tableName":"tmp_sys_user3","columnName":"id","dataType":"bigint","columnComment":"","columnKey":"PRI","extra":"auto_increment","isNullable":"NO","columnType":"bigint(11)"}
            // {"tableName":"tmp_sys_user3","columnName":"create_time","dataType":"timestamp","columnComment":"创建时间","columnKey":"","extra":"","isNullable":"NO","columnType":"timestamp"}
            // {"tableName":"tmp_sys_user3","columnName":"update_time","dataType":"timestamp","columnComment":"最后修改时间","columnKey":"","extra":"on update CURRENT_TIMESTAMP","isNullable":"NO","columnType":"timestamp"}
            if (!"auto_increment".equals(temp.getExtra())
                    && !"create_time".equals(temp.getColumnName())
                    && !"update_time".equals(temp.getColumnName())) {
                stringBuilder.append(temp.getColumnName());
                if (i < list.size()) {
                    stringBuilder.append(",");
                }
            }
            i++;
        }
        stringBuilder.append(")\r\n");
        stringBuilder.append("       values (") ;
        i=1;
        for (MySQLTableStruct temp:list){
            if (!"auto_increment".equals(temp.getExtra())
                    && !"create_time".equals(temp.getColumnName())
                    && !"update_time".equals(temp.getColumnName())) {
                stringBuilder.append("#{").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append("}");
                if (i < list.size()) {
                    stringBuilder.append(",");
                }
            }
            i++;
        }
        stringBuilder.append(")\r\n");
        stringBuilder.append(" </insert>").append("\r\n\n");

        // 更新
        stringBuilder.append(" <update id=\"update\" parameterType=\"").append(dtoPackage).append(".").append(DtoName).append("\">").append("\r\n");
        stringBuilder.append("       update ").append(tableName).append(" set ").append("\r\n");
        i=1;
        for (MySQLTableStruct temp:list) {
            if (!"auto_increment".equals(temp.getExtra())
                    && !"create_time".equals(temp.getColumnName())
                    && !"update_time".equals(temp.getColumnName())) {
                stringBuilder.append("              <if test=\"").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append(" != null\">").append(temp.getColumnName()).append("=#{").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append("}");
                if (i < list.size()) {
                    stringBuilder.append(",");
                }
                stringBuilder.append("</if>").append("\r\n");
            }

            i++;
        }
        stringBuilder.append("                              <!--update_time: 乐观锁，以第一个修改为准;且只能用主键id更新！-->").append("\r\n");
        stringBuilder.append("                  where id=#{id} and update_time = #{updateTime}").append("\r\n");
        // todo 暂不生成where
//        stringBuilder.append("                  <where>").append("\r\n");
//        for(MySQLTableStruct temp:list) {
//            if (!"update_time".equals(temp.getColumnName())) {
//                stringBuilder.append("                          <if test=\"").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append(" != null\">").append("\r\n");
//                stringBuilder.append("                              and ").append(temp.getColumnName()).append(" = #{").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append("}").append("\r\n");
//                stringBuilder.append("                          </if>").append("\r\n");
//            }
//        }
//        stringBuilder.append("                              <!--update_time: 乐观锁，以第一个修改为准-->").append("\r\n");
//        // update_time: 乐观锁，以第一个修改为准
//        stringBuilder.append("                              and update_time = #{updateTime}").append("\r\n");
//        stringBuilder.append("                   </where>").append("\r\n");
        stringBuilder.append(" </update>").append("\r\n\n");

        // 删除 逻辑删除
        stringBuilder.append(" <update id=\"delete\" parameterType=\"").append(dtoPackage).append(".").append(DtoName).append("\">").append("\r\n");
        stringBuilder.append("       update ").append(tableName).append(" set delete_flag=1 ").append("\r\n");
        stringBuilder.append("                              <!--update_time: 乐观锁，以第一个修改为准;且只能用主键id更新！-->").append("\r\n");
        stringBuilder.append("                  where id=#{id} ").append("\r\n");
        // todo 暂不生成where
//        stringBuilder.append("                  <where>").append("\r\n");
//        for(MySQLTableStruct temp:list){
//            stringBuilder.append("                          <if test=\"").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append(" != null\">").append("\r\n");
//            stringBuilder.append("                              and ").append(temp.getColumnName()).append(" = #{").append(lowerFirstChar(lineToHump(temp.getColumnName()))).append("}").append("\r\n");
//            stringBuilder.append("                          </if>").append("\r\n");
//        }
//        stringBuilder.append("                   </where>").append("\r\n");
        stringBuilder.append(" </update>").append("\r\n\n");

//            <update id="updateOnlineState">
//                update chat_user set is_online = #{isOnline} where username = #{username};
//    </update>

        stringBuilder.append("\r\n").append("</mapper>").append("\r\n");
        return stringBuilder.toString();
    }

    public static String getDefaultTime(){
        return getDate4String(new Date(),"yyyy-MM-dd HH:mm:ss");
    }
    public static String getDate4String(Date date,String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);

    }
    public static Connection getConnection() throws Exception {
        //加载mysql驱动
//        Class.forName("com.mysql.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(MySQLUrl, username, password);
    }

    public static void close(Connection connection, Statement statement, PreparedStatement preparedStatement, ResultSet resultSet) throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * @param c
     * @param sql
     * @param args
     * @param <T>
     * @return
     * @throws Exception
     * @function 获取多条记录
     */
    public static  <T> List<T> getMultiInstance(Class c, String sql, Object... args) throws Exception {
        //1.获取连接
        Connection conn = getConnection();
        //2.获取预处理SQL对象
        PreparedStatement ps = conn.prepareStatement(sql);
        //3.占位符赋值
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }
        //4.执行SQL
        ResultSet res = ps.executeQuery();
        //5.处理结果集
        /**
         * @Reflect
         * res将查询出数据的字段数据即元数据信息封装在Meta-Data中
         * 获取元数据，再获取查询的列数以及字段名
         * 通过res获取字段的对应值
         * 通过反射+字段名把对应值动态注入进去
         */
        List<T> resList = new ArrayList<>();
        while (res.next()) {
            ResultSetMetaData metaData = res.getMetaData();
            int cCount = metaData.getColumnCount();
            T t = (T) c.getConstructor(null).newInstance();
            //反射注入需要的class对象
            Class cusC = t.getClass();
            for (int i = 0; i < cCount; i++) {
                //获取字段名
                String columnName = metaData.getColumnLabel(i + 1);//获取别名对应POJO类，如果没有别名就是原名
                Object columnValue = res.getObject(i + 1);
                //反射实现动态注入数据
                //获取任意权限的字段
                Field field = cusC.getDeclaredField(columnName);
                //获取private的权限
                field.setAccessible(true);
                //设置属性值
                field.set(t, columnValue);
            }
            resList.add(t);
        }

        //6.关闭资源
        close(conn,null, ps, res);
        //7.返回查询结果
        return resList;
    }





    /**
     * 驼峰转下划线,最后转为大写
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString().toUpperCase();
    }

    public static String dropTablePrefix(String tableName) {
        return tableName.substring(tableName.indexOf("_")+1);
    }
    /**
     * 下划线转驼峰,正常输出
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 将首字母小写
     * 大写字母 A=65，Z=90
     * 小写字母 a=97，z=122
     * @param str
     * @return
     */
    private static String lowerFirstChar(String str) {
        char[] chars = str.toCharArray();
        char firstChar = String.valueOf(chars[0]).toLowerCase().charAt(0);
        if (firstChar >= 97 && firstChar <= 122) {
            if (chars[0] < 97) {
                chars[0] += 32;
                return String.valueOf(chars);
            }
        }
        return str;
    }

    /****
     * 将首字母大写方法
     * @param str
     * @return
     */
    private static String upperFirstChar(String str) {

        char[] chars = str.toCharArray();
        char firstChar = String.valueOf(chars[0]).toLowerCase().charAt(0);
        if (firstChar >= 97 && firstChar <= 122) {
            if (chars[0] > 97) {
                chars[0] -= 32;
                return String.valueOf(chars);
            }

        }
        return str;
    }

}

class MySQLTableStruct {
//+-----------------+----------+--------------------------+-----------+----------------+------------+--------------+
//| columnName      | dataType | columnComment            | columnKey | extra          | isNullable | columnType   |
//+-----------------+----------+--------------------------+-----------+----------------+------------+--------------+
    String tableName;
    String columnName;
    String dataType;
    String columnComment;
    String columnKey;
    String extra;
    String isNullable;
    String columnType;


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public MySQLTableStruct() {

    }

    public MySQLTableStruct(String tableName ,String columnName,
                            String dataType,
                            String columnComment,
                            String columnKey,
                            String extra,
                            String isNullable,
                            String columnType) {
        this.tableName=tableName;
        this.columnName = columnName;
        this.dataType = dataType;
        this.columnComment = columnComment;
        this.columnKey = columnKey;
        this.extra = extra;
        this.isNullable = isNullable;
        this.columnType = columnType;
    }

    @Override
    public String toString() {
        String result="";
        try {
            result= Constants.jsonMapper.writeValueAsString(this);
        } catch (Exception e) {
            result=getClass().getName() + "@" + Integer.toHexString(hashCode());
            e.printStackTrace();
        } finally {

        }
        return result;
    }
}

class MySQLTableName {
    String dbName;
    String tableName;
    String tableComment;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
    public MySQLTableName (){

    }
    public MySQLTableName (String dbName,String tableName,String tableComment){
        this.dbName=dbName;
        this.tableName=tableName;
        this.tableComment=tableComment;
    }
    @Override
    public String toString(){
        String result="";
        try {
            result=  Constants.jsonMapper.writeValueAsString(this);
        } catch (Exception e) {
            result=getClass().getName() + "@" + Integer.toHexString(hashCode());
            e.printStackTrace();
        } finally {

        }
        return result;
    }
}

/*
select table_name tableName,column_name columnName,data_type dataType,column_comment columnComment,column_key columnKey,extra ,
             is_nullable as isNullable,column_type as columnType
              from information_schema.columns
           where table_name = 'chat_list' and table_schema = 'chat'
         order by ordinal_position


create table `tmp_sys_user` (
  `id` bigint(11) not null auto_increment,
  `user_name` varchar(32) not null default '' comment '用户名',
  `user_pwd` varchar(32) not null default '' comment '加盐后用户密码',
  `user_phone` varchar(20) not null default '' comment '手机号',
  `user_email` varchar(100) default '' comment '邮箱地址',
  `user_sex` tinyint(4) not null default 0 comment '性别，0-男；1-女',
  `create_time` timestamp  not null default CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp  not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后修改时间',
  `delete_flag` tinyint(4)  not null default 0 comment '是否删除，0-未删除；1-已删除',
  primary key (`id`)
) engine=innodb  default charset=utf8 comment='用户表';

create table `tmp_sys_user2` (
  `id` bigint(11) not null auto_increment,
  `user_name` varchar(32) not null default '' comment '用户名',
  `user_pwd` varchar(32) not null default '' comment '加盐后用户密码',
  `user_phone` varchar(20) not null default '' comment '手机号',
  `user_email` varchar(100) default '' comment '邮箱地址',
  `user_sex` tinyint(4) not null default 0 comment '性别，0-男；1-女',
  `create_time` timestamp  not null default CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp  not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后修改时间',
  `delete_flag` tinyint(4)  not null default 0 comment '是否删除，0-未删除；1-已删除',
  primary key (`id`)
) engine=innodb  default charset=utf8 comment='用户表';

create table `tmp_sys_user3` (
  `id` bigint(11) not null auto_increment,
  `user_name` varchar(32) not null default '' comment '用户名',
  `user_pwd` varchar(32) not null default '' comment '加盐后用户密码',
  `user_phone` varchar(20) not null default '' comment '手机号',
  `user_email` varchar(100) default '' comment '邮箱地址',
  `user_sex` tinyint(4) not null default 0 comment '性别，0-男；1-女',
  `create_time` timestamp  not null default CURRENT_TIMESTAMP comment '创建时间',
  `update_time` timestamp  not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后修改时间',
  `delete_flag` tinyint(4)  not null default 0 comment '是否删除，0-未删除；1-已删除',
  primary key (`id`)
) engine=innodb  default charset=utf8 comment='用户表';

insert into `tmp_sys_user`(`user_name`,`user_pwd`,`user_phone`,`user_email`,`user_sex`)
values ('root','123456123','13112341234','',0),('zhouxx','123456123','13112341234','',0),('JonLee','123456123','13112341234','',0)
,('zhouxx12','123456123','13112341234','',0),('tom','123456123','13112341234','',0),('tony','123456123','13112341234','',0)
,('jim','123456123','13112341234','',0),('lily','123456123','13112341234','',0),('mary','123456123','13112341234','',0)
,('judy','123456123','13112341234','',0),('ted','123456123','13112341234','',0),('red','123456123','13112341234','',0);

* */