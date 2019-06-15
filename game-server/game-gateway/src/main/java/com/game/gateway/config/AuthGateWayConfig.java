package com.game.gateway.config;

import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.HashSet;
import java.util.Map;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 2:22 2019/6/15 0015
 * @explain :
 */
public class AuthGateWayConfig {


    /**
     * @Author: wx
     * @Date  : 下午 2:25 2019/6/15 0015 
     * @params: 
     * @Desc  :  zuul的断路器设置
     */
//    @Bean
//    public Set<ZuulFallbackProvider> zuulFallbackProviders(ZuulProperties zuulProperties) {
//        Set<ZuulFallbackProvider> zuulFallbackProviders = new HashSet<>();
//        Map<String, ZuulProperties.ZuulRoute> zMap = zuulProperties.getRoutes();
//        zMap.values()
//                .forEach(zuulRoute -> zuulFallbackProviders.add(new LrZuulFallbackProvider(zuulRoute.getServiceId())));
//        return zuulFallbackProviders;
//    }

    /**
     * 设置跨域参数
     *
     * @return 跨域拦截器
     */
    @Bean
    @Order
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.setMaxAge(3600L);
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
    
}
