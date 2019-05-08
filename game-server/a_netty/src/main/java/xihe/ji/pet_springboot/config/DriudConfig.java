package xihe.ji.pet_springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : jch
 * @version V1.0
 * @Project: pet_springboot
 * @Package xihe.ji.pet_springboot.config
 * @Description: 阿里数据源
 * @date Date : 2018年09月06日 11:42
 */
@Configuration
public class DriudConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**
     * 配置Druid监控
     *
     */
    @Bean
    public ServletRegistrationBean registrationBean(){
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String ,String > map=new HashMap<>();
        map.put("loginUsername","jch");
        map.put("loginPassword","xihejch");
        map.put("allow","");
        map.put("deny","192.168.1.20");
        registrationBean.setInitParameters(map);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String ,String> map=new HashMap<>();
        map.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(map);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}
