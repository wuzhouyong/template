package com.teamone.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.teamone.project.system.domain.entity.PageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author lw
 * @date 2018/8/4  3:20 下午
 */
public class MysqlGenerator {

    /**
     * 读取控制台内容
     */
    /*
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }*/

    /**
     * 需要生成的表
     * 获取数据库表数组
     * mysql -uroot -proot -S  -N -e "select table_name from information_schema.tables where table_schema='security-rbac';"|awk '{print "\""$1"\","}'
     */
    final static String[] tables = {
            "sys_dept"
    };

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新几只，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
        /*TableFill isDeleted = new TableFill("isDeleted", FieldFill.INSERT);
        TableFill createdTime = new TableFill("createdTime", FieldFill.INSERT);
        TableFill createdUserId = new TableFill("createdUserId", FieldFill.INSERT);
        TableFill createdUserName = new TableFill("createdUserName", FieldFill.INSERT);

        TableFill modifiedTime = new TableFill("modifiedTime", FieldFill.UPDATE);
        TableFill modifiedUserId = new TableFill("modifiedUserId", FieldFill.UPDATE);
        TableFill modifiedUserName = new TableFill("modifiedUserName", FieldFill.UPDATE);

        tableFillList.add(isDeleted);
        tableFillList.add(createdTime);
        tableFillList.add(createdUserId);
        tableFillList.add(createdUserName);
        tableFillList.add(modifiedTime);
        tableFillList.add(modifiedUserId);
        tableFillList.add(modifiedUserName);*/

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("lw");
        gc.setOpen(false);
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/ry-vue?useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true&characterEncoding=utf8");

        //mysql1.8驱动
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();

        //根据实际项目名称修改
        pc.setParent("com.lw");
        pc.setController("web.controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        pc.setEntity("pojo.entity");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);

        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        TemplateConfig tc = new TemplateConfig();
        tc.setController("/vm/controller.java.vm");
        tc.setService("/vm/service.java.vm");
        tc.setServiceImpl("/vm/serviceImpl.java.vm");
        tc.setMapper("/vm/mapper.java.vm");
        tc.setEntity("/vm/entity.java.vm");
        tc.setXml("/vm/mapper.xml.vm");
        mpg.setTemplate(tc);
        mpg.setCfg(cfg);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tables);
        strategy.setSuperEntityClass(PageRequest.class);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setSuperEntityClass(PageRequest.class);
        strategy.setRestControllerStyle(true);
        strategy.setTablePrefix("sys_");//表前缀
        strategy.setTableFillList(tableFillList);//设置填充字段，主要针对日期
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

}
