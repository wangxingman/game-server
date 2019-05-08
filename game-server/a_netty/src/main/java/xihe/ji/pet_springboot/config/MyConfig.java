package xihe.ji.pet_springboot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.Arrays;

/**
 * @author : jch
 * @version V1.0
 * @Project: pet_springboot
 * @Package xihe.ji.pet_springboot.config
 * @Description: 自己的配置 添加过滤器
 * @date Date : 2018年09月06日 15:05
 */
@Configuration
public class MyConfig {

    @Bean
    public FilterRegistrationBean cors(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new myFilter());
        bean.setUrlPatterns(Arrays.asList("/*"));
        bean.setOrder(1);
        bean.setName("CORS");
        return bean;
    }
}
