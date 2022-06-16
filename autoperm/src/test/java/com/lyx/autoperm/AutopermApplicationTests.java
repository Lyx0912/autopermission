package com.lyx.autoperm;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;

import javax.sql.DataSource;
import java.util.Collections;

@SpringBootTest
class AutopermApplicationTests {

    @Autowired
    private DruidDataSource dataSource;

    @Test
    void contextLoads() {
        FastAutoGenerator.create(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword())
                .globalConfig(builder -> {
                    builder.author("liyongxuan") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.lyx") // 设置父包名
                            .moduleName("autoperm") // 设置父包模块名
                            .entity("entity")
                            .controller("controller")
                            .service("service")
                            .serviceImpl("impl")
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "E:\\WorkSpace\\autoperm\\src\\main\\java\\com\\lyx\\autoperm\\mapper"))
                            .pathInfo(Collections.singletonMap(OutputFile.entity, "E:\\WorkSpace\\autoperm\\src\\main\\java\\com\\lyx\\autoperm\\entity"))
                            .pathInfo(Collections.singletonMap(OutputFile.service, "E:\\WorkSpace\\autoperm\\src\\main\\java\\com\\lyx\\autoperm\\service"))
                            .pathInfo(Collections.singletonMap(OutputFile.serviceImpl, "E:\\WorkSpace\\autoperm\\src\\main\\java\\com\\lyx\\autoperm\\service\\impl"))
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\WorkSpace\\autoperm\\src\\main\\resources\\mybatis")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("L_ROLE") // 设置需要生成的表名
                            .addInclude("L_USER")
                            .addInclude("L_PERMISSON")
                            .addInclude("L_USER_ROLE")
                            .addInclude("L_ROLE_PERMISSON")
                            .addTablePrefix("L_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
