package com.game.core.utils.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther : wx
 * @Desc :
 * @Date :  下午 12:10 2019/5/15 0015
 * @explain :  是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
 *              这个swagger-ui.html相关的所有前端静态文件都在springfox-swagger-ui-2.4.0.jar
 *              
 *              问题：SpringBoot自动配置本身并不会把/swagger-ui.html这个路径映射到对应的目录META-INF/resources/下面。
 *                      我们加上这个映射即可  【即是 webmvcConfig】
 */
@EnableSwagger2
@Configuration
public class SwaggerUtil {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable(true).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.game.*.api"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("wxApi文档")
                .description("wxApi文档")
                // 作者信息
                .contact(new Contact("wx","", "wangxingstudy@qq.com"))
                .version("1.0.0")
                .build();
    }
}
