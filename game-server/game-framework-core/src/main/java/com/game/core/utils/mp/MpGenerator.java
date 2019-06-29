//package com.game.core.utils.mp;
//
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
//import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
//import com.baomidou.mybatisplus.generator.config.rules.DbType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Auther: wx
// * @Desc :  逆向生成代码
// * @Date : 下午 5:49 2019/5/6 0006
// */
//public class MpGenerator {
//
//    public static void main(String[] args) {
//        String path = "com.game.common.";
//        AutoGenerator mpg = new AutoGenerator()
//                .setGlobalConfig(new GlobalConfig()
//                        .setOutputDir("D:\\CodeGenerator")
//                        .setFileOverride(true)
//                        .setActiveRecord(false)
//                        .setEnableCache(false)// XML 二级缓存
//                        .setBaseResultMap(true)// XML ResultMap
//                        .setBaseColumnList(true)// XML columList
//                        .setBaseResultMap(true)
//                        .setAuthor("wx")//名称配置
//                        .setControllerName("%sApi"))
//                .setDataSource(new DataSourceConfig().setDbType(DbType.MYSQL).setTypeConvert(new MySqlTypeConvert() {
//                    // 自定义数据库表字段类型转换【可选】
//                    @Override
//                    public DbColumnType processTypeConvert(String fieldType) {
//                        System.out.println("转换类型：" + fieldType);
//                        return super.processTypeConvert(fieldType);
//                    }
//                })//数据库配置
//                        .setDriverName("com.mysql.jdbc.Driver")
//                        .setUrl("jdbc:mysql://127.0.0.1:3306/game-server?characterEncoding=utf8")
//                        .setUsername("root")
//                        .setPassword("123456"))
//                //包配置
//                .setPackageInfo(new PackageConfig()
//                        .setController(path+"api")
//                        .setMapper(path+"repository")
//                        .setXml(path+"repository.xml")
//                        .setService(path+"service")
//                        .setServiceImpl(path+"service.impl")
//                        .setEntity(path+"entity.user")
//                        .setParent(""));
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
//        strategy.setTablePrefix("v_", "v_");// 此处可以修改为您的表前缀
//        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
//        //strategy.setInclude("im_pressure_differential_year");
//        // 需要生成的表
//        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
//        // 自定义实体父类
//        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
//        // 自定义实体，公共字段
//        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
//        // 自定义 repository 父类
//        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
//        // 自定义 service 父类
//        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
//        // 自定义 service 实现类父类
//        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
//        // 自定义 controller 父类
//        strategy.setSuperControllerClass("com.lrkj.im.framework.core.mvc.api.BaseApi");
//        // 【实体】是否生成字段常量（默认 false）
//        // public static final String ID = "test_id";
//        strategy.setEntityColumnConstant(true);
//        // 【实体】是否为构建者模型（默认 false）
//        // public User setName(String name) {this.name = name; return this;}
//        strategy.setEntityBuilderModel(true);
//        mpg.setStrategy(strategy);
//        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("date", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
//                map.put("specialTag2", "#");
//                this.setMap(map);
//            }
//        };
//        mpg.setCfg(cfg);
//        mpg.execute(); // 执行生成
//    }
//
//}
