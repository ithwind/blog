package com.ithwind.domain;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.lang.annotation.Annotation;
import java.util.Collections;

public class generator {
    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/sg_blog",
                "root",
                "qb030929")
                .globalConfig(builder -> {
                    builder.author("IthWind")//作者
                            .disableOpenDir()
                            .outputDir("C:\\Users\\IthWind\\IdeaProjects\\demo\\framework\\src\\main\\java")//输出路径
                            .commentDate("YYYY-MM-DD");//生成日期格式
                })
                .packageConfig(builder -> builder
                        .parent("com.ithwind")//父包名
                        .entity("domain.pojo")//生成实体类
                        .mapper("domain.mapper")//生成mapper
                        .xml("domain.mapperXml")
                        .service("service")
                        .serviceImpl("service.Impl")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Users\\IthWind\\IdeaProjects\\demo\\framework\\src\\main\\resources\\mapper")))
                .strategyConfig(builder -> builder.addInclude("sg_comment")//添加表
                        .addTablePrefix("sg_")
                        .addTablePrefix("sys_")
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImp")

                        .entityBuilder()
                        .enableLombok()
                        .enableTableFieldAnnotation()
                        .logicDeleteColumnName("delete")

                        .mapperBuilder()
                        .enableFileOverride()
                        .enableBaseResultMap()
                        .superClass(BaseMapper.class)
                        .formatMapperFileName("%sMapper")
                        .mapperAnnotation(Annotation.class)
                        .enableFileOverride())
                .execute();
    }
}
